package com.zalo.coders.calculator

import com.zalo.coders.calculator.core.CalculatorManager
import com.zalo.coders.calculator.ui.CalculatorAction
import com.zalo.coders.calculator.ui.CalculatorViewModel
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
    lateinit var calculatorManager: com.zalo.coders.calculator.core.CalculatorManager

    @InjectMocks
    lateinit var viewModel: com.zalo.coders.calculator.ui.CalculatorViewModel

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
        viewModel.onAction(com.zalo.coders.calculator.ui.CalculatorAction.NumberClicked("1"))
        viewModel.onAction(com.zalo.coders.calculator.ui.CalculatorAction.NumberClicked("+"))
        viewModel.onAction(com.zalo.coders.calculator.ui.CalculatorAction.NumberClicked("1"))
        assertEquals("1+1", viewModel.state.operation)
    }

    @Test
    fun testCalculate() {
        whenever(calculatorManager.calculate("1+1")).thenReturn("2")
        viewModel.onAction(com.zalo.coders.calculator.ui.CalculatorAction.NumberClicked("1"))
        viewModel.onAction(com.zalo.coders.calculator.ui.CalculatorAction.NumberClicked("+"))
        viewModel.onAction(com.zalo.coders.calculator.ui.CalculatorAction.NumberClicked("1"))
        viewModel.onAction(com.zalo.coders.calculator.ui.CalculatorAction.Calculate)
        assertEquals("2", viewModel.state.result)
    }

    @Test
    fun testDelete() {
        viewModel.onAction(com.zalo.coders.calculator.ui.CalculatorAction.NumberClicked("1"))
        viewModel.onAction(com.zalo.coders.calculator.ui.CalculatorAction.NumberClicked("+"))
        viewModel.onAction(com.zalo.coders.calculator.ui.CalculatorAction.NumberClicked("1"))
        viewModel.onAction(com.zalo.coders.calculator.ui.CalculatorAction.Delete(isClear = true))
        assertEquals("1+", viewModel.state.operation)
    }

    @Test
    fun testDeleteAll() {
        viewModel.onAction(com.zalo.coders.calculator.ui.CalculatorAction.NumberClicked("1"))
        viewModel.onAction(com.zalo.coders.calculator.ui.CalculatorAction.NumberClicked("2"))
        viewModel.onAction(com.zalo.coders.calculator.ui.CalculatorAction.NumberClicked("3"))

        viewModel.onAction(com.zalo.coders.calculator.ui.CalculatorAction.DeleteAll)
        assertEquals("", viewModel.state.operation)
    }
}
