package quizz.sportss.realsbet.question.app.network

import android.os.Build

fun defcheckyo():Boolean{
    val manufacturer = Build.MANUFACTURER
    return manufacturer != "Google"
}
