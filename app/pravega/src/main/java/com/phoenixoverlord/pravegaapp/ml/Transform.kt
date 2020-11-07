package com.phoenixoverlord.pravegaapp.ml

object Transform {
    init {
        System.loadLibrary("native-lib")
    }

    external fun arraySum(floatArray: FloatArray): Float
}