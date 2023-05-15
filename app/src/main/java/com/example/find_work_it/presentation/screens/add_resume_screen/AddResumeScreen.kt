package com.example.find_work_it.presentation.screens.add_resume_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.find_work_it.domain.model.ResumeDetail
import com.example.find_work_it.ui.theme.MainTheme

@Composable
fun AddResumeScreen(

){
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()){
            item {

            }
        }
    }
}

@Composable
fun MainInfoUserBlock(title: String, resumeDetail: ResumeDetail){
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MainTheme.typography.headerText,
            color = MainTheme.colors.primaryText
        )
        Row(Modifier.fillMaxWidth()) {
            Column() {
                val middleName = resumeDetail.middleName ?: ""
                val mobilePhone = resumeDetail.contact?.forEach { if(it.type?.id == "cell") it.value } // 123
                Text(
                    text = "${resumeDetail.lastName} ${resumeDetail.firstName} $middleName",
                    style = MainTheme.typography.bodyText1,
                    color = MainTheme.colors.primaryText
                )
                Text(
                    text = "$mobilePhone",
                    style = MainTheme.typography.bodyText1,
                    color = MainTheme.colors.primaryText
                )
                Text(
                    text = "${resumeDetail.lastName} ${resumeDetail.firstName} ${resumeDetail.middleName}",
                    style = MainTheme.typography.bodyText1,
                    color = MainTheme.colors.primaryText
                )
            }
            Column() {

            }
        }
    }
}