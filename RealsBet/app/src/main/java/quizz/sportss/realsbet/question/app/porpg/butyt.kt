package com.CncBet.jogar.a123.ui.theme.porpg

import android.view.MotionEvent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import quizz.sportss.realsbet.question.app.R
import kotlin.math.roundToInt

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun button_(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val che_nigga = remember { Animatable(1f) }
    val corotuina = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .then(modifier)
            .scale(che_nigga.value)
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        corotuina.launch {
                            che_nigga.animateTo(0.79f, animationSpec = tween(80)
                            )
                        }
                    }
                    MotionEvent.ACTION_UP -> {
                        corotuina.launch {
                            che_nigga.animateTo(1f, animationSpec = tween(80), block = { if (che_nigga.value == 1f) { onClick.invoke() } })
                        }
                    }
                }
                true
            }, contentAlignment = Alignment.Center
    ) {
        content.invoke()
    }

}

@Composable
fun text(text: String, size: Int, modifier: Modifier = Modifier) {
    Text(text = text,
        fontSize = size.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily(Font(R.font.poppins)),
        modifier = modifier)
}

@Composable
fun SliderWithStep(
    value: Float,
    onValueChange: (Float) -> Unit,
    minValue: Float,
    maxValue: Float,
    step: Float,
    sliderWidth: Dp,
    sliderHeight: Dp,
) {
    var bet by remember { mutableStateOf(value.toString()) }

    Box(modifier = Modifier.width(sliderWidth).height(sliderHeight))
    {
        Slider(
            value = value,
            onValueChange = {
                val newValue = (it / step).roundToInt() * step
                onValueChange(newValue)
                bet = newValue.toString()
            },
            valueRange = minValue..maxValue,
            steps = ((maxValue - minValue) / step).roundToInt(),
            colors = SliderDefaults.colors(
                thumbColor = Color.White
            )
        )
    }



}
