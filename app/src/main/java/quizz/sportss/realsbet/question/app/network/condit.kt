package quizz.sportss.realsbet.question.app.network

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.telephony.TelephonyManager
import android.webkit.ValueCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.MutableState

private fun the_sim_checky(context: Context): Boolean {
    val _mange = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    return _mange.simState != TelephonyManager.SIM_STATE_ABSENT
}

private fun airMcheck(context: Context): Boolean {
    return Settings.Global.getInt(
        context.contentResolver,
        Settings.Global.AIRPLANE_MODE_ON,
        0
    ) != 0
}

private fun devOptCheck(cntxt: Context): Boolean {

    return Settings.Secure.getInt(
        cntxt.contentResolver,
        Settings.Global.DEVELOPMENT_SETTINGS_ENABLED,
        0
    ) != 0
}

fun cond_met(cxt: Context): Boolean {
    return the_sim_checky(cxt) && !airMcheck(cxt) && !devOptCheck(cxt)
}
 fun setupAR(
    activityResultLauncher: MutableState<ActivityResultLauncher<Intent>?>,
    activity: AppCompatActivity,
    mUploadMessage: MutableState<ValueCallback<Array<Uri>>?>
) {
    activityResultLauncher.value = activity.activityResultRegistry.register(
        "UploadFile", ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            if (result.data != null) {
                val uri: Uri? = result.data?.data
                if (uri != null && mUploadMessage != null) {
                    mUploadMessage.value?.onReceiveValue(arrayOf(uri))
                    mUploadMessage.value = null
                }
            }
        } else {
            mUploadMessage.value?.onReceiveValue(null)
            mUploadMessage.value = null
        }
    }
}
