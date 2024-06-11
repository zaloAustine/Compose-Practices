package com.zalo.coders.composepractice.ui.calculator

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zalo.coders.composepractice.R


/**
Created by zaloaustine in 6/7/24.
 */
@Composable
fun DarkModeSelectorView(
    darkMode: Boolean,
    modifier: Modifier = Modifier,
    onIconClick: (Boolean) -> Unit
) {
    val icon = if (darkMode) painterResource(R.drawable.sun) else painterResource(R.drawable.moon)
    val iconColor = remember { Animatable(Color.Gray) }

    LaunchedEffect(darkMode) {
        iconColor.animateTo(
            targetValue = if (darkMode) Color.Yellow else Color.Gray,
            animationSpec = tween(durationMillis = 300, easing = LinearEasing)
        )
    }

    Column(modifier = modifier) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = iconColor.value,
            modifier = Modifier
                .size(50.dp)
                .padding(12.dp)
                .clickable {
                    onIconClick(!darkMode)
                }
        )
    }
}

@Composable
fun AnswerView(operation: String, result: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(text = operation, color = Color.Gray)
        Text(text = result, fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}

@Composable
fun NumberPad(
    modifier: Modifier = Modifier,
    currentOperation: String,
    numberClicked: (String) -> Unit,
    calculate: () -> Unit,
    delete: () -> Unit,
    deleteAll: () -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        items(padItems.size) {
            val text = padItems[it]
            CircleWithText(text = padItems[it]) { valueClicked ->
                when (text) {
                    "=" -> {
                        calculate.invoke()
                    }

                    "C" -> {
                        delete.invoke()
                    }

                    "AC" -> {
                        deleteAll.invoke()
                    }

                    "%", "*", "/", "-", "+" -> {
                        if (currentOperation.isEmpty() || !currentOperation.last().isDigit()) {
                            numberClicked.invoke("")
                        }else{
                            if (isValidInput(valueClicked)) {
                                numberClicked.invoke(valueClicked)
                            }
                        }
                    }

                    else -> {
                        if (isValidInput(valueClicked)) {
                            numberClicked.invoke(valueClicked)
                        } else {
                            numberClicked.invoke("")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CircleWithText(text: String, modifier: Modifier = Modifier, onClick: (String) -> Unit) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .size(100.dp)
            .padding(8.dp)
            .background(color = getColor(text), shape = CircleShape)
            .clickable { onClick.invoke(text) },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color.White, fontSize = 18.sp)
    }
}
