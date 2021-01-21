package com.overlord.app.compose.pages.ml

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Preview
@Composable
fun MLScreen(navController: NavController) {
    Column {
        Text(text = "ML Screen")
        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Go Home")
        }
    }
}