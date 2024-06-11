package com.zalo.coders.composepractice.calculator.core

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


fun getColor(text: String): Color {
    val gray = Color(0xFFD3D3D3)
    val orange = Color(0xFFFFA500)
    val black = Color(0xFF817D7D)
    val green = Color(0xFF4CAF50)

    val defaultColor = Color.Gray

    val color = when (text) {
        "%", "*", "/" -> gray
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "AC" -> black
        "C", "-", "+", "HIS" -> orange
        "=" -> green
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
    "HIS",
    "AC",
    "0",
    ".",
    "="
)
