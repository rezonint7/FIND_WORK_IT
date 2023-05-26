package com.example.find_work_it.presentation.screens.add_resume_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.find_work_it.data.remote.dto.dictionary_areas.AreaX
import com.example.find_work_it.data.remote.dto.dictionary_professional_roles.Role
import com.example.find_work_it.data.remote.dto.resumes.models.Area
import com.example.find_work_it.data.remote.dto.resumes.models.Value
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Employment
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.ProfessionalRole
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Schedule
import com.example.find_work_it.domain.model.ResumeDetail
import com.example.find_work_it.presentation.screens.AddStandardTextField
import com.example.find_work_it.presentation.screens.ButtonElement
import com.example.find_work_it.presentation.screens.ErrorUseCaseElement
import com.example.find_work_it.presentation.screens.LoadingUseCaseElement
import com.example.find_work_it.presentation.screens.TopBar
import com.example.find_work_it.ui.theme.MainTheme


data class TextFieldState(
    val lastName: String = "",
    val firstName: String = "",
    val middleName: String = "",
    val phone: String = "",
    val aboutMe: String = ""
)
data class TextFieldStateError(
    val lastNameError: String = "",
    val firstNameError: String = "",
    val middleNameError: String = "",
    val phoneError: String = "",
)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddResumeScreen(
    controller: NavController,
    addResumeScreenViewModel: AddResumeScreenViewModel = hiltViewModel()
){
    val state = addResumeScreenViewModel.state.value
    val areasState = addResumeScreenViewModel.areasDictionaryState.value
    val dictionariesState = addResumeScreenViewModel.dictionariesState.value
    val professionalRolesState = addResumeScreenViewModel.professionalRoles.value
    val suggests by addResumeScreenViewModel.suggestPositions.observeAsState(initial = SuggestPositionState())

    var changedStateResume = state.resume?.copy()

    val area = remember{ mutableStateOf(state.resume?.area) }

    val birthDate = remember { mutableStateOf(state.resume?.birthDate) }

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
            
        }}
        }
    ){
        state.resume?.let { resume ->
            val resumeTitle = remember {mutableStateOf(resume.title)}

            val resumeStateValidation = remember {
                mutableStateOf(
                    TextFieldState(
                        lastName = state.resume.lastName ?: "",
                        firstName = state.resume.firstName ?: "",
                        middleName = state.resume.middleName ?: "",
                        phone = state.resume.contact?.find { it.type?.id == "cell" }?.value.takeIf { it is Value }?.let { (it as Value).formatted } ?: "",
                        aboutMe = state.resume.skills ?: ""
                    )
                )
            }
            val resumeStateValidationError = remember {
                mutableStateOf(
                    TextFieldStateError(
                        lastNameError = "",
                        firstNameError = "",
                        middleNameError = "",
                        phoneError = ""
                    )
                )
            }
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
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Желаемая должность: ",
                        style = MainTheme.typography.headerText,
                        color = MainTheme.colors.primaryText,
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
                //основная информация
                item {
                    Text(
                        text = "Основная информация",
                        style = MainTheme.typography.headerText,
                        color = MainTheme.colors.primaryText,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AddStandardTextField(
                        message = resumeStateValidation.value.lastName,
                        hint = "Укажите фамилию",
                        label = "Фамилия",
                        isError = resumeStateValidationError.value.lastNameError.isNotEmpty(),
                        errorMessage = resumeStateValidationError.value.lastNameError,
                        onValueChanged = {
                            resumeStateValidation.value = resumeStateValidation.value.copy(lastName = it)
                            resumeStateValidationError.value = addResumeScreenViewModel.validateResumeFields(resumeStateValidation.value)
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AddStandardTextField(
                        message = resumeStateValidation.value.firstName,
                        hint = "Укажите имя",
                        label = "Имя",
                        isError = resumeStateValidationError.value.firstNameError.isNotEmpty(),
                        errorMessage = resumeStateValidationError.value.firstNameError,
                        onValueChanged = {
                            resumeStateValidation.value = resumeStateValidation.value.copy(firstName = it)
                            resumeStateValidationError.value = addResumeScreenViewModel.validateResumeFields(resumeStateValidation.value) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AddStandardTextField(
                        message = resumeStateValidation.value.middleName,
                        hint = "Укажите отчество",
                        label = "Отчество",
                        isError = resumeStateValidationError.value.middleNameError.isNotEmpty(),
                        errorMessage = resumeStateValidationError.value.middleNameError,
                        onValueChanged = {
                            resumeStateValidation.value = resumeStateValidation.value.copy(middleName = it)
                            resumeStateValidationError.value = addResumeScreenViewModel.validateResumeFields(resumeStateValidation.value)
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AddStandardTextField(
                        message = resumeStateValidation.value.phone,
                        hint = "Укажите номер телефона",
                        label = "Номер телефона",
                        isError = resumeStateValidationError.value.phoneError.isNotEmpty(),
                        errorMessage = resumeStateValidationError.value.phoneError,
                        onValueChanged = {
                            resumeStateValidation.value = resumeStateValidation.value.copy(phone = it)
                            resumeStateValidationError.value = addResumeScreenViewModel.validateResumeFields(resumeStateValidation.value)
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    if(areasState.areas?.isNotEmpty() == true){
                        ExposedDropdownMenuBoxItem(state.resume, areasState.areas, "Город"){ value ->
                            if(value != null) changedStateResume = changedStateResume?.copy(area = value)
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    dictionariesState.dictionaries?.schedule?.let { CheckBoxesListSchedules(list = it){ value ->
                        if(value.isNotEmpty()) changedStateResume = changedStateResume?.copy(schedules = value)
                        Log.d("CHANGE", value.toString())
                        Log.d("CHANGE", state.resume.toString())
                        Log.d("CHANGE", changedStateResume.toString())
                    }}
                    Spacer(modifier = Modifier.height(8.dp))
                    dictionariesState.dictionaries?.employment?.let { CheckBoxesListEmployments(list = it){ value ->
                        if(value.isNotEmpty()) changedStateResume = changedStateResume?.copy(employments = value)
                    }}
                    Spacer(modifier = Modifier.height(8.dp))
                    professionalRolesState.profRoles?.let { CheckBoxesListProfessionalRoles(list = it){ value ->
                        if(value.isNotEmpty()) changedStateResume = changedStateResume?.copy(professionalRoles = value)
                    }}
                }
                //О себе
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Расскажите о себе",
                        style = MainTheme.typography.headerText,
                        color = MainTheme.colors.primaryText,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AddStandardTextField(
                        message = resumeStateValidation.value.aboutMe,
                        hint = "",
                        label = "О себе",
                        singleLine = false,
                        heightField = 172,
                        onValueChanged = {
                            resumeStateValidation.value = resumeStateValidation.value.copy(aboutMe = it)
                        })
                }
                item { Spacer(modifier = Modifier.height(48.dp)) }
            }
        }
    }
    if(state.error.isNotEmpty()){
        ErrorUseCaseElement(error = state.error) {

        }
    }
    if(state.isLoading){
        LoadingUseCaseElement()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExposedDropdownMenuBoxItem(
    resumeDetail: ResumeDetail,
    areas: List<AreaX>,
    label: String,
    onValueChanged: (Area?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(resumeDetail.area ?: Area(id = "1", name = "Москва")) }
    var query by remember { mutableStateOf(selectedItem.name) }
    areas.forEach {
        Log.d("AREA", it.name)
    }
    val filteringOptions = areas.filter { it.name.contains(query.toString(), ignoreCase = true) }.toMutableList()
    var isError by remember { mutableStateOf(false) }
    Column(horizontalAlignment = Alignment.Start){
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = query.toString(),
                onValueChange = { newSearchQuery ->
                    query = newSearchQuery
                    expanded = true
                },
                label = {
                    Text(
                        text = label,
                        style = MainTheme.typography.smallText,
                        color = MainTheme.colors.auxiliaryColor
                    )
                },
                textStyle = MainTheme.typography.inputTextField,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    backgroundColor = MainTheme.colors.primaryBackground,
                    focusedIndicatorColor = if(!isError) MainTheme.colors.auxiliaryColor else {MainTheme.colors.refusedColor},
                    unfocusedIndicatorColor = if(!isError) MainTheme.colors.strokeColor else {MainTheme.colors.refusedColor},
                    cursorColor = MainTheme.colors.hintTextFieldColor,
                    trailingIconColor = MainTheme.colors.auxiliaryColor,
                    focusedTrailingIconColor = MainTheme.colors.auxiliaryColor
                )
            )

            if (filteringOptions.isNotEmpty()){
                isError = false
                val displayedOptions = if (selectedItem.name?.isNotEmpty() == true) {
                    filteringOptions.sortedBy { it.id }.take(20)
                } else {
                    areas.sortedBy { it.id }.take(20)
                }

                ExposedDropdownMenu(
                    expanded = expanded,
                    modifier = Modifier.background(MainTheme.colors.primaryBackground),
                    onDismissRequest = { expanded = false }
                ) {
                    displayedOptions.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                selectedItem = Area(id = selectionOption.id, name = selectionOption.name)
                                query = selectedItem.name.toString()
                                onValueChanged(selectedItem)
                                expanded = false
                            }
                        ) {
                            Text(
                                text = selectionOption.name,
                                style = MainTheme.typography.bodyText1,
                                color = MainTheme.colors.primaryText
                            )
                        }
                    }
                }
            }else {isError = true}
        }
        if(isError){
            Text(
                text = "Указанный город не найден",
                style = MainTheme.typography.smallText,
                color = MainTheme.colors.refusedColor,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )

        }
    }
}

@Composable
fun CheckBoxesListSchedules(list: List<Schedule>, onCheckedChange: (List<Schedule>) -> Unit){
    val updatedItems = remember { mutableStateListOf<Schedule>(list[0]) }
    Column(horizontalAlignment = Alignment.Start) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "График работы",
            style = MainTheme.typography.headerText,
            color = MainTheme.colors.primaryText,
        )
        Spacer(modifier = Modifier.height(8.dp))
        list.forEach { value ->
            val checked = remember { mutableStateOf(updatedItems.contains(value)) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checked.value,
                    onCheckedChange = { _checked ->
                        if(_checked){ updatedItems.add(value) } else { updatedItems.remove(value) }
                        onCheckedChange(updatedItems.toList())
                        checked.value = _checked
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MainTheme.colors.auxiliaryColor,
                        uncheckedColor = MainTheme.colors.primaryText
                    )
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp),
                    color = MainTheme.colors.primaryText,
                    text = value.name.toString()
                )
            }
        }
        if(updatedItems.isEmpty()){
            Text(
                text = "Выберите хотя бы один тип занятости",
                style = MainTheme.typography.smallText,
                color = MainTheme.colors.refusedColor,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )

        }
    }
}

