package com.example.find_work_it.presentation.screens.profile_screen

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.find_work_it.presentation.navigation.NavScreens
import com.example.find_work_it.presentation.screens.ButtonElement
import com.example.find_work_it.ui.theme.FINDWORKIT_Theme
import com.example.find_work_it.ui.theme.MainTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    //controller: NavController,
){
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Кунавин Антон Вячеславович",
                style = MainTheme.typography.headerText,
                color = MainTheme.colors.primaryText
            )
            Text(
                text = "rezonint@gmail.com",
                style = MainTheme.typography.headerText,
                color = MainTheme.colors.primaryText
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth()){
            item {
                ButtonElement(
                    text = "Добавить резюме",
                    modifier = Modifier.padding(8.dp),
                    backgroundColor = MainTheme.colors.auxiliaryColor
                ) {
                    //navigate to add
                }
            }
        }
    }
}

@Composable
fun ResumeItem(){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp)
        .clickable { }, elevation = 8.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth().background(MainTheme.colors.secondaryBackground).height(height = 88.dp).padding(8.dp)) {
            Text(
                text = "Kotlin Android Developer",
                style = MainTheme.typography.bodyText1,
                color = MainTheme.colors.primaryText
            )
            Text(
                text = "700000",
                style = MainTheme.typography.bodyText1,
                color = MainTheme.colors.primaryText
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview(){
    FINDWORKIT_Theme() {
        ResumeItem()
    }
}