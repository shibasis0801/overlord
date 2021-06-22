package com.overlord.app.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.overlord.app.compose.pages.about.AboutScreen
import com.overlord.app.compose.pages.dev.DevScreen
import com.overlord.app.compose.pages.dev.compose.PravegaScreen
import com.overlord.app.compose.pages.game.GameScreen
import com.overlord.app.compose.pages.ml.MLScreen
import com.overlord.app.main.MainActivity


@Composable
fun SetupRouter(navController: NavHostController) {
    val activity = LocalContext.current as MainActivity
    NavHost(navController, startDestination = Route.Dev.name) {
//                Nest Routes Here.
        composable(Route.Dev.name) {
            DevScreen(navController)
        }
        composable(Route.ML.name) {
            MLScreen(navController)
        }
        composable(Route.Game.name) {
            GameScreen(navController)
        }
        composable(Route.About.name) {
            AboutScreen(navController)
        }

        composable(Route.Pravega.name) {
            PravegaScreen(navController)
        }
    }
}