package com.overlord.app.compose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

// Just like web routes, can have parameters in routes and deep links
sealed class Route(
    val name: String,
    val icon: ImageVector? = null

) {
    companion object {
        fun getBottomNavRoutes() = listOf(
            Dev, ML, Game, About
        )
    }

    // Main Routes, can optionally have extra logic.
    object Dev: Route("dev", Icons.Filled.DesktopWindows)
    object ML: Route("ml", Icons.Filled.Flight)
    object Game: Route("game", Icons.Filled.Gamepad)
    object About: Route("about", Icons.Filled.DeveloperMode)

    // Routes accessible based on redirection from any main route || deep links
    object Math: Route("math")
    object Pravega: Route("pravega")
}


