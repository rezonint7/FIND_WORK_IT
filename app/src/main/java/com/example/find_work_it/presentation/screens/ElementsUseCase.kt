package com.example.find_work_it.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.find_work_it.R
import com.example.find_work_it.ui.theme.MainTheme

@Composable
fun LoadingUseCaseElement(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        CircularProgressIndicator(
            color = MainTheme.colors.auxiliaryColor
        )
    }
}

@Composable
fun ErrorUseCaseElement(error: String, onRetryClick: () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize().background(MainTheme.colors.primaryBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(64.dp),
            painter = painterResource(id = R.drawable.round_connection_failed),
            contentDescription = "connectionFailedIcon",
            colorFilter = ColorFilter.tint(color = MainTheme.colors.auxiliaryColor)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = error,
            color = MainTheme.colors.refusedColor,
            textAlign = TextAlign.Center,
        )
        ButtonElement(
            text = "Попробовать снова",
            modifier = Modifier.padding(all = 4.dp),
            backgroundColor = MainTheme.colors.refusedColor,
            onClick = {onRetryClick()}
        )
    }
}