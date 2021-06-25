package com.overlord.app.activities

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Toast
import com.phoenixoverlord.pravegaapp.framework.BaseActivity

class WebActivity : BaseActivity() {
    var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WebView.setWebContentsDebuggingEnabled(true);

        webView = WebView(this)
        setContentView(webView)

        webView!!.addJavascriptInterface(WebAppInterface(this), "android")

        withPermissions(
            Manifest.permission.INTERNET,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.BLUETOOTH
        ).execute {
            webView?.apply {

                settings.apply {
                    mediaPlaybackRequiresUserGesture = false
                    javaScriptEnabled = true
                    allowFileAccessFromFileURLs = true
                    allowUniversalAccessFromFileURLs = true

                }

                webChromeClient = object : WebChromeClient() {
                    override fun onPermissionRequest(request: PermissionRequest?) {
                        request?.grant(request.resources)
                    }
                }
                val dev = "http://192.168.0.217:8000"
                loadUrl(dev)
            }
        }

    }
}

class WebAppInterface(private val context: Context) {
    @JavascriptInterface
    fun showToast() {
        Toast.makeText(context, "Hello From Native", Toast.LENGTH_SHORT).show()
    }
}