package com.zalo.coders.calculator.components

import android.content.res.Configuration
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zalo.coders.calculator.core.getColor
import com.zalo.coders.calculator.core.isValidInput
import com.zalo.coders.calculator.core.padItems


/**
Created by zaloaustine in 6/7/24.
 */
@Composable
fun DarkModeSelectorView(
    darkMode: Boolean,
    modifier: Modifier = Modifier,
    onIconClick: (Boolean) -> Unit
) {
    val iconColor = remember { Animatable(Color.Gray) }
    LaunchedEffect(darkMode) {
        iconColor.animateTo(
            targetValue = if (darkMode) Color.Yellow else Color.Gray,
            animationSpec = tween(durationMillis = 300, easing = LinearEasing)
        )
    }

    Column(modifier = modifier) {
        Switch(
            checked = darkMode,
            onCheckedChange = { onIconClick(!darkMode) },
            modifier = Modifier
                .padding(12.dp)
                .testTag("modeIcon"),
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                uncheckedThumbColor = MaterialTheme.colorScheme.primary,
                checkedTrackColor = Color.DarkGray,
                uncheckedTrackColor = Color.LightGray
            )
        )
    }
}

@Composable
fun AnswerView(operation: String, result: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 24.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = operation,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 18.sp,
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = result,
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            fontFamily = FontFamily.Monospace

        )
    }
}

@Composable
fun NumberPad(
    modifier: Modifier = Modifier,
    currentOperation: String,
    numberClicked: (String) -> Unit,
    calculate: () -> Unit,
    delete: () -> Unit,
    deleteAll: () -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Bottom
    ) {

        items(padItems.size, span = {
            if (padItems[it] == "0") {
                GridItemSpan(2)
            } else {
                GridItemSpan(1)
            }
        }) {
            val text = padItems[it]
            CircleWithText(text = padItems[it]) { valueClicked ->
                when (text) {
                    "=" -> {
                        calculate.invoke()
                    }

                    "C" -> {
                        delete.invoke()
                    }

                    "AC" -> {
                        deleteAll.invoke()
                    }

                    "%", "*", "/", "-", "+", "." -> {
                        if (currentOperation.isEmpty() || !currentOperation.last().isDigit()) {
                            numberClicked.invoke("")
                        } else {
                            numberClicked.invoke(valueClicked)
                        }
                    }

                    else -> {
                        if (isValidInput(valueClicked)) {
                            numberClicked.invoke(valueClicked)
                        } else {
                            numberClicked.invoke("")
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun CircleWithText(text: String, modifier: Modifier = Modifier, onClick: (String) -> Unit) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val size by remember(configuration) {
        derivedStateOf {
            if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                (screenHeight.times(0.2f)).coerceAtMost(screenWidth.times(0.2f))
            } else {
                (screenHeight * 0.2f).coerceAtMost(screenWidth * 0.2f)
            }
        }
    }

    Box(
        modifier = modifier
            .size(size)
            .clickable { onClick.invoke(text) }
            .padding(8.dp)
            .background(color = getColor(text), shape = CircleShape)
            .testTag("CircleWithText"),
        contentAlignment = Alignment.Center
    ) {
        if (text == "0") {
            Text(
                text = text,
                color = Color.White,
                fontSize = 32.sp,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                textAlign = TextAlign.Start
            )
        } else {
            Text(
                text = text,
                color = Color.White,
                fontSize = 32.sp,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Start
            )
        }
    }
}


@Composable
fun NumberPadLandscape(
    modifier: Modifier = Modifier,
    currentOperation: String,
    numberClicked: (String) -> Unit,
    calculate: () -> Unit,
    delete: () -> Unit,
    deleteAll: () -> Unit,
) {
    Box {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(4),
            modifier = modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(padItems.size, span = {
                if (padItems[it] == "0") {
                    GridItemSpan(2)
                } else {
                    GridItemSpan(1)
                }
            }) {
                val text = padItems[it]
                CircleWithText(text = padItems[it]) { valueClicked ->
                    when (text) {
                        "=" -> {
                            calculate.invoke()
                        }

                        "C" -> {
                            delete.invoke()
                        }

                        "AC" -> {
                            deleteAll.invoke()
                        }

                        "%", "*", "/", "-", "+", "." -> {
                            if (currentOperation.isEmpty() || !currentOperation.last().isDigit()) {
                                numberClicked.invoke("")
                            } else {
                                numberClicked.invoke(valueClicked)
                            }
                        }

                        else -> {
                            if (isValidInput(valueClicked)) {
                                numberClicked.invoke(valueClicked)
                            } else {
                                numberClicked.invoke("")
                            }
                        }
                    }
                }
            }
        }
    }
}