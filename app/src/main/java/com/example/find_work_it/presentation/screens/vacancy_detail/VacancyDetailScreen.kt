package com.example.find_work_it.presentation.screens.vacancy_detail

import android.annotation.SuppressLint
import android.os.Build
import android.text.Html
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.find_work_it.R
import com.example.find_work_it.domain.model.Vacancy
import com.example.find_work_it.presentation.navigation.NavScreens
import com.example.find_work_it.presentation.screens.ButtonElement
import com.example.find_work_it.presentation.screens.EmployerInfoItem
import com.example.find_work_it.presentation.screens.favorite_screen.BackPressHandler
import com.example.find_work_it.presentation.screens.favorite_screen.FavoritesScreenViewModel
import com.example.find_work_it.presentation.screens.main_screen.VacancyItem
import com.example.find_work_it.ui.theme.FINDWORKIT_Theme
import com.example.find_work_it.ui.theme.MainTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "RememberReturnType")
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun VacancyDetailScreen(
    controller: NavController,
    detailVacancyViewModel: VacancyDetailViewModel = hiltViewModel(),
    favoritesScreenViewModel: FavoritesScreenViewModel = hiltViewModel()
){
    val state = detailVacancyViewModel.state.value
    val stateSimilarVacancies = detailVacancyViewModel.stateSimilarVacancies.value
    val statePutFavorite = favoritesScreenViewModel.statePutFavorite.value
    val stateDeleteFavorite = favoritesScreenViewModel.stateDeleteFavorite.value

    val context = LocalContext.current
    val onBack = {
        controller.popBackStack()
        favoritesScreenViewModel.updateFavoritesVacancies()
        Toast.makeText(context, "Дерьмо", Toast.LENGTH_SHORT).show()
    }
    BackPressHandler(onBackPressed = onBack)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MainTheme.colors.primaryBackground,
        topBar = {state.vacancy?.let { TopBarDetailVacancy(favoritesScreenViewModel, detailVacancyViewModel, it.idVacancy) }},
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
                    Spacer(modifier = Modifier.height(28.dp))
                }
                item{
                    Divider(modifier = Modifier.fillMaxWidth(), color = MainTheme.colors.strokeColor)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(id = R.string.simillar_vacancies),
                        style = MainTheme.typography.headerText,
                        color = MainTheme.colors.primaryText
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                items(stateSimilarVacancies.vacancies){ vacancy ->
                    VacancyItem(vacancy = vacancy, onItemClick = {
                        controller.navigate(NavScreens.VacancyDetailScreen.route + "/${vacancy.idVacancy}")
                    })
                }
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(modifier = Modifier.fillMaxWidth(), color = MainTheme.colors.strokeColor)
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
fun TopBarDetailVacancy(
    favoritesScreenViewModel: FavoritesScreenViewModel,
    vacancyDetailViewModel: VacancyDetailViewModel,
    vacancyId: String
){
    val favoritesList = favoritesScreenViewModel.state.value.vacancies
    val backPressedDispatcher = LocalOnBackPressedDispatcherOwner.current
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
            AddToFavoriteButton(vacancyDetailViewModel, favoritesList) { clicked ->
                if(clicked) {
                    favoritesScreenViewModel.putFavoriteVacancy(vacancyId)
                }
                else favoritesScreenViewModel.deleteFavoriteVacancy(vacancyId)
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
fun AddToFavoriteButton(
    vacancyDetailViewModel: VacancyDetailViewModel,
    favoritesList: MutableList<Vacancy>,
    onItemClick: (isClicked: Boolean) -> Unit
){
    var isClicked by remember { mutableStateOf(favoritesList.any { it.idVacancy == vacancyDetailViewModel.state.value.vacancy?.idVacancy }) }
    Log.d("FAVBUTTON", isClicked.toString())
    LaunchedEffect(favoritesList) {
        isClicked = favoritesList.any { it.idVacancy == vacancyDetailViewModel.state.value.vacancy?.idVacancy }
    }
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