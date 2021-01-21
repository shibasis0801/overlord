package com.overlord.app.main

import android.app.Application
import com.facebook.soloader.SoLoader
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OverlordLabApplication : Application() {}

/*
Make one package for each experiment.

app package -> Holder / start screen for everything
games package -> Games
    tictactoe ->
    ludo ->
ml package
 */