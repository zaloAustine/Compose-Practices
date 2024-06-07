package com.zalo.coders.composepractice.ui.calculator

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable


/**
Created by zaloaustine in 6/7/24.
 */
class CalculatorViewModel : ViewModel() {
    private val _operation = MutableStateFlow("0")
    val operation get() = _operation

    private val _result = MutableStateFlow("0")
    val result get() = _result

    fun updateOperation(operation: String = _operation.value, isClear: Boolean = false) {
        if (isClear) {
            _operation.value = _operation.value.dropLast(1)
        }else {
            _operation.value = _operation.value.plus(operation)
        }
        updateResult()
    }

    fun updateResult() {
        try {
            if(_operation.value != "0" && _operation.value.isNotBlank()) {
                val context: Context = Context.enter()
                context.optimizationLevel = -1
                val scriptable: Scriptable = context.initStandardObjects()

                var finalResult = context.evaluateString(scriptable, operation.value, "Javascript", 1, null).toString()
                if (finalResult.endsWith(".0")) {
                    finalResult = finalResult.replace(".0", "")
                }
                _result.value = finalResult
            }
        } catch (_: Exception) {}
    }

    fun clearAll() {
        _operation.value = ""
    }
}