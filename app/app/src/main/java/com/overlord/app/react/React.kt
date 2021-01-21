package com.overlord.app.react

import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactPackage
import com.facebook.react.ReactRootView
import com.facebook.react.common.LifecycleState
import com.overlord.app.BuildConfig
import com.overlord.app.OverlordLabApplication
import com.overlord.app.main.MainActivity

// Improve Efficiency plox. Hacky
object React {
    var instanceManager: ReactInstanceManager? = null
    var rootView: ReactRootView? = null

    fun initialiseManager(activity: MainActivity, application: OverlordLabApplication) {

        instanceManager = ReactInstanceManager.builder()
            .setApplication(application)
            .setCurrentActivity(activity)
            .setJSMainModulePath("index.android.js")
            .setUseDeveloperSupport(BuildConfig.DEBUG)
            .setInitialLifecycleState(LifecycleState.RESUMED)
            .build()

    }

    fun turnOnView(activity: MainActivity) {
        rootView = ReactRootView(activity)
        rootView?.startReactApplication(instanceManager, "ShibasisPatnaik", null);
    }

    fun turnOffView() {
        rootView?.apply { unmountReactApplication() }
        rootView = null
    }

    fun cleanManager(activity: MainActivity) {
        instanceManager?.apply { onHostDestroy(activity) }
        instanceManager = null;
    }
}