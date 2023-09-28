package quizz.sportss.realsbet.question.app.ui.theme

import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.CncBet.jogar.a123.ui.theme.porpg.button_
import com.CncBet.jogar.a123.ui.theme.porpg.text
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import quizz.sportss.realsbet.question.app.R

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun homescreen(goPreGame:() -> Unit, goRules:() -> Unit, goPolicy:() -> Unit,navController: NavController) {

    val context = LocalContext.current
    val infiniteTransition = rememberInfiniteTransition()
    val infiniteRepeatableSpec = remember {
        InfiniteRepeatableSpec<Float>(tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    }
    val smooth = infiniteTransition.animateFloat(
        initialValue = 16f,
        targetValue = 32f,
        animationSpec = infiniteRepeatableSpec,
        label = ""
    )
    val showMenu = remember { mutableStateOf(false) }
    val showRules = remember { mutableStateOf(false) }
    val showRulesText = remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit){
        delay(300)
        showMenu.value = true
    }
    val cs = rememberCoroutineScope()
    val horAlignment = remember{
        Animatable(1f)
    }
    Box(modifier = Modifier.fillMaxSize())
    {
        Image(painter = painterResource(id = R.drawable.home_background), contentDescription ="",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)
        Box(Modifier.fillMaxSize()) {

        AnimatedVisibility(
            visible = showMenu.value,
            enter = slideInVertically { (it*1.1).toInt() }+ fadeIn(),
            exit = slideOutVertically { (it*1.1).toInt() }+ fadeOut(),
            modifier  = Modifier
                .align(BiasAlignment(horAlignment.value, -1f))
                .padding(16.dp)
                .padding(top = smooth.value.dp)

            ) {
            LazyColumn(horizontalAlignment = BiasAlignment.Horizontal(horAlignment.value),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()

            ){
                item("START GAME") {
                    AnimatedVisibility(visible =  !showRules.value ,
                        enter = fadeIn(tween(450))+ slideInVertically(tween(450)) { -(it*1.1).toInt() },
                        exit = fadeOut(tween(450))+slideOutVertically(tween(450)) {-(it*1.1).toInt()},
                        modifier = Modifier.animateItemPlacement(tween(450))) {
                        but_sport(onClick = {
                            cs.launch {
                                showMenu.value = false
                                delay(450)
                                goPreGame()
                            }
                        },modifier = Modifier.animateContentSize(tween(450))) {
                            text(text = "START GAME", size = 30,color = black)
                        }
                    }
                }


                item("RULES") {
                    but_sport(onClick = {
                        cs.launch {
                            horAlignment.animateTo(0f, tween(450))
                            showRules.value = true
                            delay(450)
                            showRulesText.value = true

                        }
                    }) {
                        text(text = "RULES", size = 30,color = black)
                    }
                }
                item("TEXT_RULES"){
                    AnimatedVisibility(visible = showRulesText.value,
                        enter = fadeIn(tween(450)), exit = fadeOut(tween(450)),
                        modifier = Modifier.animateItemPlacement(tween(450))) {
                        but_sport(onClick = { },all = 16.dp) {
                            text(text = stringResource(id = R.string.rules), size = 18,color = black)

                        }
                    }
                }
                item("POLICY") {
                    AnimatedVisibility(visible =  !showRules.value ,modifier = Modifier.animateItemPlacement(tween(450)),
                        enter = fadeIn(tween(450))+fadeIn()+slideInVertically(tween(450)) {(it*1.1).toInt()},
                        exit = fadeOut(tween(450))+slideOutVertically(tween(450)) {(it*1.1).toInt()}) {
                        but_sport(onClick = {
                            cs.launch {
                                showMenu.value = false
                                delay(450)
                                goPolicy()
                            }
                        },modifier = Modifier.animateContentSize(tween(450))
                        ) {
                            text(text = "POLICY", size = 30, color = black)
                        }
                    }
                }

                item("EXIT") {
                    AnimatedVisibility(visible =  !showRules.value ,modifier = Modifier.animateItemPlacement(tween(450)),
                        enter = fadeIn(tween(450))+slideInVertically(tween(450)) {(it*1.1).toInt()},
                        exit = fadeOut(tween(450))+slideOutVertically(tween(450)) {(it*1.1).toInt()}) {
                        but_sport(onClick = {
                            cs.launch {
                                showMenu.value = false
                                delay(450)
                                (context as? ComponentActivity)?.finish()
                            }
                        },modifier = Modifier.animateContentSize(tween(450))) {
                            text(text = "EXIT", size = 30, color = black)
                        }
                    }
                }


            }

        }
            AnimatedVisibility(visible = showRulesText.value, modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 64.dp)) {
                AndroidView(
                    factory = {LottieAnimationView(it).apply {
                        layoutParams = ViewGroup.LayoutParams(250, 250)
                        repeatMode = LottieDrawable.RESTART
                        repeatCount = 1000
                        setAnimation(R.raw.ball)
                        playAnimation()
                    }
                    },

                )
            }

        }

    }
    BackHandler {
        if(showRules.value){
            cs.launch {
                showRulesText.value = false
                delay(450)
                showRules.value = false
                delay(450)
                horAlignment.animateTo(1f, tween(450))
            }

        }

        else
            navController.popBackStack()

    }
}