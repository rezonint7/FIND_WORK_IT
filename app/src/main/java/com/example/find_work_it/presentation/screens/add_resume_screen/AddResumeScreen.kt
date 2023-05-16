package com.example.find_work_it.presentation.screens.add_resume_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.find_work_it.R
import com.example.find_work_it.data.remote.dto.resumes.models.Value
import com.example.find_work_it.domain.model.ResumeDetail
import com.example.find_work_it.presentation.screens.AddStandardTextField
import com.example.find_work_it.presentation.screens.ButtonElement
import com.example.find_work_it.presentation.screens.TopBar
import com.example.find_work_it.presentation.screens.add_resume_screen.info_user_elements.EditMainInfoUser
import com.example.find_work_it.ui.theme.MainTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddResumeScreen(
    controller: NavController,
    addResumeScreenViewModel: AddResumeScreenViewModel = hiltViewModel()
){
    val state = addResumeScreenViewModel.state.value
    val suggests by addResumeScreenViewModel.suggestPositions.observeAsState(initial = SuggestPositionState())
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MainTheme.colors.primaryBackground,
        topBar = {state.resume?.let { TopBar(it.title ?: "Должность не указана") }},
        bottomBar = {state.resume?.let { ButtonElement(
            text = "Сохранить",
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 8.dp),
            backgroundColor = MainTheme.colors.auxiliaryColor
        ) {
            
        }}}
    ){
        state.resume?.let { resume ->
            val resumeTitle = remember {mutableStateOf(resume.title)}
            Log.d("ID", resume.resumeId)
            LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(18.dp)){
                item{
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(modifier = Modifier.border(
                            1.dp,
                            MainTheme.colors.auxiliaryColor,
                            RoundedCornerShape(100.dp)
                        )){
                            val image = painterResource(id = R.drawable.photo_resume_test)
                            Image(painter = image, contentDescription = "", modifier = Modifier
                                .clip(RoundedCornerShape(100.dp))
                                .size(98.dp)
                                .scale(1.6f))
                        }
                    }
                }
                item {
                    Text(
                        text = "Желаемая должность: ",
                        style = MainTheme.typography.headerText,
                        color = MainTheme.colors.primaryText,
                        modifier = Modifier.padding(8.dp)
                    )
                    AddStandardTextField(
                        message = resumeTitle.value ?: "",
                        hint = "Укажите должность",
                        label = "Должность",
                        onValueChanged = {
                            resumeTitle.value = it
                            //addResumeScreenViewModel.getSuggestsPosition(it)
                        }
                    )
                }
                item {
                    EditMainInfoUser(title = "Основная информация", resumeDetail = resume)
                }
            }
        }
    }
}

@Composable
fun MainInfoUserBlock(resumeDetail: ResumeDetail, onItemClick: () -> Unit){
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Основная информация",
            style = MainTheme.typography.headerText,
            color = MainTheme.colors.primaryText,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider(modifier = Modifier.fillMaxWidth(), color = MainTheme.colors.strokeColor)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .clickable { onItemClick },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.width(298.dp), verticalArrangement = Arrangement.Center) {
                val middleName = resumeDetail.middleName ?: ""
                val birthDate = resumeDetail.birthDate ?: "не указано"
                val city = resumeDetail.area?.name ?: "Город проживания не указан"
                val mobilePhone = resumeDetail.contact?.find { it.type?.id == "cell" }?.value.takeIf { it is Value }?.let { (it as Value).formatted } ?: "Номер телефона не указан"
                Text(
                    text = "${resumeDetail.lastName} ${resumeDetail.firstName} $middleName",
                    style = MainTheme.typography.bodyText1,
                    color = MainTheme.colors.primaryText
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = mobilePhone,
                    style = MainTheme.typography.bodyText1,
                    color = MainTheme.colors.primaryText
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = city,
                    style = MainTheme.typography.bodyText1,
                    color = MainTheme.colors.primaryText
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = resumeDetail.area?.name.toString(),
                    style = MainTheme.typography.bodyText1,
                    color = MainTheme.colors.primaryText
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Дата рождения: $birthDate",
                    style = MainTheme.typography.bodyText1,
                    color = MainTheme.colors.primaryText
                )
            }
            Column(
                modifier = Modifier.height(88.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.round_arrow_forward_ios_24),
                    colorFilter = ColorFilter.tint(MainTheme.colors.secondaryText),
                    modifier = Modifier.size(28.dp),
                    contentDescription = ""
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider(modifier = Modifier.fillMaxWidth(), color = MainTheme.colors.strokeColor)
    }
}
