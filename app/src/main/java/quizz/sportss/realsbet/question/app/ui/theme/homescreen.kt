package quizz.sportss.realsbet.question.app.ui.theme

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.CncBet.jogar.a123.ui.theme.porpg.button_
import com.CncBet.jogar.a123.ui.theme.porpg.text
import quizz.sportss.realsbet.question.app.R

@Composable
fun homescreen(goPreGame:() -> Unit, goRules:() -> Unit, goPolicy:() -> Unit) {

    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize())
    {
        Image(painter = painterResource(id = R.drawable.bg), contentDescription ="",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)
        Column(modifier  = Modifier
            .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            button_(onClick = { goPreGame() }) {
                text(text = "START GAME", size = 33)
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.02f))
            button_(onClick = { goRules() }) {
                text(text = "RULES", size = 33)
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.02f))
            button_(onClick = { goPolicy }) {
                text(text = "POLICY", size = 33)
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.02f))
            button_(onClick = { (context as? ComponentActivity)?.finish()}) {
                text(text = "EXIT", size = 33)
            }

        }

    }
}