package quizz.sportss.realsbet.question.app.ui.theme

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.CncBet.jogar.a123.ui.theme.porpg.SliderWithStep
import com.CncBet.jogar.a123.ui.theme.porpg.button_
import com.CncBet.jogar.a123.ui.theme.porpg.text
import quizz.sportss.realsbet.question.app.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun pregamescreen(goGame:(topic: Int, bet:Int) -> Unit ) {
    val sharedPreferences = LocalContext.current.getSharedPreferences("1", Context.MODE_PRIVATE)
    val images = listOf(
        R.drawable.footbal,
        R.drawable.basketball,
        R.drawable.hockey
    )
    val balance = sharedPreferences.getInt("balance", 0)
    val fullWidth = LocalConfiguration.current.screenWidthDp
    val fullHeight = LocalConfiguration.current.screenHeightDp
        val pagerState = rememberPagerState(pageCount = {
            3
        })
    var bet by remember{
        mutableStateOf(0)
    }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        Image(painter = painterResource(id = R.drawable.bg), contentDescription ="",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)
        Box(modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(top = (fullHeight * 0.05).dp))
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                text("Your game points:",20)
                text("${balance}",  20)

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
        text( "Select the topic of the questions:",  20,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = (fullHeight * 0.4).dp))
        Box(modifier = Modifier.align(Alignment.BottomCenter) )
        {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                text("Place you score:", 20)
                text("${bet}", 20)
                SliderWithStep(
                    value = bet.toFloat(),
                    onValueChange = {
                        bet = it.toInt()
                    },
                    minValue = 0f,
                    maxValue = 10f,
                    step = 1f,
                    sliderWidth = (fullWidth*0.6).dp,
                    sliderHeight = (fullHeight*0.2).dp
                )
            }
        }
    }




}