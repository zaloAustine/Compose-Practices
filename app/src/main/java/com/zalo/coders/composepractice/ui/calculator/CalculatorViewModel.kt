package com.zalo.coders.composepractice.ui.calculator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow


/**
Created by zaloaustine in 6/7/24.
 */
class CalculatorViewModel : ViewModel() {
    private val _operation = MutableStateFlow("")
    val operation get() = _operation

    private val _result = MutableStateFlow("0")
    val result get() = _operation

    fun updateOperation(operation: String = _operation.value,isClear:Boolean = false) {
        _operation.value = _operation.value.plus(operation)
    }

    fun updateResult() {
        _result.value = "500"
    }
}