package com.overlord.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import com.overlord.app.ui.OverlordAndroidTheme
import com.phoenixoverlord.pravegaapp.auth.authenticateAnonymously
import com.phoenixoverlord.pravegaapp.framework.extensions.finishAndStart
import com.phoenixoverlord.pravegaapp.toast

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OverlordAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
        authenticateAnonymously { success ->
            if (success) {
                finishAndStart(MainActivity::class.java)
            }
            else {
                toast("Error authenticating")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    BasicText(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OverlordAndroidTheme {
        Greeting("Android")
    }
}