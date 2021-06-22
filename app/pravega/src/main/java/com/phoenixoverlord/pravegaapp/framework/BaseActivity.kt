package com.phoenixoverlord.pravegaapp.framework

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.phoenixoverlord.pravegaapp.mechanisms.ActivityResultHandler
import com.phoenixoverlord.pravegaapp.mechanisms.NotificationModule
import com.phoenixoverlord.pravegaapp.mechanisms.PermissionsModule
import com.phoenixoverlord.pravegaapp.utils.LoopingAtomicInteger


/**
 * Good ideas. Needs extensive rewrite.
 * Things to add
 *
 * New ActivityResult / Permission APIs
 * New Camera APIs
 * KotlinFlow
 *
 */


abstract class BaseActivity : AppCompatActivity() {
    private val loopingAtomicInteger = LoopingAtomicInteger(100, 10000)
    private val activityResultHandler = ActivityResultHandler()
    // Improve notificationModule later, implement lifecycle
    val notificationModule : NotificationModule by lazy { NotificationModule(this) }
    private val permissionsModule = PermissionsModule()


    open fun useComponents(vararg components: Component) {
        components.forEach { lifecycle.addObserver(it) }

    }

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
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionsModule.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}