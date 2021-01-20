package com.overlord.app.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.KeyEvent
import androidx.activity.viewModels
import androidx.compose.ui.platform.setContent
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.react.modules.core.PermissionAwareActivity
import com.google.firebase.database.DatabaseReference
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
        React.initialise(this, application as OverlordLabApplication);

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupReact()
        setContent {
            MainScreen()
        }
    }

    override fun onBackPressed() {
        React.instanceManager?.apply { onBackPressed() } ?: super.onBackPressed()
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_MENU && React.instanceManager != null) {
            React.instanceManager?.showDevOptionsDialog()
            return true
        }
        return super.onKeyUp(keyCode, event)
    }

    override fun invokeDefaultOnBackPressed() {
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        React.instanceManager?.apply { onHostPause(this@MainActivity) }
    }

    override fun onResume() {
        super.onResume()
        React.instanceManager?.apply { onHostResume(this@MainActivity, this@MainActivity) }
    }

    override fun onDestroy() {
        super.onDestroy()
        React.destroy(this)
    }
}