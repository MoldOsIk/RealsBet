package com.quizofsport.staybetter.forever.screens

import android.content.Context
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.quizofsport.staybetter.forever.R
import com.quizofsport.staybetter.forever.common.SliderWithStep
import com.quizofsport.staybetter.forever.common.but_sport
import com.quizofsport.staybetter.forever.common.button_
import com.quizofsport.staybetter.forever.common.text
import com.quizofsport.staybetter.forever.ui.theme.black
import com.quizofsport.staybetter.forever.ui.theme.defBasketLightColor
import com.quizofsport.staybetter.forever.ui.theme.defBasketMainColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun pregamescreen(goGame:(topic: Int, bet:Int) -> Unit,balance:Int ) {
    val images = listOf(
        R.drawable.footbal,
        R.drawable.basketball,
        R.drawable.hockey
    )
    val fullWidth = LocalConfiguration.current.screenWidthDp
    val fullHeight = LocalConfiguration.current.screenHeightDp
        val pagerState = rememberPagerState(pageCount = {
            3
        })
    var bet by remember{
        mutableStateOf(0)
    }
    val infiniteTransition = rememberInfiniteTransition()
    val infiniteRepeatableSpec = remember {
        InfiniteRepeatableSpec<Float>(
            tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    }
    val scaleMediumText = infiniteTransition.animateFloat(
        initialValue = 0.93f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatableSpec,
        label = ""
    )
    val scaleTopBox = infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1f,
        animationSpec = infiniteRepeatableSpec,
        label = ""
    )
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        Image(painter = painterResource(id = R.drawable.bg), contentDescription ="",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)
        Box(modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(top = (fullHeight * 0.05).dp)
            .scale(scaleTopBox.value))
        {
            but_sport(onClick = {  }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    text("Your game points:",20,color= black)
                    text("${balance}",  20,color= black)

                }
            }


        }


        HorizontalPager(state = pagerState) { page ->
            val topic = page % images.size
            button_(onClick = { goGame(topic, bet) }) {
                Image(
                    painter = painterResource(id = images[page % images.size]),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = (fullWidth * 0.2).dp)
                        .clip(RoundedCornerShape(30f))
                        .border(width = 4.dp, color = Color.White),
                )

            }



        }
            text(
                "Select the topic of the questions:", 20,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .scale(scaleMediumText.value)
                    .padding(top = (fullHeight * 0.215).dp)
                    .background(brush = Brush.verticalGradient(listOf(defBasketMainColor, defBasketLightColor)
                    ), shape = RoundedCornerShape(20))
                    .border(1.4.dp, black,shape = RoundedCornerShape(20))
                    .padding(horizontal =  16.dp).padding( top = 4.dp), black
            )
        Box(modifier = Modifier.align(Alignment.BottomCenter).scale(scaleTopBox.value) )
        {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                text("Place you score:", 20,color= black,
                    modifier = Modifier
                        .background(brush = Brush.verticalGradient(listOf(defBasketMainColor, defBasketLightColor)), shape = RoundedCornerShape(20))
                        .border(1.4.dp, black, shape = RoundedCornerShape(20))
                        .padding(horizontal = 16.dp)
                        .padding(top = 4.dp) )
                text("${bet}", 20,color = black,
                    modifier = Modifier
                        .background(brush = Brush.verticalGradient(listOf(defBasketMainColor, defBasketLightColor)), shape = RoundedCornerShape(20))
                        .border(1.4.dp, black, shape = RoundedCornerShape(20))
                        .padding(horizontal = 16.dp)
                        .padding(top = 4.dp) )
                SliderWithStep(
                    value = bet.toFloat(),
                    onValueChange = {
                        bet = it.toInt()
                    },
                    minValue = 0f,
                    maxValue = 10f,
                    step = 1f,
                    sliderWidth = (fullWidth*0.75).dp,
                    sliderHeight = (fullHeight*0.15).dp
                )
            }
        }
    }




}