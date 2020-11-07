package com.phoenixoverlord.pravegaapp.audio

import android.speech.tts.TextToSpeech
import com.phoenixoverlord.pravegaapp.extensions.logDebug
import com.phoenixoverlord.pravegaapp.framework.BaseActivity
import com.phoenixoverlord.pravegaapp.toast

object EvaVoice {
    private var textToSpeech: TextToSpeech? = null

    fun onStop() {
        logDebug("STOPPING EVA", "")
        textToSpeech?.shutdown()
        textToSpeech = null
    }

    fun onStart(activity: BaseActivity) {
        logDebug("STARTING EVA", "")
        textToSpeech = TextToSpeech(activity, {
            val voice = textToSpeech?.voices?.find { it.name == "en-in-x-ahp-local" }
            if (voice == null) {
                activity.toast("Could not find voice")
            }
            else {
                activity.toast("Found voice")
            }
            textToSpeech?.voice = voice
        }, "com.google.android.tts")
    }

    fun speak(text: String) {
        textToSpeech?.apply {
            speak(text, TextToSpeech.QUEUE_ADD, null, "TEXT")
            playSilentUtterance(200, TextToSpeech.QUEUE_ADD, "SILENCE")
        }
        if (textToSpeech == null) {
            logDebug("EvaVoice","NULL")
        }
    }
}