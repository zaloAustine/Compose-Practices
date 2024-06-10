package com.zalo.coders.composepractice.calculator.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.zalo.coders.composepractice.calculator.core.CalculatorManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
Created by zaloaustine in 6/7/24.
 */

@HiltViewModel
class CalculatorViewModel @Inject constructor(private val calculatorManager: CalculatorManager) : ViewModel() {

    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(calculatorAction: CalculatorAction) {
        when (calculatorAction) {
            is CalculatorAction.Calculate -> {
                updateResult()
            }

            is CalculatorAction.Delete -> {
                updateOperation(isClear = calculatorAction.isClear)
            }

            is CalculatorAction.NumberClicked -> {
                updateOperation(operation = calculatorAction.number)
            }

            CalculatorAction.DeleteAll -> clearAll()
        }
    }

    private fun updateOperation(operation: String = state.operation, isClear: Boolean = false) {
        state = if (isClear) {
            state.copy(operation = state.operation.dropLast(1))
        } else {
            state.copy(operation = state.operation.plus(operation))
        }
        updateResult()
    }

    private fun updateResult() {
        try {
            if (state.operation != "0" && state.operation.isNotBlank()) {
                state = state.copy(result = calculatorManager.calculate(state.operation))
            }
        } catch (_: Exception) {
        }
    }

    private fun clearAll() {
        state = state.copy(operation = "")
    }
}