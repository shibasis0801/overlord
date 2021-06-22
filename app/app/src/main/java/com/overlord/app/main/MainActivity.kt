package com.overlord.app.main

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.overlord.app.R
import com.phoenixoverlord.pravegaapp.framework.BaseActivity
import com.phoenixoverlord.pravegaapp.mechanisms.CameraComponent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

typealias LumaListener = (luma: Double) -> Unit

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    // Only use these values inside the activity / viewmodel. Not in compose (not through Ambients)
    @Inject
    lateinit var realtimeDB: DatabaseReference

    @Inject
    lateinit var firestoreDB: FirebaseFirestore

    @Inject
    lateinit var storage: StorageReference

    @Inject
    lateinit var auth: FirebaseAuth

    private val mainViewModel by viewModels<MainViewModel>()

    lateinit var camera: CameraComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            MainScreen()
//        }
        setContentView(R.layout.activity_main)
        camera = CameraComponent(this, findViewById(R.id.viewFinder))
        
        useComponents(camera)
        
        findViewById<Button>(R.id.camera_capture_button).setOnClickListener { camera.takePhoto(this) }

    }
}