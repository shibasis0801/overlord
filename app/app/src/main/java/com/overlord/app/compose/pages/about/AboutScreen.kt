package com.overlord.app.compose.pages.about

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.overlord.app.main.MainActivity
import com.overlord.app.react.ReactActivity
import com.phoenixoverlord.pravegaapp.framework.extensions.finishAndStart

@Preview
@Composable
fun AboutScreen(navController: NavController) {
    val activity = AmbientContext.current as MainActivity
    Column {
        Button(onClick = { activity.finishAndStart(ReactActivity::class.java) }) {
            Text("Move to React Native")
        }
    }
}