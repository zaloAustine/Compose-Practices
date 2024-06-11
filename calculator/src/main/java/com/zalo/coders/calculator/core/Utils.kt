package com.zalo.coders.calculator.core

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


/**
Created by zaloaustine in 6/7/24.
 */
fun isValidInput(newValue: String): Boolean {
    // Define the allowed characters and patterns
    val validStartsWithNumber = "^\\d[\\d+\\-*/%]*$".toRegex()
    val validStartsWithOperator = "^[+\\-*/%]\\d*[\\d+\\-*/%]*$".toRegex()

    // Check if the input matches any of the allowed patterns
    return newValue.matches(validStartsWithNumber) || newValue.matches(validStartsWithOperator)
}

@Composable
fun getColor(text: String): Color {
    val gray = MaterialTheme.colorScheme.secondary
    val orange = Color(0xFFFFA500)
    val black = MaterialTheme.colorScheme.tertiary
    val defaultColor = Color.Gray

    val color = when (text) {
        "%", "*", "/" -> gray
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "." -> black
        "AC","C", "-", "+", "H" ,"="-> orange
        else -> defaultColor
    }
    return color
}

val padItems = listOf(
    "%",
    "*",
    "/",
    "C",
    "7",
    "8",
    "9",
    "-",
    "4",
    "5",
    "6",
    "+",
    "1",
    "2",
    "3",
    "AC",
    "0",
    ".",
    "="
)
