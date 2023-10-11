package com.quizofsport.staybetter.forever.screens

import android.view.ViewGroup
import androidx.compose.animation.core.RepeatMode
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.quizofsport.staybetter.forever.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(goForward:()->Unit) {
    LaunchedEffect(key1 = Unit, block = {
        delay(1200)
        goForward.invoke()})
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.splash_back), contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Text(text = stringResource(id = R.string.loading),modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom =  48.dp, end = 48.dp),color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
        AndroidView(factory = {
            val lottie = LottieAnimationView(it)
            lottie.apply {
                layoutParams = ViewGroup.LayoutParams(144, 168)
                repeatCount = 100
                repeatMode = LottieDrawable.RESTART
                setAnimation(R.raw.animt)
                 playAnimation()
            }

                              },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .offset(68.dp, 0.dp)
                .size(144.dp, 168.dp)
        )


    }
}