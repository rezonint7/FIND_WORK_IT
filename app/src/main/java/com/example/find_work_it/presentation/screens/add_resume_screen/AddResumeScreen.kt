package com.example.find_work_it.presentation.screens.add_resume_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.find_work_it.R
import com.example.find_work_it.data.remote.dto.resumes.models.Value
import com.example.find_work_it.domain.model.ResumeDetail
import com.example.find_work_it.presentation.screens.TopBar
import com.example.find_work_it.presentation.screens.vacancy_detail.AddResponseButton
import com.example.find_work_it.presentation.screens.vacancy_detail.TopBarDetailVacancy
import com.example.find_work_it.ui.theme.MainTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddResumeScreen(
    controller: NavController,
    addResumeScreenViewModel: AddResumeScreenViewModel = hiltViewModel()
){
    val state = addResumeScreenViewModel.state.value
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MainTheme.colors.primaryBackground,
        topBar = {state.resume?.let { TopBar(it.title ?: "Должность не указана") }}
    ){
        state.resume?.let { resume ->
            LazyColumn(modifier = Modifier.fillMaxSize()){
                item {
                    MainInfoUserBlock(resumeDetail = resume)
                }
            }
        }
    }
}

@Composable
fun MainInfoUserBlock(resumeDetail: ResumeDetail){
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Основная информация",
            style = MainTheme.typography.headerText,
            color = MainTheme.colors.primaryText
        )
        Divider(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), color = MainTheme.colors.strokeColor)
        Row(Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.width(308.dp)) {
                val middleName = resumeDetail.middleName ?: "Отчество не указано"
                val birthDate = resumeDetail.birthDate ?: "не указано"
                val mobilePhone = resumeDetail.contact?.find { it.type?.id == "cell" }?.value.takeIf { it is Value }?.let { (it as Value).formatted } ?: "Номер телефона не указан"
                Text(
                    text = "${resumeDetail.lastName} ${resumeDetail.firstName} $middleName",
                    style = MainTheme.typography.bodyText1,
                    color = MainTheme.colors.primaryText
                )
                Text(
                    text = mobilePhone,
                    style = MainTheme.typography.bodyText1,
                    color = MainTheme.colors.primaryText
                )
                Text(
                    text = resumeDetail.area?.name.toString(),
                    style = MainTheme.typography.bodyText1,
                    color = MainTheme.colors.primaryText
                )
                Text(
                    text = resumeDetail.area?.name.toString(),
                    style = MainTheme.typography.bodyText1,
                    color = MainTheme.colors.primaryText
                )

                Text(
                    text = "Дата рождения: $birthDate",
                    style = MainTheme.typography.bodyText1,
                    color = MainTheme.colors.primaryText
                )
            }
            Column() {
                Image(
                    painter = painterResource(id = R.drawable.round_arrow_forward_ios_24),
                    colorFilter = ColorFilter.tint(MainTheme.colors.secondaryText),
                    contentDescription = ""
                )
            }
        }
        Divider(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), color = MainTheme.colors.strokeColor)
    }
}
