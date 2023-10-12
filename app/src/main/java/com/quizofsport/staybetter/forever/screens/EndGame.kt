package com.quizofsport.staybetter.forever.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.quizofsport.staybetter.forever.R
import com.quizofsport.staybetter.forever.common.button_
import com.quizofsport.staybetter.forever.common.text
import com.quizofsport.staybetter.forever.ui.theme.black
import com.quizofsport.staybetter.forever.ui.theme.defBasketLightColor
import com.quizofsport.staybetter.forever.ui.theme.defBasketMainColor
import com.quizofsport.staybetter.forever.ui.theme.defFootballLightColor
import com.quizofsport.staybetter.forever.ui.theme.defFootballMainColor
import com.quizofsport.staybetter.forever.ui.theme.defHockeyLightColor
import com.quizofsport.staybetter.forever.ui.theme.defHockeyMainColor

@Composable
fun EndGame(score: Int, bet: Int, goPreGame:()-> Unit ,balance:MutableState<Int>, topic : Int) {

    val lightColor = remember {
        when (topic) {
            0 -> defFootballLightColor
            1 -> defBasketLightColor
            else -> defHockeyLightColor
        }
    }
    val defColor = remember {
        when (topic) {
            0 -> defFootballMainColor
            1 -> defBasketMainColor
            else -> defHockeyMainColor
        }
    }
    val coolback = remember {
        Modifier
            .background(brush = Brush.verticalGradient(listOf(defColor,lightColor)),
                shape = RoundedCornerShape(20))
            .border(1.4.dp, black, shape = RoundedCornerShape(20))
            .padding(horizontal = 16.dp)
    }

    val newMoney = remember {
        mutableStateOf(0)
    }
    LaunchedEffect(key1 = topic.hashCode()){
        if(bet<=score && score!=0) {
            newMoney.value=200*(score-bet+1)
            Log.d(  "jsaifjisafi", "UPDATE BALANCE!!!")
            balance.value+=newMoney.value
        }
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        Image(painter = painterResource(id =
        when(topic){
            0-> R.drawable.football
            1->R.drawable.backettopic
            else->R.drawable.hockeytopic
        }
        ), contentDescription = "" ,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier =    Modifier.then(coolback).padding(16.dp)) {
            text(text = "You scored:", size = 28, color = Black)
            text(text = "$score/10", 26, color = Black)
            text(text = "Your predict was:", 28, color = Black)
            text(text = "${bet}/10", 26, color = Black)
            text("You won:", 28, color = Black)
            text("${newMoney.value}", 26, color = Black)
        }
        button_(onClick = { goPreGame() }, modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 16.dp)) {
            text(text = "Back", size = 24, color = Black, modifier = Modifier.then(coolback))
        }

    }
    BackHandler {
        goPreGame()
    }
}