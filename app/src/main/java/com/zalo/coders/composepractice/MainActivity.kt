package com.zalo.coders.composepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.ExperimentalSafeArgsApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zalo.coders.calculator.core.Calculator
import com.zalo.coders.calculator.core.calculator
import com.zalo.coders.note_app.core.SplashScreen
import com.zalo.coders.note_app.core.notesApp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSafeArgsApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = LaunchScreen) {
                composable<LaunchScreen> {
                    AppSelectorScreen {
                        when (it) {
                            Apps.CalculatorApp -> navController.navigate(Calculator)
                            Apps.NotesApp -> navController.navigate(SplashScreen)
                        }
                    }
                }
                calculator()
                notesApp(navController)
            }
        }
    }
}

@Serializable
object LaunchScreen
