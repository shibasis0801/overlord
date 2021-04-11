package com.overlord.app.compose

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.compose.rememberNavController
import com.overlord.app.compose.components.BottomBar
import com.overlord.app.main.MainViewModel
import com.overlord.app.navigation.Route
import com.overlord.app.theme.OverlordAndroidTheme
import com.phoenixoverlord.pravegaapp.framework.BaseActivity


val AmbientActivity = compositionLocalOf<BaseActivity> { error("Register activity") }
val AmbientViewModel = compositionLocalOf<MainViewModel> { error("Register View Model") }

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel()
) {
    val routes = Route.getRoutes()
    val navController = rememberNavController()

    val context = LocalContext.current
    val base = context as BaseActivity

    OverlordAndroidTheme(darkTheme = false) {
        CompositionLocalProvider(AmbientActivity provides base, AmbientViewModel provides mainViewModel) {
            Scaffold(
                topBar = {},
                bottomBar = { BottomBar(navController, routes) }
            ) {
                Route.SetupRouter(navController)
            }
        }
    }
}