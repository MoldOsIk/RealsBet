package quizz.sportss.realsbet.question.app.network

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

const val TAG = "WebViewModelCheckInfo"

class SportViewModel :ViewModel(){

    lateinit var remote: FirebaseRemoteConfig
    val linka = MutableStateFlow("noUrl")
    fun fireInit(){
        remote = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remote.setConfigSettingsAsync(configSettings)
        remote.fetchAndActivate()
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Log.d(TAG, "Config params updated: $updated")
                    Log.d(TAG, "url: ${remote.getString("url")}")
                    Log.d(TAG, "Fetch and activate succeeded")
                    CoroutineScope(Dispatchers.Default).launch {
                        linka.emit(remote.getString("viewurl"))
                    }

                } else {
                    Log.d(TAG, "Fetch failed")
                    CoroutineScope(Dispatchers.Default).launch {
                        linka.emit("")
                    }
                }
            }

    }

}