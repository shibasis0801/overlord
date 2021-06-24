package com.phoenixoverlord.pravegaapp.ml.analysers.vision

import android.annotation.SuppressLint
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.UseCase
import androidx.lifecycle.LifecycleOwner
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.phoenixoverlord.pravegaapp.extensions.logDebug
import com.phoenixoverlord.pravegaapp.extensions.logError
import com.phoenixoverlord.pravegaapp.framework.BaseActivity
import com.phoenixoverlord.pravegaapp.mechanisms.base.SingleThreadAnalyzer

class LabellingAnalyser(activity: BaseActivity): SingleThreadAnalyzer(activity) {
    @SuppressLint("UnsafeExperimentalUsageError")
    override fun createAnalyser() =
        ImageAnalysis.Analyzer { image ->
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
                    .addOnCompleteListener { image.close() }
            }
        }
}