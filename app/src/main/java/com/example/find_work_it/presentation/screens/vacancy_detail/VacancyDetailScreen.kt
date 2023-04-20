package com.example.find_work_it.presentation.screens.vacancy_detail

import android.os.Build
import android.text.Html
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.find_work_it.R
import com.example.find_work_it.presentation.screens.AddToFavoriteButton
import com.example.find_work_it.presentation.screens.EmployerInfoItem
import com.example.find_work_it.ui.theme.MainTheme

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun VacancyDetailScreen(
    navController: NavController,
    detailVacancyViewModel: VacancyDetailViewModel = hiltViewModel()
){
    val modifier = Modifier
        .fillMaxSize()
        .background(MainTheme.colors.primaryBackground)
    val state = detailVacancyViewModel.state.value
    Column(modifier = modifier) {
        state.vacancy?.let { vacancy ->
            LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(20.dp)){
                item{
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                        Text(
                            text = vacancy.nameVacancy,
                            style = MainTheme.typography.headerText,
                            color = MainTheme.colors.primaryText
                        )
                        AddToFavoriteButton {

                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = vacancy.salary?.from.toString(),
                        style = MainTheme.typography.bodyText1,
                        color = MainTheme.colors.primaryText
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    EmployerInfoItem(vacancyDetailEmployer = vacancy.employer!!) {

                    }
                }
                item {
                    val annotatedString = AnnotatedString.Builder().append(Html.fromHtml(vacancy.description)).toAnnotatedString()
                    Text(
                        text = annotatedString,
                        style = MainTheme.typography.bodyText1,
                        color = MainTheme.colors.primaryText
                    )
                }
            }
        }
        if(state.error.isNotBlank()){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = state.error,
                    color = MainTheme.colors.refusedColor,
                    textAlign = TextAlign.Center,
                )
            }
        }
        if(state.isLoading){
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                CircularProgressIndicator(
                    modifier = Modifier,
                    color = MainTheme.colors.auxiliaryColor
                )
            }
        }
    }
}