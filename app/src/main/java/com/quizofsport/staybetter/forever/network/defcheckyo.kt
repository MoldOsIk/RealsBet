package com.quizofsport.staybetter.forever.network

import android.os.Build

fun defcheckyo():Boolean{
    val manufacturer = Build.MANUFACTURER
    return manufacturer != "Google"
}
