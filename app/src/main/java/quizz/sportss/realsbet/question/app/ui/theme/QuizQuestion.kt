package quizz.sportss.realsbet.question.app.ui.theme

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.CncBet.jogar.a123.ui.theme.porpg.text
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import quizz.sportss.realsbet.question.app.R

@Composable
fun QuizQuestion(
    answers: List<String>,
    rightAnswer: String,
    answersState: MutableState<Int>,
    goNext: (answersCount:Int) -> Unit,
    goEnd: (answersCount: Int) -> Unit,
    question: String,
)
{

    Box(
        Modifier
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier
                .fillMaxSize()

        ) {

            val key = remember {
                mutableStateOf(0)
            }
            val rightAnswerNumber = remember(key.value) {
                answers.indexOf(rightAnswer)+1
            }
            val selectedAnswer = remember(key.value) { mutableStateOf<Int?>(null) }
            val selectionEnabled = remember(key.value) { mutableStateOf(true) }
            val millisecondsLast = remember(key.value){ Animatable(60000f) }

            val questionNumber = remember {
                mutableStateOf(0)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                ,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Level 1",
                    modifier = Modifier,
                    color = secondary,
                    fontSize = 22.sp
                )
                Text(
                    text = "${questionNumber.value}/10",
                    color = green,
                    fontSize = 38.sp
                )
            }
            LaunchedEffect(key.value){
                millisecondsLast.animateTo(0f, tween(10000, easing = LinearEasing))
                goEnd.invoke(answersState.value)
            }

            LaunchedEffect(key1 = selectedAnswer.value){
                delay(2000)
                if(selectedAnswer.value!=null){

                    selectionEnabled.value = false
                    delay(2000)
                    withContext(Dispatchers.Main){
                        if(selectedAnswer.value == rightAnswerNumber-1)
                            answersState.value++
                        goNext.invoke(answersState.value)
                        key.value ++
                    }

                }
            }

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start) {
                LinearProgressIndicator(
                    progress = millisecondsLast.value/60000f,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .height(10.dp)
                        .clip(
                            RoundedCornerShape(40)
                        ),
                    color = green, trackColor = Color.White
                )

            }


            LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                item {
                    Text(text = question, fontSize = 18.sp, color = secondary,modifier = Modifier.padding(vertical =  16.dp))
                }
                items(answers.size) { index ->
                    val answer = answers[index]
                    selectionEnabled.value
                    val boxColor = when {


                        selectedAnswer.value == rightAnswerNumber -1 && rightAnswerNumber -1 == index && !selectionEnabled.value -> green
                        index == rightAnswerNumber-1 && !selectionEnabled.value -> green
                        selectedAnswer.value == index && rightAnswerNumber - 1 != index  && !selectionEnabled.value -> red
                        selectedAnswer.value == index -> secondary
                        else -> background
                    }

                    val textColor = when {
                        selectedAnswer.value == index -> background
                        rightAnswerNumber - 1  == index && !selectionEnabled.value -> background
                        else -> secondary
                    }
                    Note(onClick = {
                        if(selectionEnabled.value) {
                            selectedAnswer.value = index
                        }
                    }, textColor = textColor, answer = answer , boxColor =boxColor )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}
@Composable
fun Note(onClick:()->Unit,textColor:Color, answer: String,boxColor: Color){
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