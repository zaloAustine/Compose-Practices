package com.zalo.coders.note_app.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zalo.coders.note_app.core.NoteItem
import com.zalo.coders.note_app.core.SearchField
import com.zalo.coders.note_app.core.TopBar
import com.zalo.coders.note_app.theme.NoteAppTheme

/**
Created by zaloaustine in 6/12/24.
 */
@Composable
fun NotesHomeScreen() {
    NoteAppTheme {
        Scaffold(Modifier, topBar = { TopBar({}) }) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                SearchField()
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    items(count = 10) {
                        NoteItem()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteAppTheme {
        NotesHomeScreen()
    }
}