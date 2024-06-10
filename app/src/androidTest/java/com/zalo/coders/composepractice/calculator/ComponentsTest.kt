package com.zalo.coders.composepractice.calculator

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zalo.coders.composepractice.calculator.components.AnswerView
import com.zalo.coders.composepractice.calculator.components.CircleWithText
import com.zalo.coders.composepractice.calculator.components.DarkModeSelectorView
import com.zalo.coders.composepractice.calculator.components.NumberPad
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
Created by zaloaustine in 6/8/24.
 */
@RunWith(AndroidJUnit4::class)
class ComponentsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testDarkModeSelectorView() {
        val darkModeState = mutableStateOf(false)
        composeTestRule.setContent {
            DarkModeSelectorView(
                darkMode = darkModeState.value,
                onIconClick = { darkModeState.value = it })
        }

        composeTestRule.onNodeWithTag("modeIcon").assertIsDisplayed()
        composeTestRule.onNodeWithTag("modeIcon").performClick()

        composeTestRule.runOnIdle { assert(darkModeState.value) }
    }

    @Test
    fun testAnswerView() {
        composeTestRule.setContent {
            AnswerView(operation = "1+1", result = "2")
        }

        composeTestRule.onNodeWithText("1+1").assertIsDisplayed()
        composeTestRule.onNodeWithText("2").assertIsDisplayed()
    }

    @Test
    fun testNumberPad() {
        val currentOperation = mutableStateOf("")
        composeTestRule.setContent {
            NumberPad(
                currentOperation = currentOperation.value,
                numberClicked = { currentOperation.value = currentOperation.value.plus(it) },
                calculate = { currentOperation.value = "3" },
                delete = { currentOperation.value = currentOperation.value.dropLast(1) },
                deleteAll = { currentOperation.value = "" })
        }

        composeTestRule.onNodeWithText("1").performClick()
        composeTestRule.runOnIdle {
            assert(currentOperation.value == "1")
        }
        composeTestRule.onNodeWithText("+").performClick()
        composeTestRule.runOnIdle {
            assert(currentOperation.value == "1+")
        }
        composeTestRule.onNodeWithText("2").performClick()
        composeTestRule.runOnIdle {
            assert(currentOperation.value == "1+2")
        }

        composeTestRule.onNodeWithText("C").performClick()
        composeTestRule.runOnIdle {
            assert(currentOperation.value == "1+")
        }

        composeTestRule.onNodeWithText("2").performClick()
        composeTestRule.runOnIdle {
            assert(currentOperation.value == "1+2")
        }

        composeTestRule.onNodeWithText("=").performClick()
        composeTestRule.runOnIdle { assert(currentOperation.value == "3") }
    }

    @Test
    fun testCircleWithText() {
        val clickValue = mutableStateOf("")
        composeTestRule.setContent {
            CircleWithText(text = "1", onClick = {clickValue.value = it})
        }
        composeTestRule.onNodeWithTag("CircleWithText").assertIsDisplayed()
        composeTestRule.onNodeWithTag("CircleWithText").performClick()
        composeTestRule.runOnIdle {
            assert(clickValue.value == "1")
        }
    }
}