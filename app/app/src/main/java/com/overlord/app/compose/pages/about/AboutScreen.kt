package com.overlord.app.compose.pages.about

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.overlord.app.main.MainActivity
import com.phoenixoverlord.pravegaapp.toast


@Composable
fun AboutScreen(navController: NavController) {
    val activity = LocalContext.current as MainActivity
    Column {
        Button(onClick = { activity.toast("") }) {
            Text("Todo build about")
        }
    }
}