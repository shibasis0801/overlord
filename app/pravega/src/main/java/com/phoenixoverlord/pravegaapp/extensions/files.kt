package com.phoenixoverlord.pravegaapp.extensions

import android.os.Environment
import com.phoenixoverlord.pravegaapp.utils.uniqueName
import java.io.File


fun getDefaultFolder() : File {
    val destinationRoot = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
    return File(destinationRoot, "AppPictures")
}

fun createImageFile(fileName : String = uniqueName(), parentFolder : File = getDefaultFolder()) : File {
    return File(parentFolder, fileName)
}