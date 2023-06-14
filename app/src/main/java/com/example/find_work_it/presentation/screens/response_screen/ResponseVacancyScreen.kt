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
import androidx.compose.foundation.lazy.items
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.find_work_it.domain.model.Response
import com.example.find_work_it.presentation.screens.ErrorUseCaseElement
import com.example.find_work_it.presentation.screens.LoadingUseCaseElement
import com.example.find_work_it.presentation.screens.TopBar
import com.example.find_work_it.ui.theme.MainTheme
import com.example.find_work_it.ui.theme.Shapes

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ResponsesVacancyScreen(responseVacancyScreenViewModel: ResponseVacancyScreenViewModel = hiltViewModel()){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MainTheme.colors.primaryBackground,
        topBar = { TopBar(screenName = "Отклики")}
    ) {
        val state = responseVacancyScreenViewModel.state.value
        if(state.error.isNotBlank()){
            ErrorUseCaseElement(error = state.error) {

            }
        }
        if(state.isLoading){
            LoadingUseCaseElement()
        }
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(state.responses){
                ResponseItem(it)
            }
            item{
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}

@Composable
fun ResponseItem(response: Response){
    val responseStatusColor = when(response.status?.id){
        "invitation" -> MainTheme.colors.approvedColor
        "response" -> MainTheme.colors.auxiliaryColor
        "discard" -> MainTheme.colors.refusedColor
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
                        text = response.vacancy?.name.toString(),
                        style = MainTheme.typography.headerText,
                        color = MainTheme.colors.primaryText
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${response.vacancy?.area?.name.toString()}, ${response.vacancy?.employer?.name.toString()}",
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
                            text = response.status?.name.toString(),
                            style = MainTheme.typography.bodyText1,
                            color = responseStatusColor
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Обновлено ${response.updatedAt}",
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