package com.overlord.app.pages.about

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.overlord.app.react.React

@Composable
fun ReactNative() {
    val reactNative = remember {
        React.rootView
    }
    reactNative?.apply {
        AndroidView({ this })
    }
}

@Preview
@Composable
fun AboutScreen(navController: NavController) {
    ReactNative()
}