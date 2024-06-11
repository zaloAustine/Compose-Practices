package com.zalo.coders.composepractice.ui.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

/**
Created by zaloaustine in 6/7/24.
 */
class CalculatorViewModel : ViewModel() {

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
                val context: Context = Context.enter()
                context.optimizationLevel = -1
                val scriptable: Scriptable = context.initStandardObjects()

                var finalResult =
                    context.evaluateString(scriptable, state.operation, "Javascript", 1, null)
                        .toString()
                if (finalResult.endsWith(".0")) {
                    finalResult = finalResult.replace(".0", "")
                }
                state = state.copy(result = finalResult)
            }
        } catch (_: Exception) {
        }
    }

    private fun clearAll() {
        state = state.copy(operation = "")
    }
}