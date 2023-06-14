package com.example.find_work_it.presentation.screens.add_resume_screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.find_work_it.R
import com.example.find_work_it.data.remote.dto.dictionaries.models.EducationLevel
import com.example.find_work_it.data.remote.dto.dictionary_areas.AreaX
import com.example.find_work_it.data.remote.dto.dictionary_professional_roles.Role
import com.example.find_work_it.data.remote.dto.resumes.models.Area
import com.example.find_work_it.data.remote.dto.resumes.models.Contact
import com.example.find_work_it.data.remote.dto.resumes.models.Level
import com.example.find_work_it.data.remote.dto.resumes.models.Primary
import com.example.find_work_it.data.remote.dto.resumes.models.Type
import com.example.find_work_it.data.remote.dto.resumes.models.Value
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Citizenship
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Education
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Employment
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.ProfessionalRole
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.Schedule
import com.example.find_work_it.data.remote.dto.resumes.resume_detail.WorkTicket
import com.example.find_work_it.domain.model.ResumeDetail
import com.example.find_work_it.presentation.navigation.NavScreens
import com.example.find_work_it.presentation.screens.AddStandardTextField
import com.example.find_work_it.presentation.screens.ButtonElement
import com.example.find_work_it.presentation.screens.ErrorUseCaseElement
import com.example.find_work_it.presentation.screens.LoadingUseCaseElement
import com.example.find_work_it.presentation.screens.TopBar
import com.example.find_work_it.ui.theme.MainTheme
import java.time.LocalDate
import java.util.Date


