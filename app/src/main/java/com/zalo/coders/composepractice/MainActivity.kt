package com.zalo.coders.composepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.ExperimentalSafeArgsApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zalo.coders.composepractice.ui.calculator.CalculatorScreen
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSafeArgsApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = LaunchScreen) {
                composable<LaunchScreen> {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Calculator")
                    }

                    LaunchedEffect(null) {
                        delay(2000)
                        navController.navigate(Calculator)
                    }
                }

                composable<Calculator> {
                    CalculatorScreen()
                }
            }

        }
    }
}

@Serializable
object LaunchScreen

@Serializable
object Calculator
