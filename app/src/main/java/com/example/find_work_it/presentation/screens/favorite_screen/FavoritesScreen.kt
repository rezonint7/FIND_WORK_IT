package com.example.find_work_it.presentation.screens.favorite_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.find_work_it.presentation.navigation.NavScreens
import com.example.find_work_it.presentation.screens.ButtonElement
import com.example.find_work_it.presentation.screens.main_screen.MainScreenViewModel
import com.example.find_work_it.presentation.screens.main_screen.VacancyItem
import com.example.find_work_it.ui.theme.MainTheme

@Composable
fun FavoritesScreen(
    controller: NavController,
    favoritesScreenViewModel: FavoritesScreenViewModel = hiltViewModel()
){
    Column(modifier = Modifier.fillMaxSize()) {
        val state = favoritesScreenViewModel.state.value
        if(state.error.isBlank()){
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp)
                .layoutId("lazyColumn")){
                items(state.vacancies){vacancy ->
                    VacancyItem(vacancy = vacancy, onItemClick = {
                        controller.navigate(NavScreens.VacancyDetailScreen.route + "/${vacancy.idVacancy}")
                    })
                }
            }
        }
    }

}