data class TextFieldState(
    val lastName: String = "",
    val firstName: String = "",
    val middleName: String = "",
    val educationName: String = "",
    val educationOrganization: String = "",
    val educationResult: String = "",
    val educationYear: Int,
    val phone: String = "",
    val aboutMe: String = "",
    val skillsSet: String = ""
)
data class TextFieldStateError(
    val lastNameError: String = "",
    val firstNameError: String = "",
    val middleNameError: String = "",
    val educationNameError: String = "",
    val educationOrganizationError: String = "",
    val educationResultError: String = "",
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

    val user = addResumeScreenViewModel.user.value

    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MainTheme.colors.primaryBackground,
        topBar = {state.resume?.let { TopBar(it.title ?: "Должность не указана") }},
        bottomBar = {}
    ){
        state.resume?.let { resume ->
            val resumeTitle = remember {mutableStateOf(resume.title)}
            var changedStateResume = state.resume.copy()

            val birthDate = remember { mutableStateOf(state.resume.birthDate) }
            val education = remember { mutableStateOf(state.resume.education) }

            val editResumeState = addResumeScreenViewModel.editResumeState.value
            val getStatusResumeState = addResumeScreenViewModel.getStatusResumeState.observeAsState()
            val publishResumeState = addResumeScreenViewModel.publishResumeState.value

            val formattedNumber = state.resume.contact?.find { it.type?.id == "cell" }?.value
                .takeIf { it is Value }
                ?.let { (it as Value).formatted }
                ?.toString()
                ?: ""
            val isNavigated = remember { mutableStateOf(false) }
            val resumeStateValidation = remember {
                mutableStateOf(
                    TextFieldState(
                        lastName = state.resume.lastName ?: "",
                        firstName = state.resume.firstName ?: "",
                        middleName = state.resume.middleName ?: "",
                        educationName = state.resume.education?.primary?.getOrNull(0)?.name ?: "",
                        educationOrganization = state.resume.education?.primary?.getOrNull(0)?.organization ?: "",
                        educationResult = state.resume.education?.primary?.getOrNull(0)?.result ?: "",
                        phone = formattedNumber,
                        aboutMe = state.resume.skills ?: "",
                        educationYear = state.resume.education?.primary?.getOrNull(0)?.year ?: LocalDate.now().year,
                        skillsSet = state.resume.skills_set.toString()
                    )
                )
            }
            val resumeStateValidationError = remember {
                mutableStateOf(
                    TextFieldStateError(
                        lastNameError = "",
                        firstNameError = "",
                        middleNameError = "",
                        phoneError = "",
                        educationNameError = "",
                        educationOrganizationError = "",
                        educationResultError = "",
                    )
                )
            }
            Scaffold(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = MainTheme.colors.primaryBackground,
                bottomBar = {
                    ButtonElement(
                        text = "Сохранить",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(horizontal = 8.dp),
                        backgroundColor = MainTheme.colors.auxiliaryColor
                    ) {
                        resumeStateValidationError.value = addResumeScreenViewModel.validateResumeFields(resumeStateValidation.value)
                        val validationError = resumeStateValidationError.value
                        if (validationError.lastNameError.isNotEmpty() ||
                            validationError.firstNameError.isNotEmpty() ||
                            validationError.middleNameError.isNotEmpty() ||
                            validationError.phoneError.isNotEmpty() ||
                            validationError.educationNameError.isNotEmpty() ||
                            validationError.educationOrganizationError.isNotEmpty() ||
                            validationError.educationResultError.isNotEmpty()
                        ) {
                            Toast.makeText(context, "Заполните ошибочные поля ввода", Toast.LENGTH_SHORT).show()
                        } else {
                            val primary = if(resumeStateValidation.value.educationName.isEmpty() ||
                                resumeStateValidation.value.educationOrganization.isEmpty() ||
                                resumeStateValidation.value.educationResult.isEmpty()) null else listOf(Primary(
                                    name = resumeStateValidation.value.educationName,
                                    result = resumeStateValidation.value.educationResult,
                                    organization = resumeStateValidation.value.educationOrganization,
                                    year = resumeStateValidation.value.educationYear
                                ))
                            education.value = education.value?.copy(primary = primary)
                            changedStateResume = changedStateResume.copy(
                                title = resumeTitle.value,
                                lastName = resumeStateValidation.value.lastName,
                                firstName = resumeStateValidation.value.firstName,
                                middleName = resumeStateValidation.value.middleName,
                                skills = resumeStateValidation.value.aboutMe.ifEmpty { null },
                                skills_set = resumeStateValidation.value.skillsSet.split(", ", ";", ",", limit = 30),
                                education = changedStateResume.education?.copy(
                                    level = education.value?.level,
                                    primary = education.value?.primary,

                                ),
                                contact = changedStateResume.copy(contact = listOf(
                                    Contact(preferred = true, type = Type("cell", "Мобильный телефон"), verified = false, value = Value(formatted = resumeStateValidation.value.phone)),
                                    Contact(preferred = false, type = Type("email", "Эл. почта"), verified = false, value = user.user?.email.toString()),
                                )).contact,
                                citizenship = listOf(Citizenship(id = "113", name = "Россия", url = "https://api.hh.ru/areas/113")),
                                workTicket = listOf(WorkTicket(id = "113", name = "Россия", url = "https://api.hh.ru/areas/113")),
                                experience = listOf()
                            )
                            Log.d("edu", changedStateResume.skills_set.toString())
                            Log.d("edu", changedStateResume.toString())
                            resume.resumeId?.let {addResumeScreenViewModel.editResume(it, changedStateResume)}
                            val editError = editResumeState.error.ifBlank { "Изменено" }
                            Toast.makeText(context, editError, Toast.LENGTH_SHORT).show()

                            if(editResumeState.success){
                                resume.resumeId?.let{
                                    addResumeScreenViewModel.getStatusResume(it)
                                }
                                getStatusResumeState.value.let { status ->
                                    resume.resumeId?.let { addResumeScreenViewModel.publishResume(it) }
                                    publishResumeState.success.let {
                                        if(status?.success?.canPublishOrUpdate == true){
                                            Toast.makeText(context, "Опубликовано", Toast.LENGTH_SHORT).show()
                                        }
                                        if(!isNavigated.value){
                                            isNavigated.value = true
                                            controller.navigate(NavScreens.ProfileScreen.route)
                                        }
                                    }
                                    if(publishResumeState.error.isNotEmpty()){
                                        if(!isNavigated.value){
                                            isNavigated.value = true
                                            controller.navigate(NavScreens.ProfileScreen.route)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(18.dp)){
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
                            }
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
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        if(areasState.areas?.isNotEmpty() == true){
                            ExposedDropdownMenuBoxItem(state.resume, areasState.areas, "Город"){ value ->
                                if(value != null) changedStateResume = changedStateResume.copy(area = value)
                            }
                        }
                    } // образование
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Уровень образования",
                            style = MainTheme.typography.headerText,
                            color = MainTheme.colors.primaryText,
                        )
                        dictionariesState.dictionaries?.educationLevel?.let { it ->
                            ExposedDropdownTypeEducationMenuBoxItem(
                                resumeDetail = resume,
                                levels = it,
                                label = "Уровень образования",
                            ){ value ->
                                if (value != null) {
                                    education.value = education.value?.copy(level = value)
                                    changedStateResume = changedStateResume.copy(
                                        education = changedStateResume.education?.copy(level = value)
                                    )
                                }
                            }
                        }
                        AddStandardTextField(
                            message = resumeStateValidation.value.educationName,
                            hint = "Укажите место учебы",
                            label = "Место учебы",
                            isError = resumeStateValidationError.value.educationNameError.isNotEmpty(),
                            errorMessage = resumeStateValidationError.value.educationNameError,
                            onValueChanged = {
                                resumeStateValidation.value = resumeStateValidation.value.copy(educationName = it)
                            }
                        )
                        AddStandardTextField(
                            message = resumeStateValidation.value.educationOrganization,
                            hint = "Укажите факультет на котором учились",
                            label = "Факультет",
                            isError = resumeStateValidationError.value.educationOrganizationError.isNotEmpty(),
                            errorMessage = resumeStateValidationError.value.educationOrganizationError,
                            onValueChanged = {
                                resumeStateValidation.value = resumeStateValidation.value.copy(educationOrganization = it)
                            }
                        )
                        AddStandardTextField(
                            message = resumeStateValidation.value.educationResult,
                            hint = "Укажите специальность",
                            label = "Специальность",
                            isError = resumeStateValidationError.value.educationResultError.isNotEmpty(),
                            errorMessage = resumeStateValidationError.value.educationResultError,
                            onValueChanged = {
                                resumeStateValidation.value = resumeStateValidation.value.copy(educationResult = it)
                            }
                        )
                        ExposedDropdownEducationYearMenuBoxItem("Год окончания"){
                            resumeStateValidation.value = resumeStateValidation.value.copy(educationYear = it)
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        AddStandardTextField(
                            message = resumeStateValidation.value.skillsSet,
                            hint = "",
                            label = "Ключевые навыки (через запятую)",
                            singleLine = false,
                            heightField = 122,
                            onValueChanged = {
                                resumeStateValidation.value = resumeStateValidation.value.copy(skillsSet = it)
                            })
                    }
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        dictionariesState.dictionaries?.schedule?.let { CheckBoxesListSchedules(list = it){ value ->
                            if(value.isNotEmpty()) changedStateResume = changedStateResume.copy(schedules = value)
                            Log.d("CHANGE", value.toString())
                            Log.d("CHANGE", state.resume.toString())
                            Log.d("CHANGE", changedStateResume.toString())
                        }}
                        Spacer(modifier = Modifier.height(8.dp))
                        dictionariesState.dictionaries?.employment?.let { CheckBoxesListEmployments(list = it){ value ->
                            if(value.isNotEmpty()) changedStateResume = changedStateResume.copy(employments = value)
                        }}
                        Spacer(modifier = Modifier.height(8.dp))
                        professionalRolesState.profRoles?.let { CheckBoxesListProfessionalRoles(list = it){ value ->
                            if(value.isNotEmpty()) changedStateResume = changedStateResume.copy(professionalRoles = value)
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExposedDropdownTypeEducationMenuBoxItem(
    resumeDetail: ResumeDetail,
    levels: List<EducationLevel>,
    label: String,
    onValueChanged: (Level?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(resumeDetail.education?.level) }

    var isError by remember { mutableStateOf(false) }
    Column(horizontalAlignment = Alignment.Start){
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedItem?.name.toString(),
                readOnly = true, // Set the TextField to be read-only
                onValueChange = {},
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

            if (levels.isNotEmpty()) {
                isError = false
                ExposedDropdownMenu(
                    expanded = expanded,
                    modifier = Modifier.background(MainTheme.colors.primaryBackground),
                    onDismissRequest = { expanded = false }
                ) {
                    levels.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                selectedItem = Level(id = selectionOption.id, name = selectionOption.name)
                                onValueChanged(selectedItem)
                                expanded = false
                            }
                        ) {
                            Text(
                                text = selectionOption.name.toString(),
                                style = MainTheme.typography.bodyText1,
                                color = MainTheme.colors.primaryText
                            )
                        }
                    }
                }
            }
        }
//        if(isError){
//            Text(
//                text = "Указанный город не найден",
//                style = MainTheme.typography.smallText,
//                color = MainTheme.colors.refusedColor,
//                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
//            )
//        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExposedDropdownEducationYearMenuBoxItem(
    label: String,
    onValueChanged: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(LocalDate.now().year) }

    var isError by remember { mutableStateOf(false) }
    Column(horizontalAlignment = Alignment.Start){
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedItem.toString(),
                readOnly = true, // Set the TextField to be read-only
                onValueChange = {},
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
            val years = (1980..2038).toList()
            if (years.isNotEmpty()) {
                isError = false
                ExposedDropdownMenu(
                    expanded = expanded,
                    modifier = Modifier.background(MainTheme.colors.primaryBackground),
                    onDismissRequest = { expanded = false }
                ) {
                    years.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                selectedItem = selectionOption
                                onValueChanged(selectedItem)
                                expanded = false
                            }
                        ) {
                            Text(
                                text = selectionOption.toString(),
                                style = MainTheme.typography.bodyText1,
                                color = MainTheme.colors.primaryText
                            )
                        }
                    }
                }
            }
        }
//        if(isError){
//            Text(
//                text = "Указанный город не найден",
//                style = MainTheme.typography.smallText,
//                color = MainTheme.colors.refusedColor,
//                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
//            )
//        }
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
