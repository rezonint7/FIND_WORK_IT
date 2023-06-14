package com.example.find_work_it.presentation.screens.vacancy_detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.text.Html
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
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
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.find_work_it.R
import com.example.find_work_it.domain.model.Resume
import com.example.find_work_it.domain.model.Vacancy
import com.example.find_work_it.presentation.navigation.NavScreens
import com.example.find_work_it.presentation.screens.ButtonElement
import com.example.find_work_it.presentation.screens.EmployerInfoItem
import com.example.find_work_it.presentation.screens.favorite_screen.BackPressHandler
import com.example.find_work_it.presentation.screens.favorite_screen.FavoritesScreenViewModel
import com.example.find_work_it.presentation.screens.main_screen.VacancyItem
import com.example.find_work_it.ui.theme.FINDWORKIT_Theme
import com.example.find_work_it.ui.theme.MainTheme
import com.example.find_work_it.ui.theme.Shapes

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

    val suitableResumesState = detailVacancyViewModel.resumes.value
    val responseVacancy = detailVacancyViewModel.responseVacancy.value
    val context = LocalContext.current
    val isNavigated = remember { mutableStateOf(false) }
    val onBack = {
        if(!isNavigated.value){
            isNavigated.value = true
            controller.popBackStack()
        }
    }

    val dialogState by lazy { mutableStateOf(false) }
    val shareLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ -> }
    BackPressHandler(onBackPressed = {onBack()})
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MainTheme.colors.primaryBackground,
        topBar = {state.vacancy?.let { TopBarDetailVacancy(
            favoritesScreenViewModel,
            detailVacancyViewModel,
            it.idVacancy,
            onBackClick = {onBack()},
            onShareClick = {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, it)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                shareLauncher.launch(shareIntent)
            }
        ) }},
        bottomBar = {state.vacancy?.let { AddResponseButton(){
            dialogState.value = true
        }}}
    ) {
        if(dialogState.value){
            SingleSelectDialog("Выберите резюме", suitableResumesState.resumes.filter { it.status?.id == "published" }, onDismissRequest = { dialogState.value = false }){
                detailVacancyViewModel.responseVacancy(it.id, state.vacancy?.idVacancy.toString())
            }
        }
        if(responseVacancy.error.isNotBlank()){
            Toast.makeText(context, responseVacancy.error.toString(), Toast.LENGTH_SHORT).show()
        }

        else if(responseVacancy.success && !isNavigated.value){
            Toast.makeText(context, "Отклик создан", Toast.LENGTH_SHORT).show()
            isNavigated.value = true
            controller.navigate(NavScreens.ResponsesVacancyScreen.route)
        }
        state.vacancy?.let { vacancy ->
            LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(18.dp)){
                item{
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Text(
                            text = vacancy.nameVacancy,
                            style = MainTheme.typography.headerText,
                            color = MainTheme.colors.primaryText
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    if(vacancy.salary != null){
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
                            style = MainTheme.typography.bodyText1,
                            color = MainTheme.colors.primaryText
                        )
                    }
                    else{
                        Text(
                            text = "Уровень дохода: не указан",
                            style = MainTheme.typography.bodyText1,
                            color = MainTheme.colors.primaryText
                        )
                    }
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
                if(stateSimilarVacancies.vacancies.isNotEmpty()){
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
fun SingleSelectDialog(title: String, optionsList: List<Resume>, onDismissRequest: () -> Unit, onValueSelected: (Resume) -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest.invoke() }) {
        Surface(modifier = Modifier.width(300.dp),
            color = MainTheme.colors.primaryBackground,
            shape = RoundedCornerShape(10.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(modifier = Modifier.padding(10.dp)) {
                item{
                    Text(
                        text = title,
                        style = MainTheme.typography.headerText,
                        color = MainTheme.colors.primaryText,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                items(optionsList){
                    ResumeItem(resume = it) {
                        onValueSelected(it)
                    }
                }
            }
        }
    }
}

@Composable
fun ResumeItem(resume: Resume, onItemClick: () -> Unit){
    Card(modifier = Modifier
        .wrapContentSize()
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .clickable { onItemClick() },
        elevation = 10.dp,
        shape = Shapes.small
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(MainTheme.colors.secondaryBackground)
            .padding(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween){
                    val title = resume.title ?: "Должность не указана"
                    Text(
                        text = title,
                        style = MainTheme.typography.headerText,
                        color = MainTheme.colors.primaryText
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.Bottom){
                        Text(
                            text = resume.updatedAt.toString(),
                            style = MainTheme.typography.smallText,
                            color = MainTheme.colors.secondaryText
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun TopBarDetailVacancy(
    favoritesScreenViewModel: FavoritesScreenViewModel,
    vacancyDetailViewModel: VacancyDetailViewModel,
    vacancyId: String,
    onBackClick: () -> Unit,
    onShareClick: (String) -> Unit,
){
    val favoritesList = favoritesScreenViewModel.state.value?.vacancies
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
            .clickable { onBackClick() }) {
            Image(
                painter = painterResource(id = R.drawable.round_arrow_back_24),
                contentDescription = "arrowBack",
                colorFilter = ColorFilter.tint(MainTheme.colors.secondaryText),
                modifier = imageModifier
            )
        }
        Row(modifier = Modifier.padding(horizontal = 8.dp)) {
            AddToFavoriteButton(vacancyDetailViewModel, favoritesList!!) { clicked ->
                if(clicked) {
                    favoritesScreenViewModel.putFavoriteVacancy(vacancyId)
                }
                else favoritesScreenViewModel.deleteFavoriteVacancy(vacancyId)
            }
            val vacancyUrl = vacancyDetailViewModel.state.value.vacancy?.url
            Spacer(modifier = Modifier.width(16.dp))
            Row(modifier = Modifier
                .size(34.dp)
                .clickable { onShareClick(vacancyUrl.toString()) }
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
fun AddResponseButton(onItemClick: () -> Unit){
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
            onItemClick()
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