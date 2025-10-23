package com.example.jc_animation.Chapter1

import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

/**Stage 1: Learn the low-level tools
 * Goal: Animate a box moving across the screen.*/
@Composable
fun MovingBoxHorizontally(containerWidth: Dp = 400.dp) {
    //1> state that controls whether box is on right or on left
    var moved by remember { mutableStateOf(false) }
    //2)target offset in dp depending on `moved`
    val targetOffsetDp = if (moved) containerWidth - 50.dp else 0.dp
    val density = LocalDensity.current
    //3)animateFloatAsState expects a Float -> convert Dp to px using local density
    val targetOffsetPx = with(density) { targetOffsetDp.toPx() }

    //4) animateFloatAsState does interpolation for us
    //underlying values us in Dp (convert to Float by using value in px)
    // We'll animate using Dp by animating the Float px value
    //then convert back when setting Modifier.offset

    val animatedOffsetPx by animateFloatAsState(
        targetValue = targetOffsetPx,
        //we can change duration/easing by passing animationSpec = tween(500)
        animationSpec = /*tween(500, easing = LinearOutSlowInEasing)*/
            spring(dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow)
    )

    Column(
        modifier = Modifier
            .padding(16.dp)
            .width(containerWidth),
        horizontalAlignment = Alignment.Start
    ) {
        //Track
        Box(modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .background(Color(0xFFECEFF1))) {
            //The animated box
            Box(modifier = Modifier
                .offset {
                    IntOffset(animatedOffsetPx.toInt(), 0)
                }
                .size(48.dp)
                .align(Alignment.CenterStart)
                .background(Color(0xFF2962FF))
                .clickable {
//                    moved = !moved
                })
        }
        Spacer(Modifier.height(12.dp))
        Button(onClick = {
            moved = !moved
        }) {
            Text(if (moved) "Move left" else "Move right")
        }
    }
}

/***/
@Composable
fun MovingBoxVertically(containerHeight:Dp = 400.dp) {
    var moved by remember { mutableStateOf(false) }
    val targetOffsetDp = if (moved) containerHeight - 150.dp else 0.dp
    val density = LocalDensity.current
    val targetOffsetPX = with(density){ targetOffsetDp.toPx() }
    val animateOffsetPx by animateDpAsState(targetValue = targetOffsetDp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)
    )

    Column (modifier = Modifier.size(containerHeight),
        horizontalAlignment = Alignment.CenterHorizontally){
        Box (){
            Box(modifier = Modifier
                .offset {
                    IntOffset(x = 0, y = animateOffsetPx.roundToPx())
                }
                .background(Color.Red)
                .size(84.dp)
                .align(Alignment.TopCenter))
        }
        Spacer(Modifier.weight(1f))
        Button(onClick = {moved = !moved}) {
            Text(if (moved) "Up" else "Down")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovingBoxPreview() {
//    MovingBoxHorizontally(containerWidth = 320.dp)
    MovingBoxVertically(containerHeight = 500.dp)
}