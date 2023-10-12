package com.quizofsport.staybetter.forever.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quizofsport.staybetter.forever.R
import com.quizofsport.staybetter.forever.common.but_sport
import com.quizofsport.staybetter.forever.ui.theme.background
import com.quizofsport.staybetter.forever.ui.theme.black
import com.quizofsport.staybetter.forever.ui.theme.defBasketMainColor
import com.quizofsport.staybetter.forever.ui.theme.green
import com.quizofsport.staybetter.forever.ui.theme.defBasketLightColor
import com.quizofsport.staybetter.forever.ui.theme.defFootballLightColor
import com.quizofsport.staybetter.forever.ui.theme.defFootballMainColor
import com.quizofsport.staybetter.forever.ui.theme.defHockeyLightColor
import com.quizofsport.staybetter.forever.ui.theme.defHockeyMainColor
import com.quizofsport.staybetter.forever.ui.theme.red
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun QuizQuestion(
    answers: List<String>,
    rightAnswer: String,
    answersState: MutableState<Int>,
    goNext: (answersCount: Int) -> Unit,
    goEnd: (answersCount: Int) -> Unit,
    question: String,
    indexOfQuestion: Int,
    topic: Int
) {
    Box(
        Modifier
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)

        ) {

            val key = remember {
                mutableStateOf(0)
            }
            val rightAnswerNumber = remember(key.value) {
                answers.indexOf(rightAnswer) + 1
            }
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
            val scope = rememberCoroutineScope()
            val selectedAnswer = remember(key.value) { mutableStateOf<Int?>(null) }
            val selectionEnabled = remember(key.value) { mutableStateOf(true) }
            val millisecondsLast = remember(key.value) { Animatable(60000f) }
            val coolBack = remember {
                Modifier
                    .background(
                        brush = Brush.verticalGradient(listOf(defColor, lightColor)),
                        shape = RoundedCornerShape(20)
                    )
                    .border(1.4.dp, black, shape = RoundedCornerShape(20))
                    .padding(horizontal = 16.dp)
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    text = "${indexOfQuestion}/10",
                    color = black,
                    fontSize = 38.sp,
                    modifier = Modifier.then(coolBack)
                )
            }
            LaunchedEffect(key.value) {
                millisecondsLast.animateTo(0f, tween(10000, easing = LinearEasing))
                if(millisecondsLast.value == 0f)
                goEnd.invoke(answersState.value)
            }


            LaunchedEffect(key1 = selectedAnswer.value) {
                delay(2000)
                if (selectedAnswer.value != null) {

                    selectionEnabled.value = false
                    if (selectedAnswer.value == rightAnswerNumber - 1)
                        answersState.value++

                    delay(2000)
                    withContext(Dispatchers.Main) {

                        goNext.invoke(answersState.value)
                        key.value++
                    }

                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                LinearProgressIndicator(
                    progress = millisecondsLast.value / 60000f,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .height(12.dp)
                        .clip(
                            RoundedCornerShape(40)
                        ),
                    color = defColor, trackColor = Color.White
                )

            }

            LazyColumn(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = question, fontSize = 18.sp, color = black, modifier = Modifier
                            .background(
                                brush = Brush.verticalGradient(listOf(defColor, lightColor)),
                                shape = RoundedCornerShape(20)
                            )
                            .border(1.4.dp, black, shape = RoundedCornerShape(20))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
                items(answers.size) { index ->
                    val answer = answers[index]
                    selectionEnabled.value
                    val boxColor = when {


                        selectedAnswer.value == rightAnswerNumber - 1 && rightAnswerNumber - 1 == index && !selectionEnabled.value -> green
                        index == rightAnswerNumber - 1 && !selectionEnabled.value -> green
                        selectedAnswer.value == index && rightAnswerNumber - 1 != index && !selectionEnabled.value -> red
                        selectedAnswer.value == index -> defColor
                        else -> background
                    }

                    val textColor = when {
                        selectedAnswer.value == index -> background
                        rightAnswerNumber - 1 == index && !selectionEnabled.value -> background
                        else -> black
                    }
                    Note(onClick = {
                        if (selectionEnabled.value) {
                            selectedAnswer.value = index
                        }
                    }, textColor = textColor, answer = answer, boxColor = boxColor)

                }
                item {
                    but_sport(
                        onClick = {
                            scope.launch { millisecondsLast.snapTo(60000f) }
                            scope.launch { key.value++ }
                            goNext.invoke(answersState.value)
                        },
                        lightCr = lightColor,
                        defCr = defColor,
                    ) {
                        Text(
                            text = stringResource(id = R.string.skip),
                            fontSize = 24.sp,
                            color = black,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                            fontFamily = FontFamily(Font(R.font.poppins))
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Note(onClick: () -> Unit, textColor: Color, answer: String, boxColor: Color) {
    Box(
        modifier = Modifier

            .fillMaxWidth()
            .height(100.dp)
            .border(
                width = 1.dp,
                color = Color.DarkGray,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(shape = RoundedCornerShape(12.dp))
            .clickable {
                onClick.invoke()
            }
            .background(boxColor)
    ) {
        Text(
            color = textColor,
            text = answer,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center)
        )
    }
}