package com.zalo.coders.note_app.core

import androidx.navigation.ExperimentalSafeArgsApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zalo.coders.note_app.presentation.AddNoteScreen
import com.zalo.coders.note_app.presentation.LaunchScreen
import com.zalo.coders.note_app.presentation.NotesHomeScreen
import kotlinx.serialization.Serializable

/**
Created by zaloaustine in 6/19/24.
 */
@OptIn(ExperimentalSafeArgsApi::class)
fun NavGraphBuilder.notesApp(navController: NavController) {
    composable<SplashScreen> {
        LaunchScreen {
            navController.navigate(NotesHomeScreen)
        }
    }

    composable<NotesHomeScreen> {
        NotesHomeScreen()
    }

    composable<AddNoteScreen> {
        AddNoteScreen()
    }
}

@Serializable
object SplashScreen

@Serializable
object NotesHomeScreen

@Serializable
object AddNoteScreen