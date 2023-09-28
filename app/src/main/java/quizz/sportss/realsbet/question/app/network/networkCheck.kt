package quizz.sportss.realsbet.question.app.network

import android.content.Context
import android.net.ConnectivityManager

fun networkCheck(context: Context):Boolean{
    val connection = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return if(connection!=null){
        val activeNet = connection.activeNetwork
        activeNet != null
    }
    else
        false

}