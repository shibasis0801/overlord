@file:JvmName("ActivityUtils")
package com.phoenixoverlord.pravegaapp.framework.extensions

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.coroutineScope
import com.phoenixoverlord.pravegaapp.framework.BaseActivity
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.CoroutineScope
import java.util.concurrent.Executor

fun AppCompatActivity.getSimpleName() : String {
    return this.javaClass.simpleName
}

fun AppCompatActivity.getTag() : String {
    val length = this.getSimpleName().length
    val till = Math.min(length - 1, 20)
    return this.getSimpleName().substring(0..till)
}

fun AppCompatActivity.toastSuccess(message : String) {
    Toasty.success(this, message, Toast.LENGTH_SHORT, true).show()
}

fun AppCompatActivity.toastError(message : String) {
    Toasty.error(this, message, Toast.LENGTH_SHORT, true).show()
}

fun AppCompatActivity.startOther(activity: Class<*>, disableAnimation: Boolean = false) {
    startActivity(Intent(this, activity).apply {
        if (disableAnimation)
            addFlags(FLAG_ACTIVITY_NO_ANIMATION)
    })
}

fun AppCompatActivity.finishAndStart(activity : Class<*>) {
    startActivity(Intent(this, activity))
    finish()
}

fun AppCompatActivity.replaceFragment(containerID : Int, fragment : Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(containerID, fragment)
        .commit()
}

fun AppCompatActivity.addFragment(containerID : Int, fragment : Fragment) {
    supportFragmentManager.beginTransaction()
        .add(containerID, fragment)
        .commit()
}

fun AppCompatActivity.safeIntentDispatch(intent : Intent) {
    intent.resolveActivity(packageManager)?.let {
        startActivity(intent)
    }
}

fun PackageManager.intentHandlerExists(intent : Intent) = intent.resolveActivity(this) != null

val BaseActivity.mainExecutorCompat: Executor
    get() = ContextCompat.getMainExecutor(this)

val BaseActivity.scope: LifecycleCoroutineScope
    get() = lifecycle.coroutineScope