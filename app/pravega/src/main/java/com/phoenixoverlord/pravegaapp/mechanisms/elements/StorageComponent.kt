package com.phoenixoverlord.pravegaapp.mechanisms.elements

import android.content.ContentValues
import android.provider.MediaStore.MediaColumns
import com.phoenixoverlord.pravegaapp.R
import com.phoenixoverlord.pravegaapp.framework.BaseActivity
import com.phoenixoverlord.pravegaapp.framework.Component
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Android Storage APIs are very complicated now, create abstraction using this.
 */
class StorageComponent(activity: BaseActivity): Component(activity) {
    private val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"

    fun getHomeDirectory(): File {
        return activity.run {
            val appName = resources.getString(R.string.app_name)

            val mediaDir = externalMediaDirs
                .firstOrNull()
                ?.let { File(it, appName).apply { mkdirs() } }

            val hasMediaDir = mediaDir != null && mediaDir.exists()

            return if (hasMediaDir) mediaDir!! else filesDir
        }
    }


    // Send "jpg", "png", etc without the dot.
    fun timeStampedFileName(extension: String): String {
        val timestamp = SimpleDateFormat(FILENAME_FORMAT, Locale.UK).format(System.currentTimeMillis())
        return "$timestamp.$extension"
    }
}