package com.overlord.app.compose.pages.dev

import HelloWorld
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.phoenixoverlord.pravegaapp.framework.BaseActivity
import com.phoenixoverlord.pravegaapp.toast

@Preview
@Composable
fun DevScreen(navController: NavController) {
    val context = AmbientContext.current
    val message = HelloWorld().getMessage()
    Column {
        Text(text = "Dev Screen")
        Button(onClick = { (context as BaseActivity).toast("Pravega working with compose") }) {
            Text(text = "Dev Screen")
        }
        Text(message)
    }
}