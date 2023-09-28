package quizz.sportss.realsbet.question.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.CncBet.jogar.a123.ui.theme.porpg.text
import quizz.sportss.realsbet.question.app.R
import quizz.sportss.realsbet.question.app.ui.theme.black
import quizz.sportss.realsbet.question.app.ui.theme.defSportColor

@Composable
fun Privacy_screen() {
    Box(modifier = Modifier.fillMaxSize())
    {
        Image(
            painter = painterResource(id = R.drawable.home_background), contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 64.dp)
            .background(color = defSportColor, shape = RoundedCornerShape(10))
            .border(1.4.dp, black, shape = RoundedCornerShape(10))
            .padding(16.dp)
            .verticalScroll(
                rememberScrollState()
            ), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Privacy policy", fontSize = 28.sp , color = black,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.poppins))
            )
            text(text = stringResource(id = R.string.privacy), size = 18,color = black)

        }
    }
}