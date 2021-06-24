package com.phoenixoverlord.pravegaapp.mechanisms.base

import androidx.camera.core.ImageAnalysis
import androidx.lifecycle.LifecycleOwner
import com.phoenixoverlord.pravegaapp.framework.BaseActivity
import com.phoenixoverlord.pravegaapp.framework.Component
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


abstract class AnalyzerComponent(activity: BaseActivity): Component(activity) {
    lateinit var executor: ExecutorService

    abstract fun createExecutor(): ExecutorService
    abstract fun createAnalyser(): ImageAnalysis.Analyzer

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        executor = createExecutor()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        executor.shutdown()
    }

    fun buildAnalyser() = ImageAnalysis
        .Builder()
        .build()
        .apply { setAnalyzer(executor, createAnalyser()) }
}

abstract class SingleThreadAnalyzer(activity: BaseActivity): AnalyzerComponent(activity) {
    override fun createExecutor(): ExecutorService = Executors.newSingleThreadExecutor()
}

abstract class ThreadPoolAnalyzer(activity: BaseActivity): AnalyzerComponent(activity) {
    override fun createExecutor(): ExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
}