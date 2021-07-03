package com.overlord.app.main

import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.overlord.app.R
import com.phoenixoverlord.pravegaapp.extensions.logDebug
import com.phoenixoverlord.pravegaapp.framework.BaseActivity
import com.phoenixoverlord.pravegaapp.mechanisms.base.AnalyzerComponent
import com.phoenixoverlord.pravegaapp.mechanisms.compounds.CameraComponent
import com.phoenixoverlord.pravegaapp.mechanisms.elements.StorageComponent
import com.phoenixoverlord.pravegaapp.mechanisms.helpers.buildCapture
import com.phoenixoverlord.pravegaapp.mechanisms.helpers.buildPreview
import com.phoenixoverlord.pravegaapp.ml.analysers.vision.LabellingAnalyser
import dagger.hilt.android.AndroidEntryPoint
import org.liquidplayer.javascript.JSContext
import org.liquidplayer.javascript.JSFunction
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    // Only use these values inside the activity / viewmodel. Not in compose (not through Ambients)
    @Inject
    lateinit var realtimeDB: DatabaseReference

    @Inject
    lateinit var firestoreDB: FirebaseFirestore

    @Inject
    lateinit var storageDB: StorageReference

    @Inject
    lateinit var auth: FirebaseAuth

    private val mainViewModel by viewModels<MainViewModel>()

    lateinit var camera: CameraComponent
    lateinit var storage: StorageComponent
    lateinit var analyzer: AnalyzerComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        analyzer = LabellingAnalyser(this, 0.7f)
        storage = StorageComponent(this)
        camera = CameraComponent(this, storage, analyzer)

        useComponents(
            analyzer,
            storage,
            camera
        )

        val preview = buildPreview(findViewById(R.id.viewFinder))
        val capture = buildCapture()
        camera.start(preview, capture)
        findViewById<Button>(R.id.camera_capture_button).setOnClickListener { camera.savePicture() }

        testDrive()
    }

    fun testDrive() {
//        val webView = WebView(this)
//        webView.settings.javaScriptEnabled = true
//        webView.evaluateJavascript("function(){ return 'Running Pravega' }();") {
//            logDebug("Shibasis Patnaik webview $it")
//        }
        val JavaScript = JSContext()

        val log: JSFunction = object : JSFunction(JavaScript, "log") {
            fun log(tag: String, content: String) {
                logDebug(tag, content)
            }
        }
        JavaScript.property("log", log)
        JavaScript.evaluateScript("""
            function helloJS() {
                return "Hello using liquidCore";
            }
            const param = helloJS();
            log("Shibasis", param);
        """.trimIndent())
    }
}

