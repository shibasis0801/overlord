package com.overlord.app.pages.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Preview
@Composable
fun GameScreen(navController: NavController) {
    Column {
        Text(text = "Game Screen")
        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Go Home")
        }
    }
}