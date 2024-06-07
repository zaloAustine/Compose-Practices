package com.zalo.coders.composepractice.ui.calculator

import androidx.compose.ui.graphics.Color


/**
Created by zaloaustine in 6/7/24.
 */
fun isValidInput(newValue: String): Boolean {
    val allowedCharacters = "0123456789+-X/%."
    val lastChar = newValue.lastOrNull()
    val newChar = newValue.lastOrNull()

    return when {
        newChar == null || newChar !in allowedCharacters -> false
        newValue.length == 1 && newChar == '-' -> true
        lastChar != null && lastChar in "+-X/%" && newChar in "+-X/%" -> false
        lastChar == null && newChar in "+X/%" -> false
        else -> true
    }
}

fun getColor(text: String): Color {
    val gray = Color(0xFFD3D3D3)
    val orange = Color(0xFFFFA500)
    val black = Color(0xFF817D7D)
    val green = Color(0xFF4CAF50)

    val defaultColor = Color.Gray

    val color = when (text) {
        "%", "X", "/" -> gray
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "AC" -> black
        "C", "-", "+", "HIS" -> orange
        "=" -> green
        else -> defaultColor
    }
    return color
}

val padItems = listOf(
    "%",
    "X",
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
