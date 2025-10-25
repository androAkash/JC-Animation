package com.example.jc_animation.Chapter2

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

/**Exercise: BouncingBox (Infinite Animation)
 * Create a box that bounces vertically up and down forever.*/

/**🚀 Next Step Choices
Now you’ve mastered:
Triggered animations (state → animate)
Continuous animations (infinite transition)
We have two natural next directions to train your animation muscle:
Easing, timing, and curve mastery
→ Learn how EaseIn, EaseOut, CubicBezierEasing change how things feel.
Combination motion
→ Make your box move + rotate + scale simultaneously.
(This starts teaching you animation composition — the key to advanced UI work.)*/

@Composable
fun BouncingBallAnimation() {
    val infiniteTransition = rememberInfiniteTransition()
    val targetOffset  = 200f
    val animationOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = targetOffset,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = EaseInCubic) /*spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)*/,
            repeatMode = RepeatMode.Reverse
        )
    )
    Box (modifier = Modifier
        .size(500.dp)
        .background(Color.Gray)){
        Box(modifier = Modifier
            .offset {
                IntOffset(x = 0, y = animationOffset.toInt())
            }
            .size(100.dp)
            .clip(shape = CircleShape)
            .background(Color.Red)
            .align(Alignment.Center))
    }
}
/**Variation 2 – Change color as it bounces*/
@Composable
fun BouncingBallWithColorChangingEffect() {
    val infiniteTransition = rememberInfiniteTransition()
    val animateOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 200f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 900, easing = EaseInCubic),
            repeatMode = RepeatMode.Reverse
        )
    )
    val animateColor by infiniteTransition.animateColor(
        initialValue = Color.Green,
        targetValue = Color.Blue,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = EaseInCubic
            ), repeatMode = RepeatMode.Reverse
        ),
    )
    Box(modifier = Modifier.size(500.dp)) {
        Box(Modifier
            .offset {
                IntOffset(x = 0, y = animateOffset.toInt())
            }
            .clip(CircleShape)
            .size(100.dp)
            .background(color = animateColor)
            .align(Alignment.Center))
    }
}
/**Variation 3 – Two balls, opposite direction*/
@Composable
fun TwoBouncingBalls() {
    val infiniteTransition = rememberInfiniteTransition()
    val animateOffsetDown by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 400f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse)
    )

    val offsetUp = 400f - animateOffsetDown

    Box (modifier = Modifier.fillMaxSize().background(Color.Gray)){
        Box(modifier = Modifier
            .offset { IntOffset(x = 0, y = animateOffsetDown.toInt()) }
            .size(100.dp)
            .clip(shape = CircleShape)
            .background(Color.Red)
            .align(Alignment.Center))

        Box(modifier = Modifier
            .offset { IntOffset(x = 0, y = offsetUp.toInt()) }
            .size(100.dp)
            .clip(shape = CircleShape)
            .background(Color.Blue)
            .align(Alignment.Center))
    }
}

@Preview(showBackground = true)
@Composable
private fun BouncingBallAnimationPreview() {
    Column {
        /*BouncingBallAnimation()
        Spacer(Modifier.height(20.dp))
        BouncingBallWithColorChangingEffect()*/
        TwoBouncingBalls()
    }

}