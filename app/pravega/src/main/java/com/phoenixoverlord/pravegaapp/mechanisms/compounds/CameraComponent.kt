package com.phoenixoverlord.pravegaapp.mechanisms.compounds

import android.Manifest
import android.net.Uri
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.phoenixoverlord.pravegaapp.extensions.logDebug
import com.phoenixoverlord.pravegaapp.extensions.logError
import com.phoenixoverlord.pravegaapp.framework.BaseActivity
import com.phoenixoverlord.pravegaapp.framework.Component
import com.phoenixoverlord.pravegaapp.mechanisms.base.AnalyzerComponent
import com.phoenixoverlord.pravegaapp.mechanisms.elements.StorageComponent
import com.phoenixoverlord.pravegaapp.toast
import java.io.File
import java.lang.Exception

// Can be a memory leak to store activity
class CameraComponent(
    activity: BaseActivity,
    val storageComponent: StorageComponent?,
    val analyzerComponent: AnalyzerComponent?
): Component(activity) {

    var imageCapture: ImageCapture? = null

    fun start(preview: Preview? = null, capture: ImageCapture? = null) {
        imageCapture = capture
        activity.withPermissions(Manifest.permission.CAMERA)
            .execute {
                val cameraProviderFuture = ProcessCameraProvider.getInstance(activity)
                cameraProviderFuture.addListener({
                    val cameraProvider = cameraProviderFuture.get()

                    val imageAnalyser = analyzerComponent?.buildAnalyser()

                    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(activity, cameraSelector, preview, imageCapture, imageAnalyser)
                    } catch (e: Exception) {
                        logError(e)
                    }
                }, ContextCompat.getMainExecutor(activity))
            }
    }

    /**
     * Needs checks for safe calling from multiple places
     */
    fun takePhoto() {
        if (imageCapture != null && storageComponent != null) {
            val photoFile = File(
                storageComponent.getHomeDirectory(),
                storageComponent.timeStampedFileName("jpg")
            )

            val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

            imageCapture?.takePicture(outputOptions,
                ContextCompat.getMainExecutor(activity),
                object: ImageCapture.OnImageSavedCallback {
                    override fun onError(exc: ImageCaptureException) {
                        logError(exc)
                    }

                    override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                        val savedUri = Uri.fromFile(photoFile)
                        activity.toast("Photo capture succeeded: $savedUri")
                        activity.logDebug("Photo capture succeeded: $savedUri")
                    }
            })
        }
    }

    fun stop() {
        val future = ProcessCameraProvider.getInstance(activity)
        future.addListener({
                           future.get().unbindAll()
        }, ContextCompat.getMainExecutor(activity))
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        stop()
    }
}