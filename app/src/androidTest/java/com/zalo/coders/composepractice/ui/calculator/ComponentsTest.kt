package com.zalo.coders.composepractice.ui.calculator

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
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

    }

    @Test
    fun testCircleWithText() {

    }
}