@Composable
fun CheckBoxesListEmployments(list: List<Employment>, onCheckedChange: (List<Employment>) -> Unit){
    val updatedItems = remember { mutableStateListOf<Employment>(list[0]) }
    Column(horizontalAlignment = Alignment.Start) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Тип занятости",
            style = MainTheme.typography.headerText,
            color = MainTheme.colors.primaryText,
        )
        Spacer(modifier = Modifier.height(8.dp))
        list.forEach { value ->
            val checked = remember { mutableStateOf(updatedItems.contains(value)) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checked.value,
                    onCheckedChange = { _checked ->
                        if(_checked){ updatedItems.add(value) } else { updatedItems.remove(value) }
                        onCheckedChange(updatedItems.toList())
                        checked.value = _checked
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MainTheme.colors.auxiliaryColor,
                        uncheckedColor = MainTheme.colors.primaryText
                    )
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp),
                    color = MainTheme.colors.primaryText,
                    text = value.name.toString()
                )
            }
        }
        if(updatedItems.isEmpty()){
            Text(
                text = "Выберите хотя бы один тип графика работы",
                style = MainTheme.typography.smallText,
                color = MainTheme.colors.refusedColor,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )

        }
    }
}

@Composable
fun CheckBoxesListProfessionalRoles(list: List<Role>, onCheckedChange: (List<ProfessionalRole>) -> Unit){
    val updatedItems = remember { mutableStateListOf<ProfessionalRole>(ProfessionalRole(id = list[0].id, name = list[0].name)) }
    Column(horizontalAlignment = Alignment.Start) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Профессиональные роли",
            style = MainTheme.typography.headerText,
            color = MainTheme.colors.primaryText,
        )
        Spacer(modifier = Modifier.height(8.dp))
        list.forEach { value ->
            val checked = remember { mutableStateOf(updatedItems.contains(ProfessionalRole(id = value.id, name = value.name))) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checked.value,
                    onCheckedChange = { _checked ->
                        if(_checked){ updatedItems.add(ProfessionalRole(id = value.id, name = value.name)) }
                        else { updatedItems.remove(ProfessionalRole(id = value.id, name = value.name)) }
                        onCheckedChange(updatedItems.toList())
                        checked.value = _checked
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MainTheme.colors.auxiliaryColor,
                        uncheckedColor = MainTheme.colors.primaryText
                    )
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp),
                    color = MainTheme.colors.primaryText,
                    text = value.name.toString()
                )
            }
        }
        if(updatedItems.isEmpty()){
            Text(
                text = "Выберите хотя бы одну профессиональную роль",
                style = MainTheme.typography.smallText,
                color = MainTheme.colors.refusedColor,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )

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
