package com.example.find_work_it.presentation.screens.response_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.find_work_it.presentation.screens.TopBar
import com.example.find_work_it.ui.theme.MainTheme
import com.example.find_work_it.ui.theme.Shapes

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ResponsesVacancyScreen(){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MainTheme.colors.primaryBackground,
        topBar = { TopBar(screenName = "Отклики")}
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()){
            item{
                ResponseItem("Отклик", "25.05.23 в 17:45")
                ResponseItem("Отказ", "15.05.23 в 11:31")
                ResponseItem("Приглашение", "23.05.23 в 13:15")
            }
        }
    }
}

@Composable
fun ResponseItem(responseStatus: String, date: String){
    val responseStatusColor = when(responseStatus){
        "Приглашение" -> MainTheme.colors.approvedColor
        "Отклик" -> MainTheme.colors.auxiliaryColor
        "Отказ" -> MainTheme.colors.refusedColor
        else -> MainTheme.colors.secondaryBackground
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 8.dp),
        elevation = 4.dp,
        shape = Shapes.small,
        backgroundColor = MainTheme.colors.secondaryBackground
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .border(border = BorderStroke(2.dp, responseStatusColor), shape = Shapes.small)
            .background(MainTheme.colors.secondaryBackground)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
                .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                    Text(
                        text = "Программист Python",
                        style = MainTheme.typography.headerText,
                        color = MainTheme.colors.primaryText
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Екатеринбург, СКБ Контур",
                        style = MainTheme.typography.bodyText1,
                        color = MainTheme.colors.primaryText
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(){
                        Text(
                            text = "Статус: ",
                            style = MainTheme.typography.bodyText1,
                            color = MainTheme.colors.primaryText
                        )
                        Text(
                            text = responseStatus,
                            style = MainTheme.typography.bodyText1,
                            color = responseStatusColor
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Обновлено $date",
                        style = MainTheme.typography.smallText,
                        color = MainTheme.colors.secondaryText
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview(){

}