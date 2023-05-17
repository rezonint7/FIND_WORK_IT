package com.example.find_work_it.presentation.screens.favorite_screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.find_work_it.presentation.navigation.NavScreens
import com.example.find_work_it.presentation.screens.ButtonElement
import com.example.find_work_it.presentation.screens.TopBar
import com.example.find_work_it.presentation.screens.main_screen.MainScreenViewModel
import com.example.find_work_it.presentation.screens.main_screen.VacancyItem
import com.example.find_work_it.ui.theme.MainTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavoritesScreen(
    controller: NavController,
    favoritesScreenViewModel: FavoritesScreenViewModel = hiltViewModel()
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MainTheme.colors.primaryBackground,
        topBar = { TopBar(screenName = stringResource(id = NavScreens.FavoritesScreen.title))}
    ) {
        val state = favoritesScreenViewModel.state.observeAsState(initial = FavoritesScreenState()).value
        if(state.error.isBlank()){
            Log.d("FAVORITES", "TRUE")
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp)){
                items(state.vacancies){vacancy ->
                    VacancyItem(vacancy = vacancy, onItemClick = {
                        controller.navigate(NavScreens.VacancyDetailScreen.route + "/${vacancy.idVacancy}")
                    })
                    Log.d("FAVORITES", vacancy.nameVacancy)
                }
            }
        }
        if(state.isLoading){
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
        if (state.error.isNotBlank()){
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = state.error,
                    style = MainTheme.typography.headerText,
                    color = MainTheme.colors.secondaryText,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        else{
            if(state.vacancies.isEmpty()){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "Туть пусто...",
                        style = MainTheme.typography.headerText,
                        color = MainTheme.colors.secondaryText,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

            }
        }
    }
}

@Composable
fun BackPressHandler(
    backPressedDispatcher: OnBackPressedDispatcher? = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
    onBackPressed: () -> Unit
) {
    val currentOnBackPressed by rememberUpdatedState(newValue = onBackPressed)

    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }
        }
    }

    DisposableEffect(key1 = backPressedDispatcher) {
        backPressedDispatcher?.addCallback(backCallback)

        onDispose {
            backCallback.remove()
        }
    }
}