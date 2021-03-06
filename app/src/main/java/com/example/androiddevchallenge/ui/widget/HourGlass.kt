/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.widget

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun HourGlass(
    modifier: Modifier = Modifier,
    strokeColor: Color = MaterialTheme.colors.primaryVariant,
    fillColor: Color = MaterialTheme.colors.primary,
    completionPercentage: Float = 0.5f
) {
    val percent: Float by animateFloatAsState(completionPercentage)

    Canvas(
        modifier = modifier.padding(16.dp),
    ) {
        drawFrame(strokeColor = strokeColor, strokeWidth = 8f)

        val topComplete = (size.height / 2f) * percent
        val bottomComplete = (size.height - topComplete)
        drawTopSand(topEdge = topComplete, brush = SolidColor(value = fillColor))
        drawBottomSand(topEdge = bottomComplete, brush = SolidColor(value = fillColor))
    }
}

private fun DrawScope.drawFrame(strokeColor: Color, strokeWidth: Float) {
    drawLine(
        color = strokeColor,
        start = Offset.Zero,
        cap = StrokeCap.Round,
        end = Offset(size.width, 0f),
        strokeWidth = strokeWidth
    )

    drawLine(
        color = strokeColor,
        start = Offset.Zero,
        end = Offset(size.width / 2, size.height / 2),
        strokeWidth = strokeWidth,
        cap = StrokeCap.Round,
        pathEffect = PathEffect.cornerPathEffect(12f)
    )

    drawLine(
        color = strokeColor,
        start = Offset(size.width, 0f),
        cap = StrokeCap.Round,
        end = Offset(size.width / 2, size.height / 2),
        strokeWidth = strokeWidth
    )

    drawLine(
        color = strokeColor,
        start = Offset(size.width, size.height),
        cap = StrokeCap.Round,
        end = Offset(size.width / 2, size.height / 2),
        strokeWidth = strokeWidth
    )

    drawLine(
        color = strokeColor,
        start = Offset(0f, size.height),
        cap = StrokeCap.Round,
        end = Offset(size.width / 2, size.height / 2),
        strokeWidth = strokeWidth
    )

    drawLine(
        color = strokeColor,
        start = Offset(0f, size.height),
        cap = StrokeCap.Round,
        end = Offset(size.width, size.height),
        strokeWidth = strokeWidth
    )
}

private fun DrawScope.drawTopSand(topEdge: Float, brush: Brush) {
    val topStart = Point((size.width / size.height) * topEdge, topEdge)
    val topEnd = Point((-(size.width * topEdge) / size.height + size.width), topEdge)

    val path = Path()
    with(path) {
        fillType = PathFillType.EvenOdd
        moveTo(topStart.x, topStart.y)
        lineTo(topEnd.x, topEnd.y)
        lineTo(size.width / 2, size.height / 2)
        close()
    }
    drawPath(
        path = path,
        brush = brush
    )
}

private fun DrawScope.drawBottomSand(topEdge: Float, brush: Brush) {
    val topStart = Point((-(size.width * topEdge) / size.height + size.width), topEdge)
    val topEnd = Point((size.width / size.height) * (topEdge), topEdge)

    val path = Path()
    with(path) {
        fillType = PathFillType.EvenOdd
        moveTo(topStart.x, topStart.y)
        lineTo(topEnd.x, topEnd.y)
        lineTo(size.width, size.height)
        lineTo(0f, size.height)
        close()
    }
    drawPath(
        path = path,
        brush = brush
    )
}

data class Point(val x: Float, val y: Float)

@Preview(
    name = "Light Theme",
    widthDp = 360,
    heightDp = 640,
    showBackground = true,
    backgroundColor = 0xFFFFFF
)
@Composable
fun HourglassPreview() {
    MyTheme(darkTheme = false) {
        HourGlass(strokeColor = MaterialTheme.colors.primaryVariant)
    }
}
