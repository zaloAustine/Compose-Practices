package com.zalo.coders.calculator.ui


/**
Created by zaloaustine in 6/8/24.
 */
sealed class CalculatorAction {
    data class NumberClicked(val number: String) : CalculatorAction()
    data object Calculate : CalculatorAction()
    data class Delete(val isClear: Boolean) : CalculatorAction()
    data object DeleteAll : CalculatorAction()
}
