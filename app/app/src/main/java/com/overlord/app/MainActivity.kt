package com.overlord.app

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.ui.platform.setContent
import com.google.firebase.database.DatabaseReference
import com.phoenixoverlord.pravegaapp.framework.BaseActivity
import com.phoenixoverlord.pravegaapp.toast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var firebase: DatabaseReference

    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }

        mainViewModel.getResponse()

        firebase.root.child("testing")
            .setValue("from overlord with hilt")
            .addOnCompleteListener {
                toast("Done")
            }
    }
}
