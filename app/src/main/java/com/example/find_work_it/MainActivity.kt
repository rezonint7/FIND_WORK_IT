package com.example.find_work_it

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.find_work_it.presentation.navigation.SetUpNavController
import com.example.find_work_it.presentation.screens.splash_screen.SplashScreen
import com.example.find_work_it.presentation.ui.theme.FIND_WORK_ITTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FIND_WORK_ITTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SetUpNavController(controller = rememberNavController())
                }
            }
        }
    }
}



@Composable
fun Greeting(name: String) {
    Text(text = "Hello!")

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FIND_WORK_ITTheme {

    }
}