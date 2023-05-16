package com.example.find_work_it.presentation.screens.add_resume_screen.info_user_elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.find_work_it.data.remote.dto.resumes.models.Value
import com.example.find_work_it.domain.model.ResumeDetail
import com.example.find_work_it.presentation.screens.AddStandardTextField
import com.example.find_work_it.ui.theme.MainTheme

@Composable
fun EditExtraInfoUser(title: String, resumeDetail: ResumeDetail){
    Column(modifier = Modifier.fillMaxSize()) {
        val lastName = remember { mutableStateOf(resumeDetail.lastName) }
        val firstName = remember { mutableStateOf(resumeDetail.firstName) }
        val middleName = remember { mutableStateOf(resumeDetail.middleName) }
        val phone = remember { mutableStateOf(resumeDetail.contact?.find { it.type?.id == "cell" }?.value.takeIf { it is Value }?.let { (it as Value).formatted }) }
        val area = remember{ mutableStateOf(resumeDetail.area?.name ?: "") }
        val birthDate = remember { mutableStateOf(resumeDetail.birthDate) }

        Text(
            text = title,
            style = MainTheme.typography.headerText,
            color = MainTheme.colors.primaryText,
        )
        Spacer(modifier = Modifier.height(8.dp))
        AddStandardTextField(
            message = lastName.value,
            hint = "Укажите фамилию",
            label = "Фамилия",
            onValueChanged = {lastName.value = it}
        )
        Spacer(modifier = Modifier.height(8.dp))
        AddStandardTextField(
            message = firstName.value,
            hint = "Укажите имя",
            label = "Имя",
            onValueChanged = {firstName.value = it}
        )
        Spacer(modifier = Modifier.height(8.dp))
        AddStandardTextField(
            message = middleName.value ?: "",
            hint = "Укажите отчество",
            label = "Отчество",
            onValueChanged = {middleName.value = it}
        )
        Spacer(modifier = Modifier.height(8.dp))
        AddStandardTextField(
            message = phone.value ?: "",
            hint = "Укажите номер телефона",
            label = "Номер телефона",
            onValueChanged = {phone.value = it}
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExposedDropdownMenuBoxItem(){
    var expanded by remember { mutableStateOf(false) }

    val listItems = arrayOf("Favorites", "Options", "Settings", "Share")


    // remember the selected item
    var selectedItem by remember { mutableStateOf(listItems[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            value = selectedItem,
            onValueChange = { selectedItem = it },
            label = { Text(text = "Label") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        // filter options based on text field value
        val filteringOptions =
            listItems.filter { it.contains(selectedItem, ignoreCase = true) }

        if (filteringOptions.isNotEmpty()) {
            // menu
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                // this is a column scope
                // all the items are added vertically
                filteringOptions.forEach { selectionOption ->
                    // menu item
                    DropdownMenuItem(
                        onClick = {
                            selectedItem = selectionOption
                            expanded = false
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }
    }
}