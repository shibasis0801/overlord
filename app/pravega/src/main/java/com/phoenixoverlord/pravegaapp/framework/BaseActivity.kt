package com.phoenixoverlord.pravegaapp.framework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.phoenixoverlord.pravegaapp.mechanisms.ActivityResultHandler
import com.phoenixoverlord.pravegaapp.mechanisms.NotificationModule
import com.phoenixoverlord.pravegaapp.mechanisms.PermissionsModule
import com.phoenixoverlord.pravegaapp.utils.LoopingAtomicInteger

abstract class BaseActivity : AppCompatActivity() {
    private val loopingAtomicInteger = LoopingAtomicInteger(100, 10000)
    private val activityResultHandler = ActivityResultHandler()
    // Improve notificationModule later, implement lifecycle
    val notificationModule : NotificationModule by lazy { NotificationModule(this) }
    private val permissionsModule = PermissionsModule()


//    Camera API has changed. Need to rewrite
//    private val camera = CameraModule()
//    /** CameraModule */
//    fun takePhoto(prompt : String) = camera.takePhoto(this, prompt)

    /** ActivityResultModule */
    fun startActivityGetResult(
        intent : Intent,
        requestCode : Int = loopingAtomicInteger.nextInt()
    ) = activityResultHandler
        .createAction(requestCode)
        .perform {
            startActivityForResult(intent, requestCode)
        }

    /** PermissionsModule */
    fun withPermissions(vararg permissions : String) =
        permissionsModule.PermissionBuilder(loopingAtomicInteger.nextInt())
            .withPermissions(this, permissions.toCollection(ArrayList()))

    /** CompressionModule */
//    fun compressImage(image : File) = compressionModule.compressImage(this, image)


    //Update this to use new callback version
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activityResultHandler.onActivityResult(requestCode, resultCode, data)
        //    Camera API has changed. Need to rewrite
        //    camera.internalOnActivityResult(this, requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionsModule.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}