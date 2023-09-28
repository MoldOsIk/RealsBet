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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    goLose: () -> Unit,
    goMenu: () -> Unit,
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

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(Color.White)
            )

            val rightAnswerNumber = remember {
                answers.indexOf(rightAnswer)+1
            }
            val selectedAnswer = remember { mutableStateOf<Int?>(null) }
            val selectionEnabled = remember { mutableStateOf(true) }
            val millisecondsLast = remember{ Animatable(60000f) }

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
                    color = Color.Gray,
                    fontSize = 22.sp
                )
                Text(
                    text = "${answersState.value}/10",
                    color = Color.Green,
                    fontSize = 38.sp
                )
            }
            LaunchedEffect(Unit){
                millisecondsLast.animateTo(0f, tween(60000, easing = LinearEasing))
                goLose.invoke()
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
                    color = Color.Green, trackColor = Color.White
                )

            }


            LazyColumn(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                item {
                    Text(text = question)
                }
                items(answers.size) { index ->
                    val answer = answers[index]
                    selectionEnabled.value
                    val boxColor = when {


                        selectedAnswer.value == rightAnswerNumber -1 && rightAnswerNumber -1 == index && !selectionEnabled.value -> Color.Green
                        index == rightAnswerNumber-1 && !selectionEnabled.value -> Color.Green
                        selectedAnswer.value == index && rightAnswerNumber - 1 != index  && !selectionEnabled.value -> Color.Red
                        selectedAnswer.value == index -> Color.Yellow
                        else -> Color.Gray
                    }

                    val textColor = when {
                        selectedAnswer.value == index -> Color.Gray
                        rightAnswerNumber - 1  == index && !selectionEnabled.value -> Color.Gray
                        else -> Color.Yellow
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
    BackHandler {
        goMenu.invoke()
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