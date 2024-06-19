package com.zalo.coders.calculator.core

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.ExperimentalSafeArgsApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zalo.coders.calculator.ui.CalculatorScreen
import com.zalo.coders.calculator.ui.CalculatorViewModel
import kotlinx.serialization.Serializable

/**
Created by zaloaustine in 6/19/24.
 */
@OptIn(ExperimentalSafeArgsApi::class)
fun NavGraphBuilder.calculator() {
    composable<Calculator> {
        val calculatorViewModel: CalculatorViewModel = hiltViewModel()
        CalculatorScreen(
            calculatorState = calculatorViewModel.state,
            onAction = calculatorViewModel::onAction
        )
    }
}

@Serializable
object Calculator