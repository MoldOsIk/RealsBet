package com.quizofsport.staybetter.forever.screens

import android.app.Activity
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
import androidx.compose.animation.core.animateFloat
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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.quizofsport.staybetter.forever.HOMESCREEN
import com.quizofsport.staybetter.forever.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.quizofsport.staybetter.forever.common.but_sport
import com.quizofsport.staybetter.forever.common.text
import com.quizofsport.staybetter.forever.ui.theme.black
import com.quizofsport.staybetter.forever.ui.theme.metal

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun homescreen(goPreGame:() -> Unit,navController: NavController) {

    val context = LocalContext.current as Activity
    val infiniteTransition = rememberInfiniteTransition()
    val infiniteRepeatableSpec = remember {
        InfiniteRepeatableSpec<Float>(tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    }
    val smooth = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 16f,
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
        Text(text = stringResource(id = R.string.app_name), fontSize = 32.sp,color = metal,
            fontWeight = FontWeight.Bold, letterSpacing = 3.2.sp,modifier =
            Modifier.align(Alignment.TopCenter).padding(top = 8.dp).graphicsLayer { rotationX = -25f
            },
            lineHeight = 26.sp,
            fontFamily = FontFamily(Font(R.font.poppins))
            )
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
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 48.dp)
            ){
                item("START GAME"){
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
                            text(text = stringResource(id = R.string.rules),
                                size = 18,color = black, fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center)

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
                .padding(bottom = 64.dp)
                .offset(0.dp, (smooth.value * 5 / 2).dp),
                enter = fadeIn(), exit = fadeOut()) {
                AndroidView(
                    factory = {LottieAnimationView(it).apply {
                        layoutParams = ViewGroup.LayoutParams(250, 250)
                        repeatMode = LottieDrawable.RESTART
                        repeatCount = 1000
                        setAnimation(R.raw.ball)
                        playAnimation()
                    }
                    }

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
        else if(navController.currentBackStackEntry?.destination?.route == HOMESCREEN){
            context.finishAffinity()

        }
        else
        {
            navController.popBackStack()
        }


    }
}