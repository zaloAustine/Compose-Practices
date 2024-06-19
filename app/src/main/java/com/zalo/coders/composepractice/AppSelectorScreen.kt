package com.zalo.coders.composepractice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zalo.coders.calculator.ui.theme.CalculatorTheme
import com.zalo.coders.note_app.theme.NoteAppTheme

/**
Created by zaloaustine in 6/19/24.
 */
@Composable
fun AppSelectorScreen(onAppSelected: (Apps) -> Unit) {
    CalculatorTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Various apps for compose mastery",
                modifier = Modifier.padding(12.dp),
                fontFamily = FontFamily.Monospace
            )
            Button(onClick = { onAppSelected.invoke(Apps.CalculatorApp) }) {
                Text(text = "Calculator")
            }
            Button(onClick = { onAppSelected.invoke(Apps.NotesApp) }) {
                Text(text = "Notes App")
            }
        }
    }
}

sealed class Apps {
    data object CalculatorApp : Apps()
    data object NotesApp : Apps()
}

@Preview(showBackground = true)
@Composable
private fun AppSelectorScreenPreview() {
    NoteAppTheme {
        AppSelectorScreen(onAppSelected = {})
    }
}