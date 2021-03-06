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
package com.example.androiddevchallenge.countdown

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.widget.HourGlass

@Composable
fun CountdownContent(timerText: String, completionPercent: Float, playState: PlayState, onPlayClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            shape = MaterialTheme.shapes.medium.copy(all = CornerSize(percent = 50)),
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 24.dp, start = 16.dp, end = 16.dp),
            elevation = 16.dp
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {

                HourGlass(
                    modifier = Modifier
                        .width(140.dp)
                        .height(200.dp),
                    strokeColor = MaterialTheme.colors.onSurface,
                    completionPercentage = completionPercent
                )
            }
        }

        Text(
            text = timerText,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(top = 16.dp)
        )

        FloatingActionButton(
            modifier = Modifier.padding(16.dp),
            onClick = { onPlayClick() }
        ) {
            Image(
                imageVector = getIcon(playState),
                contentDescription = "Play"
            )
        }
    }
}

@Composable
private fun getIcon(playState: PlayState): ImageVector {
    val icon = when (playState) {
        PlayState.CAN_PLAY -> R.drawable.ic_play_arrow
        PlayState.CAN_PAUSE -> R.drawable.ic_pause
        PlayState.CAN_RESET -> R.drawable.ic_replay
    }
    return ImageVector.vectorResource(id = icon)
}

enum class PlayState {
    CAN_PLAY,
    CAN_PAUSE,
    CAN_RESET
}
