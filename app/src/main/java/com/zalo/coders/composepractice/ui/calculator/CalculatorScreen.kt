package com.zalo.coders.composepractice.ui.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zalo.coders.composepractice.ui.theme.ComposePracticeTheme


/**
Created by zaloaustine in 6/7/24.
 */
@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {

    val viewModel:CalculatorViewModel  = viewModel()

    var idDarkMode by remember { mutableStateOf(false) }
    var operation = viewModel.operation.collectAsState()
    var result  = viewModel.result.collectAsState()

    ComposePracticeTheme(darkTheme = idDarkMode) {
        Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                DarkModeSelectorView(idDarkMode) { idDarkMode = it }
                Spacer(modifier = Modifier.height(48.dp))
                AnswerView(operation = operation.value, result = result.value)
                NumberPad(onClick = { clickValue ->
                    viewModel.updateOperation(clickValue)
                }, onEquals = {
                    viewModel.updateResult()
                }, onClear = {
                    viewModel.updateOperation(isClear = true)
                })
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePracticeTheme {
        CalculatorScreen()
    }
}