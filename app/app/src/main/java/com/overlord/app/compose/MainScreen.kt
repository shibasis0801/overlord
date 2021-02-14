package com.overlord.app.compose

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.ambientOf
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.compose.rememberNavController
import com.overlord.app.compose.components.BottomBar
import com.overlord.app.main.MainViewModel
import com.overlord.app.navigation.Route
import com.overlord.app.theme.OverlordAndroidTheme
import com.phoenixoverlord.pravegaapp.framework.BaseActivity


val AmbientActivity = ambientOf<BaseActivity> { error("Register activity") }
val AmbientViewModel = ambientOf<MainViewModel> { error("Register View Model") }

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = viewModel()
) {
    val routes = Route.getRoutes()
    val navController = rememberNavController()

    val context = AmbientContext.current
    val base = context as BaseActivity

    OverlordAndroidTheme(darkTheme = false) {
        Providers(AmbientActivity.provides(base), AmbientViewModel.provides(mainViewModel)) {
            Scaffold(
                topBar = {},
                bottomBar = { BottomBar(navController, routes) }
            ) {
                Route.SetupRouter(navController)
            }
        }
    }
}