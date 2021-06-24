package com.phoenixoverlord.pravegaapp.mechanisms.helpers

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.camera.view.PreviewView
import java.util.concurrent.Executor

fun buildPreview(viewFinder: PreviewView) = Preview
    .Builder()
    .build()
    .apply { setSurfaceProvider(viewFinder.surfaceProvider) }


fun buildCapture() = ImageCapture.Builder().build()