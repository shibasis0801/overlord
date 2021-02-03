package com.overlord.app.compose.pages.dev

import HelloWorld
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.overlord.app.main.AmbientActivity
import com.phoenixoverlord.pravegaapp.cloud.firebase.extensions.Firebase
import com.phoenixoverlord.pravegaapp.framework.BaseActivity
import com.phoenixoverlord.pravegaapp.toast

val realtimeDB = Firebase.realtime
val firestoreDB = Firebase.firestore

fun testFirebase() {
    realtimeDB.child("/dev-screen")
        .setValue("Shibasis Patnaik")
}


// Inside commons repo, have one style for all 3 apps
@Preview
@Composable
fun DevScreen(navController: NavController) {
    val message = HelloWorld().getMessage()
    val activity = AmbientActivity.current
    var textState by remember { mutableStateOf("") }
    //  Create an observable ArrayList
    var regimenList by remember { mutableStateOf(listOf<String>()) }

    Column(Modifier.padding(16.dp).fillMaxSize()) {
        Text(
            text = "Exercise Planner",
            textAlign = TextAlign.Center,
            fontSize = TextUnit.Sp(32),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.fillMaxWidth()
        )

//        Message from KotlinMultiplatform
        Text(message)

        Text(
            text = "Enter your regimen titles",
            fontSize = TextUnit.Sp(24),
            fontWeight = FontWeight.SemiBold
        )

        regimenList.forEach {
            Text(it)
        }


        TextField(
            value = textState,
            onValueChange = { textState = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        Button(onClick = {
            activity.toast("You entered $textState")
            regimenList = regimenList + textState
            textState = ""
        }) {
            Text(text = "Done")
        }


    }
}