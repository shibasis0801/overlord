package com.overlord.app.react

import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactRootView
import com.facebook.react.common.LifecycleState
import com.overlord.app.BuildConfig
import com.overlord.app.OverlordLabApplication
import com.overlord.app.main.MainActivity

object React {
    var instanceManager: ReactInstanceManager? = null
    var rootView: ReactRootView? = null

    fun initialise(activity: MainActivity, application: OverlordLabApplication) {
        instanceManager = ReactInstanceManager.builder()
            .setApplication(application)
            .setCurrentActivity(activity)
            .setJSMainModulePath("index.android.js")
            .setUseDeveloperSupport(BuildConfig.DEBUG)
            .setInitialLifecycleState(LifecycleState.RESUMED)
            .build()

        rootView = ReactRootView(activity)
        rootView?.startReactApplication(instanceManager, "ShibasisPatnaik", null);
    }

    fun destroy(activity: MainActivity) {
        instanceManager?.apply { onHostDestroy(activity) }
        rootView?.apply { unmountReactApplication() }

        instanceManager = null;
        rootView = null
    }
}