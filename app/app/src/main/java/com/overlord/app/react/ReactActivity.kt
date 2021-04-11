package com.overlord.app.react

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.KeyEvent
import com.facebook.react.PackageList
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactPackage
import com.facebook.react.ReactRootView
import com.facebook.react.common.LifecycleState
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.soloader.SoLoader
import com.overlord.app.BuildConfig
import com.phoenixoverlord.pravegaapp.framework.BaseActivity

class ReactActivity : BaseActivity(), DefaultHardwareBackBtnHandler {
    fun setupInstanceManager() {}
    fun setupRootView() {

    }

    // Convert this to activity result APIs
    fun setupOverlay() {
        if (!Settings.canDrawOverlays(this)) {
            startActivityGetResult(
                Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                ), 1
            ).addOnSuccessListener {
                mReactInstanceManager?.onActivityResult(this, 1, Activity.RESULT_OK, it);
            }.addOnSuccessListener {
                mReactInstanceManager?.onActivityResult(this, 1, Activity.RESULT_CANCELED, it);
            }
        }
    }

    private var mReactRootView: ReactRootView? = null
    private var mReactInstanceManager: ReactInstanceManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SoLoader.init(this, false)

        mReactRootView = ReactRootView(this)
        val packages: List<ReactPackage> = PackageList(application).packages
        mReactInstanceManager = ReactInstanceManager.builder()
            .setApplication(application)
            .setCurrentActivity(this)
            .setBundleAssetName("index.android.bundle")
            .setJSMainModulePath("index")
            .addPackages(packages)
            .setUseDeveloperSupport(BuildConfig.DEBUG)
            .setInitialLifecycleState(LifecycleState.RESUMED)
            .build()

        mReactRootView!!.startReactApplication(mReactInstanceManager, BuildConfig.REACT_NAME, null);
        setContentView(mReactRootView)
    }

    override fun invokeDefaultOnBackPressed() {
        super.onBackPressed()
    }
    override fun onPause() {
        super.onPause()
        if (mReactInstanceManager != null) {
            mReactInstanceManager!!.onHostPause(this)
        }
    }

    override fun onResume() {
        super.onResume()
        if (mReactInstanceManager != null) {
            mReactInstanceManager!!.onHostResume(this, this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mReactInstanceManager != null) {
            mReactInstanceManager!!.onHostDestroy(this)
        }
        if (mReactRootView != null) {
            mReactRootView!!.unmountReactApplication()
        }
    }

    override fun onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager!!.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager!!.showDevOptionsDialog()
            return true
        }
        return super.onKeyUp(keyCode, event)
    }
}