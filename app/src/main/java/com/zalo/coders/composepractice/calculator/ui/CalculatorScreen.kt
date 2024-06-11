package com.zalo.coders.composepractice.calculator.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zalo.coders.composepractice.calculator.components.AnswerView
import com.zalo.coders.composepractice.calculator.components.DarkModeSelectorView
import com.zalo.coders.composepractice.calculator.components.NumberPad
import com.zalo.coders.composepractice.ui.theme.ComposePracticeTheme

/**
Created by zaloaustine in 6/7/24.
 */
@Composable
fun CalculatorScreen(
    calculatorState: CalculatorState,
    onAction: (CalculatorAction) -> Unit
) {

    var idDarkMode by remember { mutableStateOf(false) }

    ComposePracticeTheme(darkTheme = idDarkMode) {
        Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                DarkModeSelectorView(idDarkMode) { idDarkMode = it }
                Spacer(modifier = Modifier.height(48.dp))
                AnswerView(operation = calculatorState.operation, result = calculatorState.result)
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
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePracticeTheme {
        CalculatorScreen(calculatorState = CalculatorState(), onAction = {})
    }
}