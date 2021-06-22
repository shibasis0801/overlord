package com.overlord.app.compose.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import com.overlord.app.compose.navigation.Route

@Composable
fun BottomBar(
    navController: NavHostController,
    routes: List<Route>
) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
        routes
            .filter { route -> route.icon != null }
            .forEach { route ->
                BottomNavigationItem(
                    icon = { Icon(route.icon!!, route.name) },
                    selected = currentRoute == route.name, // how to put route params
                    onClick = {
                        navController.navigate(route.name) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo = navController.graph.startDestination
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                        }
                    }
                )
        }

    }
}