package com.overlord.app.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.KeyEvent
import androidx.activity.viewModels
import com.facebook.react.PackageList
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactPackage
import com.facebook.react.ReactRootView
import com.facebook.react.common.LifecycleState
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.soloader.SoLoader
import com.google.firebase.database.DatabaseReference
import com.overlord.app.BuildConfig
import com.overlord.app.OverlordLabApplication
import com.overlord.app.react.React
import com.phoenixoverlord.pravegaapp.framework.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity(), DefaultHardwareBackBtnHandler {

    @Inject
    lateinit var firebase: DatabaseReference

    private val mainViewModel by viewModels<MainViewModel>()

    fun setupReact() {
        React.initialiseManager(this, application as OverlordLabApplication);

        if (!Settings.canDrawOverlays(this)) {
            startActivityGetResult(
                Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                ), 1
            ).addOnSuccessListener {
                React.instanceManager?.onActivityResult(this, 1, Activity.RESULT_OK, it);
            }.addOnSuccessListener {
                React.instanceManager?.onActivityResult(this, 1, Activity.RESULT_CANCELED, it);
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
            .build();
        // The string here (e.g. "MyReactNativeApp") has to match
        // the string in AppRegistry.registerComponent() in index.js
        mReactRootView!!.startReactApplication(mReactInstanceManager, BuildConfig.REACT_NAME, null);
        setContentView(mReactRootView)
//        setupReact()
//        React.turnOnView(this)
//        setContentView(React.rootView)
//        setContent {
//            MainScreen()
//        }
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
//    override fun onBackPressed() {
//        React.instanceManager?.apply { onBackPressed() } ?: super.onBackPressed()
//    }
//
//    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_MENU && React.instanceManager != null) {
//            React.instanceManager?.showDevOptionsDialog()
//            return true
//        }
//        return super.onKeyUp(keyCode, event)
//    }
//
//    override fun invokeDefaultOnBackPressed() {
//        super.onBackPressed()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        React.instanceManager?.apply { onHostPause(this@MainActivity) }
//    }
//
//    override fun onResume() {
//        super.onResume()
//        React.instanceManager?.apply { onHostResume(this@MainActivity, this@MainActivity) }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        React.cleanManager(this)
//        React.turnOffView()
//    }
}