package com.overlord.app.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController


@Preview
@Composable
fun ScreenOne(navController: NavController) {
    Column {
        BasicText(text = "ScreenOne")
        Button(onClick = { navController.navigateUp() }) {
            BasicText(text = "Go Home")
        }
    }
}

@Preview
@Composable
fun ScreenTwo(navController: NavController) {
    Column {
        BasicText(text = "ScreenTwo")
        Button(onClick = { navController.navigateUp() }) {
            BasicText(text = "Go Home")
        }
    }
}

@Preview
@Composable
fun HomeScreen(navController: NavController) {
    Column {
        BasicText(text = "HomeScreen")
        Button(onClick = { navController.navigate("screenone") }) {
            BasicText(text = "Go to screen one")
        }
        Button(onClick = { navController.navigate("screentwo") }) {
            BasicText(text = "Go to screen two")
        }
    }
}


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("screenone")  { ScreenOne(navController) }
        composable("screentwo") { ScreenTwo(navController) }
    }
}