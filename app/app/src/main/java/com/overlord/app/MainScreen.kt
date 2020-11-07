package com.overlord.app

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.viewinterop.viewModel

@Composable
fun MainScreen(mainViewModel: MainViewModel = viewModel()) {
    val name: String by mainViewModel.name.observeAsState("")

    Column {
        Text(text = name)
    }
}

