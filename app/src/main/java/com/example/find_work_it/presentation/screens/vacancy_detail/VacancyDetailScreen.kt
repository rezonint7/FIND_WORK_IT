package com.example.find_work_it.presentation.screens.vacancy_detail

import android.os.Build
import android.text.Html
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.find_work_it.R
import com.example.find_work_it.domain.model.Vacancy
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
    val statePutFavorite = detailVacancyViewModel.statePut.value
    val stateDeleteFavorite = detailVacancyViewModel.stateDelete.value
    Column(modifier = modifier) {
        state.vacancy?.let { vacancy ->
            TopBarDetailVacancy(detailViewModel = detailVacancyViewModel, vacancy.idVacancy)
            LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(20.dp)){
                item {
                    // Add a header as a separate item before the main items
                    Text(
                        text = "Vacancy Details",
                        style = MainTheme.typography.headerText,
                        color = MainTheme.colors.primaryText,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
                item{
                    Row(
                        modifier = Modifier.padding(4.dp)
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
                }
                item {

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
            AddToFavoriteButton { clicked ->
                if(clicked)
                    detailViewModel.putFavoriteVacancy(vacancyId)
                else
                    detailViewModel.deleteFavoriteVacancy(vacancyId)
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