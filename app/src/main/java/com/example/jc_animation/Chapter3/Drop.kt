package com.example.jc_animation.Chapter3

import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
@Preview
@Composable
fun DropTest() {
    val infiniteTransition = rememberInfiniteTransition()
    val softDrop by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = EaseInCubic),
            repeatMode = RepeatMode.Reverse
        )
    )
    val hardDrop by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = EaseOutCubic),
            repeatMode = RepeatMode.Reverse
        )
    )
    Column(Modifier.fillMaxSize()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .offset(x = 0.dp, y = softDrop.dp * 300)
                    .clip(CircleShape)
                    .size(110.dp)
                    .background(Color.Green)
            )
            Text("SoftDrop",
                modifier = Modifier
                    .offset(x = 0.dp, y = softDrop.dp * 300))
            Spacer(Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .offset(x = 0.dp, y = hardDrop.dp * 300)
                    .clip(CircleShape)
                    .size(110.dp)
                    .background(Color.Black)
            )
            Text("HardDrop",
                modifier = Modifier
                    .offset(x = 0.dp, y = hardDrop.dp * 300))
        }
    }
}