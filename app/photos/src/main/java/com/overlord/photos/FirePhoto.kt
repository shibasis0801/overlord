package com.overlord.photos

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import javax.inject.Inject

/*
Direct Upload
Filters (maybe https://github.com/cats-oss/android-gpuimage)
Wall and comments
Videos using ExoPlayer
Google Photos integration
Instagram share (simple)
*/

class FirePhoto {
    @Inject
    lateinit var storageReference: StorageReference

    @Inject
    lateinit var firestore: FirebaseFirestore
}