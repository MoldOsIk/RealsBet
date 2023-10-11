package com.quizofsport.staybetter.forever.common

import android.util.Log
import android.view.MotionEvent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.quizofsport.staybetter.forever.ui.theme.black
import com.quizofsport.staybetter.forever.ui.theme.defSportColor
import com.quizofsport.staybetter.forever.ui.theme.lightSportColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun but_sport(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    all: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    val che_nigga = remember { Animatable(1f) }
    val corotuina = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .then(modifier)
            .scale(che_nigga.value)
            .background(brush = Brush.verticalGradient(listOf(lightSportColor, defSportColor)
            ), shape = RoundedCornerShape(20))
            .border(1.4.dp, black,shape = RoundedCornerShape(20))
            .padding(horizontal =  16.dp).padding( top = 4.dp).padding(all)

            .pointerInteropFilter{
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                       corotuina.launch {
                            che_nigga.animateTo(0.9f, animationSpec = tween(80)
                            )
                        }
                    }
                    MotionEvent.ACTION_UP -> {
                        corotuina.launch {
                            che_nigga.animateTo(1f, animationSpec = tween(80), block = {
                                if (che_nigga.value == 1f) { onClick.invoke() } })
                        }
                    }
                    MotionEvent.ACTION_CANCEL->{
                        corotuina.launch {
                            che_nigga.animateTo(1f, animationSpec = tween(80))}
                    }

                }
               true
            }
        ,
        contentAlignment = Alignment.Center
    ) {
        content.invoke()
    }

}
