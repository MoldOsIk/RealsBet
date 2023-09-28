package quizz.sportss.realsbet.question.app.ui.theme

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.CncBet.jogar.a123.ui.theme.porpg.button_
import com.CncBet.jogar.a123.ui.theme.porpg.text
import quizz.sportss.realsbet.question.app.R

@Composable
fun EndGame(score: Int, bet: Int, goPreGame:()-> Unit ) {

    val sharedPreferences = LocalContext.current.getSharedPreferences("1", Context.MODE_PRIVATE)
    var balance = sharedPreferences.getInt("balance", 0)
    Log.d("TAGTAG", "${balance}")
    LaunchedEffect(key1 = Unit){
        if(bet>=score && score!=0)
        {
            balance+=200
            sharedPreferences.edit().putInt("balance", balance).apply()
        }
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        Image(painter = painterResource(id = R.drawable.endgamebg), contentDescription ="",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            text(text = "You scored:", size = 35)
            text("$score/10", 30)
            text("Your predict was:", 35)
            text("${bet}/10", 35)
            text("You won", 35)
            text("${if(bet>=score)200 else 0}", 35)
        }
        button_(onClick = { goPreGame() }, modifier = Modifier.align(Alignment.BottomCenter)) {
            text(text = "Back", size = 30)
        }

    }
}