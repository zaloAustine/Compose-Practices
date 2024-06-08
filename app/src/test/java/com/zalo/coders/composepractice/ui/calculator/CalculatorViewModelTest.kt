package com.zalo.coders.composepractice.ui.calculator

import org.junit.Test
import org.junit.Assert.assertEquals

/**
Created by zaloaustine in 6/8/24.
 */

class CalculatorViewModelTest {

    private val viewModel = CalculatorViewModel()


    @Test
    fun testInitialState() {
        assertEquals("", viewModel.state.operation)
        assertEquals("", viewModel.state.result)
    }

    @Test
    fun testNumberClicked() {
        viewModel.onAction(CalculatorAction.NumberClicked("1"))
        viewModel.onAction(CalculatorAction.NumberClicked("+"))
        viewModel.onAction(CalculatorAction.NumberClicked("1"))
        assertEquals("1+1", viewModel.state.operation)
    }

    @Test
    fun testCalculate() {
        viewModel.onAction(CalculatorAction.NumberClicked("1"))
        viewModel.onAction(CalculatorAction.NumberClicked("+"))
        viewModel.onAction(CalculatorAction.NumberClicked("1"))
        assertEquals("2", viewModel.state.result)
    }

    @Test
    fun testDelete() {
        viewModel.onAction(CalculatorAction.NumberClicked("1"))
        viewModel.onAction(CalculatorAction.NumberClicked("+"))
        viewModel.onAction(CalculatorAction.NumberClicked("1"))
        viewModel.onAction(CalculatorAction.Delete(isClear = true))
        assertEquals("1+", viewModel.state.operation)
    }

    @Test
    fun testDeleteAll() {
        viewModel.onAction(CalculatorAction.NumberClicked("1"))
        viewModel.onAction(CalculatorAction.NumberClicked("2"))
        viewModel.onAction(CalculatorAction.NumberClicked("3"))

        viewModel.onAction(CalculatorAction.DeleteAll)
        assertEquals("", viewModel.state.operation)
    }
}
