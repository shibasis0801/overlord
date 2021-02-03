package com.overlord.app.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.ui.platform.setContent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.phoenixoverlord.pravegaapp.framework.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}