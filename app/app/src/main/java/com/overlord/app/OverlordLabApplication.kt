package com.overlord.app

import android.app.Application
import com.facebook.soloader.SoLoader
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OverlordLabApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this,  false)
    }
}


/*
Make one package for each experiment.

app package -> Holder / start screen for everything
games package -> Games
    tictactoe ->
    ludo ->
ml package
 */