package com.zalo.coders.calculator.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.zalo.coders.calculator.components.AnswerView
import com.zalo.coders.calculator.components.CircleWithText
import com.zalo.coders.calculator.components.DarkModeSelectorView
import com.zalo.coders.calculator.components.NumberPad
import com.zalo.coders.calculator.components.NumberPadLandscape
import com.zalo.coders.calculator.core.isValidInput
import com.zalo.coders.calculator.core.padItems
import com.zalo.coders.calculator.ui.theme.CalculatorTheme
import kotlinx.serialization.json.Json.Default.configuration

/**
Created by zaloaustine in 6/7/24.
 */
@Composable
fun CalculatorScreen(
    calculatorState: CalculatorState,
    onAction: (CalculatorAction) -> Unit
) {
    val configuration = LocalConfiguration.current
    val isLandscapeOrientation by remember { derivedStateOf { configuration.orientation == Configuration.ORIENTATION_LANDSCAPE } }
    var idDarkMode by remember { mutableStateOf(false) }

    CalculatorTheme(darkTheme = idDarkMode) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            ) {
                DarkModeSelectorView(idDarkMode) { idDarkMode = it }
                AnswerView(operation = calculatorState.operation, result = calculatorState.result)
                if (!isLandscapeOrientation) {
                    NumberPad(
                        currentOperation = calculatorState.operation,
                        numberClicked = { clickValue ->
                            onAction.invoke(
                                CalculatorAction.NumberClicked(
                                    clickValue
                                )
                            )
                        },
                        calculate = { onAction.invoke(CalculatorAction.Calculate) },
                        delete = { onAction.invoke(CalculatorAction.Delete(true)) },
                        deleteAll = { onAction.invoke(CalculatorAction.DeleteAll) })
                } else {
                    NumberPadLandscape(
                        currentOperation = calculatorState.operation,
                        numberClicked = { clickValue ->
                            onAction.invoke(
                                CalculatorAction.NumberClicked(
                                    clickValue
                                )
                            )
                        },
                        calculate = { onAction.invoke(CalculatorAction.Calculate) },
                        delete = { onAction.invoke(CalculatorAction.Delete(true)) },
                        deleteAll = { onAction.invoke(CalculatorAction.DeleteAll) })
                }
            }
        }
    }
}



@PreviewScreenSizes()
@Composable
fun GreetingPreview() {
    CalculatorTheme {
        CalculatorScreen(calculatorState = CalculatorState(), onAction = {})
    }
}