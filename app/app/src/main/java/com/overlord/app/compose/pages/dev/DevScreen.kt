package com.overlord.app.compose.pages.dev

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.overlord.app.compose.AmbientActivity
import com.overlord.app.compose.AmbientViewModel
import com.overlord.app.compose.navigation.Route
import com.overlord.app.react.WebActivity
import com.phoenixoverlord.pravegaapp.cloud.firebase.extensions.Firebase
import com.phoenixoverlord.pravegaapp.framework.extensions.finishAndStart
import com.phoenixoverlord.pravegaapp.toast

val realtimeDB = Firebase.realtime
// memory leak, fix later
val firestoreDB = Firebase.firestore

fun testFirebase() {
    realtimeDB.child("/dev-screen")
        .setValue("Shibasis Patnaik")
}


// Inside commons repo, have one style for all 3 apps
@Composable
fun DevScreen(navController: NavController) {
    val message = "HelloWorld().getMessage()"
    val activity = AmbientActivity.current
    val viewModel = AmbientViewModel.current
    val getEcho by viewModel.getEcho.observeAsState("")
    val postEcho by viewModel.postEcho.observeAsState("")

    var textState by remember { mutableStateOf("") }
    //  Create an observable ArrayList
    var regimenList by remember { mutableStateOf(listOf<String>()) }

    Column(Modifier.padding(16.dp).fillMaxSize()) {
        Text(
            text = "Exercise Planner",
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.fillMaxWidth()
        )

//        Message from KotlinMultiplatform
        Text(message)

        Text(
            text = "Enter your regimen titles",
            fontSize = 24.sp,
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


        Button(modifier = Modifier.padding(8.dp), onClick = {
            viewModel.getResponse()
        }) {
            Text(text = getEcho)
        }

        Button(modifier = Modifier.padding(8.dp), onClick = {
            viewModel.postResponse()
        }) {
            Text(text = postEcho)
        }


        Button(modifier = Modifier.padding(8.dp), onClick = {
            activity.finishAndStart(WebActivity::class.java)
        }) {
            Text(text = "Open Website")
        }

        Button(modifier = Modifier.padding(8.dp), onClick = {
            navController.navigate(Route.Pravega.name)
        }) {
            Text(text = "Start Pravega")
        }
    }
}