package com.overlord.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OverlordLabApplication: Application() {

}

/*
Make one package for each experiment.

app package -> Holder / start screen for everything
games package -> Games
    tictactoe ->
    ludo ->
ml package
 */