package com.example.find_work_it

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.find_work_it.presentation.navigation.SetUpNavController
import com.example.find_work_it.presentation.screens.AddBasicTextField
import com.example.find_work_it.presentation.screens.FilterButton
import com.example.find_work_it.ui.theme.BasicTextFieldStyle
import com.example.find_work_it.ui.theme.FINDWORKIT_Theme
import com.example.find_work_it.ui.theme.MainTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FINDWORKIT_Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MainTheme.colors.primaryBackground
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
    FINDWORKIT_Theme(darkTheme = true) {
        Surface(modifier = Modifier.background(MainTheme.colors.primaryBackground)) {
            Spacer(modifier = Modifier.width(width = 8.dp))
            AddBasicTextField(
                sizeWidth = 254,
                sizeHeight = 48,
                textStyle = BasicTextFieldStyle,
                placeholder = "Поиск",
                icon = Icons.Default.Search,
                iconContentDescription = "iconSearch"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    FINDWORKIT_Theme(darkTheme = false) {
        Surface() {
            Spacer(modifier = Modifier.width(width = 8.dp))
            AddBasicTextField(
                sizeWidth = 254,
                sizeHeight = 48,
                textStyle = BasicTextFieldStyle,
                placeholder = "Поиск",
                icon = Icons.Default.Search,
                iconContentDescription = "iconSearch"
            )
        }

    }
}