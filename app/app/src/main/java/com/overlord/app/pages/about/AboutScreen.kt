package com.overlord.app.pages.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Preview
@Composable
fun AboutScreen(navController: NavController) {
    Column {
        Text(text = "About Screen")
        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Go Home")
        }
    }
}