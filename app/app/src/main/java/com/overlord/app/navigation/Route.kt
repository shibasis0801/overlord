package com.overlord.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.overlord.app.pages.about.AboutScreen
import com.overlord.app.pages.dev.DevScreen
import com.overlord.app.pages.game.GameScreen
import com.overlord.app.pages.ml.MLScreen

// Just like web routes, can have parameters in routes and deep links
sealed class Route(
    val name: String,
    val icon: ImageVector

) {
    object Dev: Route("dev", Icons.Filled.DesktopWindows) {
        // Chat, Social (Front-end and Back-end)

    }
    object ML: Route("ml", Icons.Filled.Flight) {
        // MLKit and HerokuFastAPI

    }
    object Game: Route("game", Icons.Filled.Gamepad) {
        // Native and Jetpack Compose

    }
    object About: Route("about", Icons.Filled.DeveloperMode) {
        // Will render PWA

    }

    companion object {
        @JvmStatic
        @Composable
        fun SetupRouter(navController: NavHostController) {
            NavHost(navController, startDestination = Dev.name) {
                composable(Dev.name) { DevScreen(navController) }
                composable(ML.name) { MLScreen(navController) }
                composable(Game.name) { GameScreen(navController) }
                composable(About.name) { AboutScreen(navController) }
            }
        }

        fun getRoutes() = listOf(
            Dev, ML, Game, About
        )
    }
}

