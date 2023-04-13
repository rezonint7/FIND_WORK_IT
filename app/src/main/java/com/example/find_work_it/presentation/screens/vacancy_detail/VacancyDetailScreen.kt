package com.example.find_work_it.presentation.screens.vacancy_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.find_work_it.ui.theme.MainTheme

@Composable
fun VacancyDetailScreen(
    navController: NavController,
    detailVacancyViewModel: VacancyDetailViewModel = hiltViewModel()
){
    val constraintLayoutModifier = Modifier
        .fillMaxSize()
        .background(MainTheme.colors.primaryBackground)
    val state = detailVacancyViewModel.state.value
    ConstraintLayout(modifier = constraintLayoutModifier) {
        state.vacancy?.let {
            LazyColumn(modifier = Modifier.fillMaxSize()){

            }
        }
    }
}