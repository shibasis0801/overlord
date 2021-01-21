package com.overlord.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.AmbientContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.overlord.app.main.MainActivity
import com.overlord.app.pages.about.AboutScreen
import com.overlord.app.pages.dev.DevScreen
import com.overlord.app.pages.game.GameScreen
import com.overlord.app.pages.ml.MLScreen
import com.overlord.app.react.React

// Just like web routes, can have parameters in routes and deep links
sealed class Route(
    val name: String,
    val icon: ImageVector

) {
    object Dev: Route("dev", Icons.Filled.DesktopWindows) {
        // Chat, Social (Front-end and Back-end)

    }

    object Math: Route("math", Icons.Filled.Calculate) {
        // FFT implementation in C++, etc, etc.
        // Basic Neural Network implementation in C++ from ground.
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
            val activity = AmbientContext.current as MainActivity
            NavHost(navController, startDestination = Dev.name) {
                composable(Dev.name) {
                    React.turnOffView()
                    DevScreen(navController)
                }
                composable(ML.name) {
                    React.turnOffView()
                    MLScreen(navController)
                }
                composable(Game.name) {
                    React.turnOffView()
                    GameScreen(navController)
                }
                composable(About.name) {
                    React.turnOnView(activity)
                    AboutScreen(navController)
                }
            }
        }

        fun getRoutes() = listOf(
            Dev, ML, Game, About
        )
    }
}


