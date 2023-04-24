package com.example.find_work_it.presentation.screens.vacancy_detail

import android.annotation.SuppressLint
import android.os.Build
import android.text.Html
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.find_work_it.R
import com.example.find_work_it.presentation.screens.ButtonElement
import com.example.find_work_it.presentation.screens.EmployerInfoItem
import com.example.find_work_it.ui.theme.FINDWORKIT_Theme
import com.example.find_work_it.ui.theme.MainTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun VacancyDetailScreen(
    navController: NavController,
    detailVacancyViewModel: VacancyDetailViewModel = hiltViewModel()
){
    val state = detailVacancyViewModel.state.value
    val statePutFavorite = detailVacancyViewModel.statePutFavorite.value
    val stateDeleteFavorite = detailVacancyViewModel.stateDeleteFavorite.value
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MainTheme.colors.primaryBackground,
        topBar = {state.vacancy?.let { TopBarDetailVacancy(detailViewModel = detailVacancyViewModel, it.idVacancy) }},
        bottomBar = {state.vacancy?.let { AddResponseButton(detailVacancyViewModel, it.idVacancy) }}
    ) {
        state.vacancy?.let { vacancy ->
            LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(18.dp)){
                item{
                    Row(
                        modifier = Modifier.padding(horizontal = 4.dp)
                    ){
                        Text(
                            text = vacancy.nameVacancy,
                            style = MainTheme.typography.headerText,
                            color = MainTheme.colors.primaryText
                        )
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
                    EmployerInfoItem(vacancy = vacancy) {

                    }
                }
                item {
                    val annotatedString = AnnotatedString.Builder().append(Html.fromHtml(vacancy.description)).toAnnotatedString()
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = annotatedString,
                        style = MainTheme.typography.bodyText1,
                        color = MainTheme.colors.primaryText
                    )
                    Spacer(modifier = Modifier.height(48.dp))
                }
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

@Composable
fun TopBarDetailVacancy(detailViewModel: VacancyDetailViewModel, vacancyId: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MainTheme.colors.primaryBackground)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        val imageModifier = Modifier.size(34.dp)
        Row(modifier = Modifier
            .size(34.dp)
            .clickable { }) {
            Image(
                painter = painterResource(id = R.drawable.round_arrow_back_24),
                contentDescription = "arrowBack",
                colorFilter = ColorFilter.tint(MainTheme.colors.secondaryText),
                modifier = imageModifier
            )
        }
        Row(modifier = Modifier.padding(horizontal = 8.dp)) {
            AddToFavoriteButton(detailViewModel) { clicked ->
                if(clicked) detailViewModel.putFavoriteVacancy(vacancyId)
                else detailViewModel.deleteFavoriteVacancy(vacancyId)
                Log.d("delFav1", detailViewModel.stateDeleteFavorite.value.success.toString())
                Log.d("delFav1", detailViewModel.stateDeleteFavorite.value.error)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Row(modifier = Modifier
                .size(34.dp)
                .clickable { }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.round_share_24),
                    contentDescription = "share",
                    colorFilter = ColorFilter.tint(MainTheme.colors.secondaryText),
                    modifier = imageModifier
                )
            }
        }
    }
}

@Composable
fun AddToFavoriteButton(vacancyDetailViewModel: VacancyDetailViewModel, onItemClick: (isClicked: Boolean) -> Unit){
    var isClicked by remember { mutableStateOf(vacancyDetailViewModel.isFavorited.value) }
    Column(modifier = Modifier.size(34.dp)) {
        Image(
            painter = painterResource(id = R.drawable.round_favorite_screen_icon),
            contentDescription = "addFavorite",
            modifier = Modifier
                .size(34.dp)
                .clickable {
                    isClicked = !isClicked
                    onItemClick(isClicked)
                },
            colorFilter = if (isClicked) ColorFilter.tint(MainTheme.colors.refusedColor) else ColorFilter.tint(MainTheme.colors.secondaryText)
        )
    }
}


@Composable
fun AddResponseButton(detailVacancyViewModel: VacancyDetailViewModel, vacancyId: String){
    Row(modifier = Modifier
        .fillMaxWidth()
    ){
        ButtonElement(
            text = "Откликнуться",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(48.dp),
            backgroundColor = MainTheme.colors.auxiliaryColor
        ) {

        }
    }
}
@Preview(showBackground = true)
@Composable
fun preview(){
    FINDWORKIT_Theme() {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)){
            ButtonElement(
                text = "Откликнуться",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(48.dp),
                backgroundColor = MainTheme.colors.auxiliaryColor
            ) {

            }
        }
    }
}