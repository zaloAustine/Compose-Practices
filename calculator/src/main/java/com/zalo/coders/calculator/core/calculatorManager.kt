package com.zalo.coders.calculator.core

import javax.inject.Inject
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

/**
Created by zaloaustine in 6/10/24.
 */
interface CalculatorManager {
    fun calculate(operation: String): String
}

class CalculatorManagerImpl @Inject constructor(private val scriptable: Scriptable, private val calculatorContext: Context) :
    CalculatorManager {

    companion object{
        const val SOURCE_NAME = "Javascript"
    }

    override fun calculate(operation: String): String {
        var finalResult =
            calculatorContext.evaluateString(scriptable, operation, SOURCE_NAME, 1, null)
                .toString()
        if (finalResult.endsWith(".0")) {
            finalResult = finalResult.replace(".0", "")
        }
        return finalResult
    }
}