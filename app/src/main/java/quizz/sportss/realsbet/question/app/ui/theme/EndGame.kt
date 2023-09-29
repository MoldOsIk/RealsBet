package quizz.sportss.realsbet.question.app.ui.theme

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.CncBet.jogar.a123.ui.theme.porpg.button_
import com.CncBet.jogar.a123.ui.theme.porpg.text
import quizz.sportss.realsbet.question.app.R

@Composable
fun EndGame(score: Int, bet: Int, goPreGame:()-> Unit ,balance:MutableState<Int>, topic : Int) {

    val defColor = remember {
        when(topic){
            0-> Color(0xFF2867C7)
            1-> Color(0xFFDBA24D)
            else-> Color(0xFFB2D4E4)
        }

    }

    val coolback = remember {
        Modifier
            .background(color = defColor, shape = RoundedCornerShape(20))
            .border(1.4.dp, black, shape = RoundedCornerShape(20))
            .padding(horizontal = 16.dp)
    }

    val newMoney = remember {
        mutableStateOf(0)
    }
    Log.d("guyt6hu","$bet $score $balance")
    LaunchedEffect(key1 = Unit){
        if(bet<=score && score!=0) {
            newMoney.value=200*(score-bet+1)
            balance.value+=newMoney.value
        }
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        Image(painter = painterResource(id =
        when(topic){
            0->R.drawable.football
            1->R.drawable.backettopic
            else->R.drawable.hockeytopic
        }
        ), contentDescription = "" ,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier =    Modifier.then(coolback)) {
            text(text = "You scored:", size = 35, color = Black)
            text(text = "$score/10", 30, color = Black)
            text(text = "Your predict was:", 35, color = Black)
            text(text = "${bet}/10", 35, color = Black)
            text("You won", 35, color = Black)
            text("${newMoney.value}", 35, color = Black)
        }
        button_(onClick = { goPreGame() }, modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 16.dp)) {
            text(text = "Back", size = 30, color = Black, modifier = Modifier.then(coolback))
        }

    }
    BackHandler {
        goPreGame()
    }
}