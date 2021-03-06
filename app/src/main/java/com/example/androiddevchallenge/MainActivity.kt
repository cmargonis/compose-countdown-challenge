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
package com.example.androiddevchallenge

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.countdown.CountdownContent
import com.example.androiddevchallenge.countdown.PlayState
import com.example.androiddevchallenge.ui.theme.MyTheme
import java.util.concurrent.TimeUnit

private const val TWENTY_SECONDS = 20000L

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {

    Surface(color = MaterialTheme.colors.background) {
        var playState by remember { mutableStateOf(PlayState.CAN_PLAY) }
        var timerText by remember { mutableStateOf("20:00") }
        var completedPercent by remember { mutableStateOf(0f) }
        val timer = object : CountDownTimer(TWENTY_SECONDS, 100) {
            override fun onTick(millisUntilFinished: Long) {
                timerText = formatMillisToSeconds(millisUntilFinished)
                val elapsedTime = TWENTY_SECONDS - millisUntilFinished
                completedPercent = (elapsedTime.toDouble() / TWENTY_SECONDS).toFloat()
            }

            override fun onFinish() {
                playState = PlayState.CAN_RESET
                timerText = "00:00"
            }
        }
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = MaterialTheme.colors.primary,
                    title = { Text(text = stringResource(id = R.string.app_name)) },
                    elevation = 8.dp
                )
            }
        ) {
            CountdownContent(
                timerText = timerText,
                completionPercent = completedPercent,
                playState = playState
            ) {
                playState = setPlayState(playState)
                when (playState) {
                    PlayState.CAN_PLAY -> {
                        timer.cancel()
                        completedPercent = 0f
                        timerText = "20:00"
                    }
                    PlayState.CAN_PAUSE -> timer.start()
                    PlayState.CAN_RESET -> timer.cancel()
                }
            }
        }
    }

}

private fun formatMillisToSeconds(timeMs: Long): String {
    return String.format(
        "%02d:%02d",
        TimeUnit.MILLISECONDS.toSeconds(timeMs),
        (timeMs - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(timeMs))).toString()
            .take(2)
            .toInt()
    )
}

private fun setPlayState(playState: PlayState) =
    when (playState) {
        PlayState.CAN_PLAY -> PlayState.CAN_PAUSE
        PlayState.CAN_PAUSE -> PlayState.CAN_RESET
        PlayState.CAN_RESET -> PlayState.CAN_PLAY
    }

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
