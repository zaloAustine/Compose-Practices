package com.zalo.coders.composepractice.calculator

import com.zalo.coders.composepractice.calculator.core.CalculatorManager
import com.zalo.coders.composepractice.calculator.ui.CalculatorAction
import com.zalo.coders.composepractice.calculator.ui.CalculatorViewModel
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Before
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

/**
Created by zaloaustine in 6/8/24.
 */

class CalculatorViewModelTest {

    @Mock
    lateinit var calculatorManager: CalculatorManager

    @InjectMocks
    lateinit var viewModel: CalculatorViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

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
        whenever(calculatorManager.calculate("1+1")).thenReturn("2")
        viewModel.onAction(CalculatorAction.NumberClicked("1"))
        viewModel.onAction(CalculatorAction.NumberClicked("+"))
        viewModel.onAction(CalculatorAction.NumberClicked("1"))
        viewModel.onAction(CalculatorAction.Calculate)
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
