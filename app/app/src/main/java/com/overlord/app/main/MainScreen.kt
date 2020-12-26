package com.overlord.app.main

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.overlord.app.main.components.BottomBar
import com.overlord.app.navigation.Route
import com.overlord.app.theme.OverlordAndroidTheme


@Composable
fun MainScreen() {
    val routes = Route.getRoutes()
    val navController = rememberNavController()

    OverlordAndroidTheme {
        Scaffold(
            topBar = {},
            bottomBar = { BottomBar(navController, routes) }
        ) {
            Route.SetupRouter(navController)
        }
    }

}