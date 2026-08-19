package com.zykrave.anirumy.core.ui.composables

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import kotlin.math.sin
import kotlin.random.Random

private data class Petal(
    var x: Float,
    var y: Float,
    val size: Float,
    val fallSpeed: Float,
    val swayAmplitude: Float,
    val swaySpeed: Float,
    var rotation: Float,
    val rotationSpeed: Float,
    val alpha: Float,
    val phase: Float,
)

@Composable
fun SakuraPetalsOverlay(
    modifier: Modifier = Modifier,
    petalCount: Int = 18,
    color: Color = Color(0xFFF7B8D0),
) {
    val petals = remember {
        mutableStateListOf<Petal>().apply {
            repeat(petalCount) {
                add(
                    Petal(
                        x = Random.nextFloat(),
                        y = Random.nextFloat() * -1f,
                        size = Random.nextFloat() * 8f + 6f,
                        fallSpeed = Random.nextFloat() * 0.00035f + 0.00025f,
                        swayAmplitude = Random.nextFloat() * 40f + 20f,
                        swaySpeed = Random.nextFloat() * 1.5f + 0.5f,
                        rotation = Random.nextFloat() * 360f,
                        rotationSpeed = Random.nextFloat() * 60f - 30f,
                        alpha = Random.nextFloat() * 0.35f + 0.25f,
                        phase = Random.nextFloat() * 6.28f,
                    )
                )
            }
        }
    }

    var elapsedMs by remember { mutableLongStateOf(0L) }

    LaunchedEffect(Unit) {
        var lastFrameTime = withFrameNanos { it }
        while (true) {
            val frameTime = withFrameNanos { it }
            val deltaMs = (frameTime - lastFrameTime) / 1_000_000f
            lastFrameTime = frameTime
            elapsedMs += deltaMs.toLong()

            for (i in petals.indices) {
                val p = petals[i]
                val newY = p.y + p.fallSpeed * deltaMs
                petals[i] = p.copy(
                    y = if (newY > 1.1f) -0.1f else newY,
                    rotation = p.rotation + p.rotationSpeed * deltaMs / 1000f,
                )
            }
        }
    }

    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        petals.forEach { p ->
            val swayX = sin((elapsedMs / 1000f) * p.swaySpeed + p.phase) * p.swayAmplitude
            val cx = p.x * w + swayX
            val cy = p.y * h

            rotate(degrees = p.rotation, pivot = Offset(cx, cy)) {
                drawOval(
                    color = color.copy(alpha = p.alpha),
                    topLeft = Offset(cx - p.size / 2, cy - p.size / 3),
                    size = Size(p.size, p.size * 0.65f),
                )
            }
        }
    }
}
