package quizz.sportss.realsbet.question.app

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.webkit.CookieManager
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import quizz.sportss.realsbet.question.app.network.SportViewModel
import quizz.sportss.realsbet.question.app.network.cond_met
import quizz.sportss.realsbet.question.app.network.defcheckyo
import quizz.sportss.realsbet.question.app.network.networkCheck
import quizz.sportss.realsbet.question.app.network.setupAR


const val TAGGY = "tagSportRealsbe"
class SportWebActivity : AppCompatActivity() {

    private var uplMess:MutableState<ValueCallback<Array<Uri>>?> = mutableStateOf(null)
    private var activityResLauncher: MutableState<ActivityResultLauncher<Intent>?> = mutableStateOf(null)
    lateinit var sportWeb:WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sport_web)
        val sportVM = SportViewModel()
        sportWeb = findViewById(R.id.sportweb)
        val cs_scope = CoroutineScope(Dispatchers.Default)
        val delay_scope = CoroutineScope(Dispatchers.Default)
        val game_intent = Intent(this, MainActivity::class.java)
        val openGame = {
            startActivity(game_intent)
            finish()
        }
        val prefs  = getSharedPreferences("sportik", Context.MODE_PRIVATE)
        val ready_url = prefs.getString("ready","noUrl").toString()
        cs_scope.launch {sportVM.linka.emit(ready_url)}

        sportVM.fireInit()
        setupSportWB(sportWeb)
        setupAR(activityResLauncher,this,uplMess)

        if(networkCheck(this)){
            if(cond_met(this)){
                if(defcheckyo()){
                    cs_scope.launch {
                        sportVM.linka.collectLatest { url ->
                            Log.d(TAGGY, "url = $url")
                            delay(500)
                            withContext(Dispatchers.Main) {
                                if ( url != "noUrl" && !url.isNullOrBlank() ) {
                                    prefs.edit().putString("ready",url).apply()
                                    sportWeb.loadUrl(url)
                                }
                                if (url.isBlank()) {
                                    Log.d(TAGGY,"url is blank")
                                    openGame.invoke()
                                }
                            }
                        }
                    }
                }else {
                    Log.d(TAGGY,"device fail")
                    delay_scope.launch {
                        delay(1500)
                        withContext(Dispatchers.Main) {
                            openGame.invoke()
                        }
                    }

                }
            } else {
                Log.d(TAGGY,"con_none_met")
                delay_scope.launch {
                    delay(1500)
                    withContext(Dispatchers.Main) {
                        openGame.invoke()
                    }
                }
            }
        }
        else {
            Log.d(TAGGY,"no network")
            delay_scope.launch {
                delay(1500)
                withContext(Dispatchers.Main) {
                    openGame.invoke()
                }
            }
        }
    }


    override fun onBackPressed() {
        if(sportWeb.canGoBack())
            sportWeb.goBack()
    }

    fun setupSportWB(sportWeb: WebView) {
        CookieManager.getInstance().setAcceptCookie(true)
        CookieManager.getInstance().setAcceptThirdPartyCookies(sportWeb, true)
        sportWeb.settings.apply {
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            domStorageEnabled = true
            useWideViewPort = true
            loadWithOverviewMode = true
            builtInZoomControls = true
            displayZoomControls = false

        }
        sportWeb.webViewClient = object : WebViewClient() {

            override fun onPageFinished(sportV: WebView?, url: String?) {
                super.onPageFinished(sportV, url)
                if (sportV != null) {
                    sportV.visibility = View.VISIBLE
                }
            }
        }
        sportWeb.requestFocus(View.FOCUS_DOWN)
        sportWeb.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        sportWeb.webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(wv: WebView?,filePathCallback: ValueCallback<Array<Uri>>?, fileChooserParams: FileChooserParams?
            ): Boolean {
                if (uplMess != null) {
                uplMess.value?.onReceiveValue(null)
                uplMess.value = null
            }
                uplMess.value = filePathCallback
                val spIntnt = Intent(Intent.ACTION_GET_CONTENT)
                spIntnt.addCategory(Intent.CATEGORY_OPENABLE)
                spIntnt.type = "*/*"
                activityResLauncher.value?.launch(Intent.createChooser(spIntnt, "File Browser"))
                return true
            }
        }


        sportWeb.setOnKeyListener(View.OnKeyListener { k, co_de, event ->
            if (co_de == KeyEvent.KEYCODE_BACK
                && event.action == MotionEvent.ACTION_UP
                && sportWeb.canGoBack()
            ) {
                sportWeb.goBack()
                return@OnKeyListener true
            }
            false
        })

    }

}