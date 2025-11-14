package com.example.jc_animation.Chapter3

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EasingCurvesExamples(modifier: Modifier = Modifier) {
    /**“Race of Easing”
     * Goal: Notice how each dot feels — which one starts slow? which one overshoots?*/
}

@Preview
@Composable
fun RaceOfEasing(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition()
    val progressLiner by infiniteTransition.animateFloat(
        0f,1f,
        animationSpec = infiniteRepeatable(tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse)
    )
    val progressFastOutSlowInEasing by infiniteTransition.animateFloat(0f,1f,
        animationSpec = infiniteRepeatable(tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse))

    val progressEaseInOutCubic by infiniteTransition.animateFloat(0f,1f,
        animationSpec = infiniteRepeatable(tween(1500, easing = EaseInOutCubic),repeatMode = RepeatMode.Reverse))

    //Cubic bezier
    val progressCustom by infiniteTransition.animateFloat(
        0f, 1f,
        animationSpec = infiniteRepeatable(
            tween(
                1500, easing = CubicBezierEasing(0f, 0f, 0.2f, 1f) //slow finish
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 42.dp)
    ) {
//        Box(
//            modifier = Modifier
//                .offset(x = progressLiner.dp * 300, y = 0.dp)
//                .clip(CircleShape)
//                .background(Color.Red)
//                .size(100.dp)
//        )
//        Box(
//            modifier = Modifier
//                .offset(x = progressFastOutSlowInEasing.dp * 300, y = 0.dp)
//                .clip(CircleShape)
//                .background(Color.Green)
//                .size(100.dp)
//        )
//        Box(
//            modifier = Modifier
//                .offset(x = progressEaseInOutCubic.dp * 300, y = 0.dp)
//                .clip(CircleShape)
//                .background(Color.Blue)
//                .size(100.dp)
//        )
        Box(
            modifier = Modifier
                .offset(x = progressCustom.dp * 300, y = 0.dp)
                .clip(CircleShape)
                .background(Color.Yellow)
                .size(100.dp)
        )
    }
}