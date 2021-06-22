package com.phoenixoverlord.pravegaapp.mechanisms

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.phoenixoverlord.pravegaapp.R
import com.phoenixoverlord.pravegaapp.extensions.logDebug
import com.phoenixoverlord.pravegaapp.extensions.logError
import com.phoenixoverlord.pravegaapp.framework.BaseActivity
import com.phoenixoverlord.pravegaapp.framework.Component
import com.phoenixoverlord.pravegaapp.toast
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

// Want
//class CameraModule: Component {
//    override fun onCreate(activity: BaseActivity) {
//        requestPermissions, createExecutor etc
//    }
//}

class CameraComponent(activity: BaseActivity, val viewFinder: PreviewView): Component(activity) {
    private val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
    private var imageCapture: ImageCapture? = null

    override fun onCreate(owner: LifecycleOwner) {
        cameraExecutor = Executors.newSingleThreadExecutor()
        activity.withPermissions(Manifest.permission.CAMERA)
            .execute {
                startCamera(activity, viewFinder)
            }
    }

    private class LabellingAnalyser: ImageAnalysis.Analyzer {
        @SuppressLint("UnsafeExperimentalUsageError")
        override fun analyze(image: ImageProxy) {
            image.image?.apply {
                val inputImage =
                    InputImage.fromMediaImage(this, image.imageInfo.rotationDegrees)

                val options = ImageLabelerOptions.Builder().setConfidenceThreshold(0.7f).build()
                val client = ImageLabeling.getClient(options)

                client.process(inputImage)
                    .addOnSuccessListener {
                        it.forEach {
                            logDebug("IMAGE_CONTENTS", "${it.text}, ${it.confidence}")
                        }
                        image.close()
                    }
                    .addOnFailureListener(::logError)
            }
        }
    }


    private fun startCamera(activity: BaseActivity, viewFinder: PreviewView) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(activity)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()

            val imageAnalyser = ImageAnalysis.Builder()
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, LabellingAnalyser())
                }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(activity, cameraSelector, preview, imageCapture, imageAnalyser)
            } catch (e: Exception) {
                logError(e)
            }
        }, ContextCompat.getMainExecutor(activity))
    }


    private lateinit var cameraExecutor: ExecutorService

    private fun getOutputDirectory(activity: BaseActivity): File {
        return activity.run {
            val mediaDir = externalMediaDirs.firstOrNull()?.let {
                File(it, resources.getString(R.string.app_name)).apply { mkdirs() } }
            return if (mediaDir != null && mediaDir.exists())
                mediaDir else filesDir
        }
    }

    /**
     * Needs checks for safe calling from multiple places
     */
    fun takePhoto(activity: BaseActivity) {
        imageCapture?.apply {

            val photoFile = File(
                getOutputDirectory(activity),
                SimpleDateFormat(FILENAME_FORMAT, Locale.UK)
                    .format(System.currentTimeMillis()) + ".jpg"
            )

            val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

            takePicture(outputOptions, ContextCompat.getMainExecutor(activity), object: ImageCapture.OnImageSavedCallback {
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

    override fun onDestroy(owner: LifecycleOwner) {
        cameraExecutor.shutdown()
    }
}