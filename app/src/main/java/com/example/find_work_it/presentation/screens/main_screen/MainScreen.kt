package com.example.find_work_it.presentation.screens.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.find_work_it.R
import com.example.find_work_it.domain.model.Vacancy
import com.example.find_work_it.presentation.navigation.NavScreens
import com.example.find_work_it.presentation.screens.*
import com.example.find_work_it.ui.theme.FINDWORKIT_Theme
import com.example.find_work_it.ui.theme.MainTheme
import com.example.find_work_it.ui.theme.Shapes


@Suppress("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    controller: NavController,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel()
){
    val state = mainScreenViewModel.state.value
    val stateExtra = mainScreenViewModel.extra.value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarMainScreen()},
        backgroundColor = MainTheme.colors.primaryBackground
    ) {
        if(state.error.isBlank() && stateExtra.error.isBlank()){
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp)){
                items(state.vacancies){vacancy ->
                    VacancyItem(vacancy = vacancy, onItemClick = {
                        controller.navigate(NavScreens.VacancyDetailScreen.route + "/${vacancy.idVacancy}")
                    })
                }
                item {
                    if(stateExtra.isLoading){
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(color = MainTheme.colors.auxiliaryColor)
                        }
                    }else{
                        if(state.vacancies.isNotEmpty()){
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .size(48.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                ButtonElement(text = "Показать еще", modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .padding(horizontal = 8.dp),
                                    backgroundColor = MainTheme.colors.auxiliaryColor
                                ) {
                                    mainScreenViewModel.getExtraVacancies()
                                }
                            }
                        }
                    }
                }
            }
            if(state.isLoading){
                LoadingUseCaseElement()
            }

        }
        else if (state.error.isNotBlank() || stateExtra.error.isNotBlank()){
            val error = state.error.ifBlank { stateExtra.error }
            ErrorUseCaseElement(error = error) {
                
            }
        }
    }
}

@Composable
fun TopBarMainScreen(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MainTheme.colors.primaryBackground)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        AddBasicTextField(
            sizeWidth = 274,
            sizeHeight = 48,
            textStyle = MainTheme.typography.inputTextField,
            placeholder = "Поиск",
            icon = Icons.Default.Search,
            iconContentDescription = "iconSearch"
        )
        FilterButton(size = 48, onClick = {

        })

    }
}

@Composable
fun VacancyItem(
    vacancy: Vacancy,
    onItemClick: (Vacancy) -> Unit
){
    val rowModifier = Modifier
        .fillMaxWidth()
        .padding(start = 8.dp, end = 8.dp, top = 8.dp)
    val columnMainModifier = Modifier
        .wrapContentSize()
        .padding(8.dp)
        .background(MainTheme.colors.secondaryBackground, Shapes.small)
        .clickable { onItemClick(vacancy) }
    val boxModifier = Modifier
        .size(48.dp)
        .background(Color.LightGray)

    Column(modifier = columnMainModifier) {
        Row(
            modifier = rowModifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = vacancy.nameVacancy,
                style = MainTheme.typography.headerText,
                color = MainTheme.colors.primaryText
            )
        }
        if(vacancy.salary != null){
            Row(modifier = rowModifier){
                val salary = when{
                    (vacancy.salary.from != null && vacancy.salary.to != null) -> "от ${vacancy.salary.from} до ${vacancy.salary.to}"
                    (vacancy.salary.to == vacancy.salary.from) -> vacancy.salary.to.toString()
                    (vacancy.salary.from != null) -> "от ${vacancy.salary.from}"
                    (vacancy.salary.to != null) -> "до ${vacancy.salary.to}"
                    else -> {""}
                }
                val currency = when(vacancy.salary.currency){
                    "RUR" -> "₽"
                    "USD" -> "$"
                    "EUR" -> "€"

                    else -> vacancy.salary.currency
                }
                Text(
                    text = "$salary $currency",
                    style = MainTheme.typography.bodyText2,
                    color = MainTheme.colors.primaryText
                )
            }
        }
        Row(modifier = rowModifier, verticalAlignment = Alignment.CenterVertically){
            if(vacancy.employer != null){
                val area = if (vacancy.areaName != null) "${vacancy.areaName}, ${vacancy.employer.name.toString()}" else vacancy.employer.name.toString()
                Text(
                    text = area,
                    style = MainTheme.typography.bodyText1,
                    color = MainTheme.colors.primaryText
                )
                if(vacancy.employer.trusted!!){
                    Image(
                        modifier = Modifier
                            .size(24.dp)
                            .padding(top = 4.dp, bottom = 2.dp),
                        painter = painterResource(id = R.drawable.baseline_check_circle_outline_24),
                        contentDescription = "checkEmployer",
                        colorFilter = ColorFilter.tint(color = MainTheme.colors.auxiliaryColor)
                    )
                }
            }
        }
        Row(modifier = rowModifier) {
            Text(
                text = "Опубликовано: ${vacancy.publishDate}",
                style = MainTheme.typography.smallText,
                color = MainTheme.colors.secondaryText,
                modifier = Modifier.padding(horizontal = 0.dp, vertical = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FINDWORKIT_Theme(darkTheme = true) {
        ErrorUseCaseElement(error = "Что - то пошло не так ... Проверьте подключение к интернету") {

        }
    }
}

