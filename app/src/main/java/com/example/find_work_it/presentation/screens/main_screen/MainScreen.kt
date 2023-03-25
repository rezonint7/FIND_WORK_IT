package com.example.find_work_it.presentation.screens.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.find_work_it.BuildConfig
import com.example.find_work_it.Greeting
import com.example.find_work_it.presentation.screens.AddBasicTextField
import com.example.find_work_it.presentation.screens.FilterButton
import com.example.find_work_it.ui.theme.BasicTextFieldStyle


@Composable
fun MainScreen(){
    Column(Modifier.fillMaxSize()) {
        TopBar()
        //Greeting(name = "${BuildConfig.}")
    }
}

@Composable
fun TopBar(){
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.width(width = 8.dp))
        AddBasicTextField(
            sizeWidth = 254,
            sizeHeight = 48,
            textStyle = BasicTextFieldStyle,
            placeholder = "Поиск",
            icon = Icons.Default.Search,
            iconContentDescription = "iconSearch"
        )
        Spacer(modifier = Modifier.width(width = 8.dp))
        FilterButton(size = 48)

    }
}

