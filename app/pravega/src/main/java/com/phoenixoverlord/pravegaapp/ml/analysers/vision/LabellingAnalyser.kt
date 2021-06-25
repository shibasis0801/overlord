package com.phoenixoverlord.pravegaapp.ml.analysers.vision

import android.annotation.SuppressLint
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_BLOCK_PRODUCER

import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.phoenixoverlord.pravegaapp.extensions.logDebug
import com.phoenixoverlord.pravegaapp.extensions.logError
import com.phoenixoverlord.pravegaapp.framework.BaseActivity
import com.phoenixoverlord.pravegaapp.mechanisms.base.SingleThreadAnalyzer

class LabellingAnalyser(
    activity: BaseActivity,
    confidenceThreshold: Float
): SingleThreadAnalyzer(activity) {

    private val options = ImageLabelerOptions
        .Builder()
        .setConfidenceThreshold(confidenceThreshold)
        .build()
    val client = ImageLabeling.getClient(options)


    override fun buildAnalysis(builder: ImageAnalysis.Builder) = builder
        .setBackpressureStrategy(STRATEGY_BLOCK_PRODUCER)
        .build()


    @SuppressLint("UnsafeExperimentalUsageError")
    override fun createAnalyser() =
        ImageAnalysis.Analyzer { image ->
            image.image?.apply {
                val inputImage =
                    InputImage.fromMediaImage(this, image.imageInfo.rotationDegrees)

                client
                    .process(inputImage)
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