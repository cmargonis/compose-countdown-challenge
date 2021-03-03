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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun HourGlass(strokeColor: Color = Color.Black) {
    val color = remember { strokeColor }
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
    ) {
        drawFrame(strokeColor = color)
    }
}

private fun DrawScope.drawFrame(strokeColor: Color) {
    drawLine(
        color = strokeColor,
        start = Offset.Zero,
        cap = StrokeCap.Round,
        end = Offset(size.width, 0f),
        strokeWidth = 12f
    )

    drawLine(
        color = strokeColor,
        start = Offset.Zero,
        end = Offset(size.width / 2, size.height / 2),
        strokeWidth = 12f,
        cap = StrokeCap.Round,
        pathEffect = PathEffect.cornerPathEffect(12f)
    )

    drawLine(
        color = strokeColor,
        start = Offset(size.width, 0f),
        cap = StrokeCap.Round,
        end = Offset(size.width / 2, size.height / 2),
        strokeWidth = 12f
    )

    drawLine(
        color = strokeColor,
        start = Offset(size.width, size.height),
        cap = StrokeCap.Round,
        end = Offset(size.width / 2, size.height / 2),
        strokeWidth = 12f
    )

    drawLine(
        color = strokeColor,
        start = Offset(0f, size.height),
        cap = StrokeCap.Round,
        end = Offset(size.width / 2, size.height / 2),
        strokeWidth = 12f
    )

    drawLine(
        color = strokeColor,
        start = Offset(0f, size.height),
        cap = StrokeCap.Round,
        end = Offset(size.width, size.height),
        strokeWidth = 12f
    )
}

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
        HourGlass(strokeColor = Color.Black)
    }
}
