package com.example.find_work_it.presentation.screens.profile_screen



import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.find_work_it.R
import com.example.find_work_it.common.ConstantsError
import com.example.find_work_it.domain.model.Resume
import com.example.find_work_it.presentation.navigation.NavScreens
import com.example.find_work_it.presentation.screens.AddBasicTextField
import com.example.find_work_it.presentation.screens.ButtonElement
import com.example.find_work_it.presentation.screens.ErrorUseCaseElement
import com.example.find_work_it.presentation.screens.LoadingUseCaseElement
import com.example.find_work_it.presentation.screens.TopBar
import com.example.find_work_it.ui.theme.FINDWORKIT_Theme
import com.example.find_work_it.ui.theme.MainTheme
import com.example.find_work_it.ui.theme.Shapes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    controller: NavController,
    profileScreenViewModel: ProfileScreenViewModel = hiltViewModel(),
){
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState =
    BottomSheetState(BottomSheetValue.Collapsed))
//    BackHandler(bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
//        coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.collapse() }
//    }

    val state = profileScreenViewModel.state.value
    val getResumesState = profileScreenViewModel.resumes.value
    if (state != null) {
        if(state.user != null){
            Column(modifier = Modifier
                .fillMaxSize()
                .background(MainTheme.colors.primaryBackground)) {
                BottomSheetScaffold(
                    topBar = {
                        TopBar(screenName = "Профиль"){
                            Row(modifier = Modifier.size(64.dp, 34.dp), horizontalArrangement = Arrangement.SpaceBetween){
                                Row(modifier = Modifier
                                    .size(28.dp)
                                    .clickable {
                                        coroutineScope.launch {
                                            if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                                bottomSheetScaffoldState.bottomSheetState.expand()
                                            } else {
                                                bottomSheetScaffoldState.bottomSheetState.collapse()
                                            }
                                        }
                                    }) {
                                    Image(
                                        painter = painterResource(id = R.drawable.round_edit_24),
                                        modifier = Modifier.fillMaxSize(),
                                        colorFilter = ColorFilter.tint(MainTheme.colors.primaryText),
                                        contentDescription = "ProfileEdit"
                                    )
                                }
                                Row(modifier = Modifier.size(28.dp)) {
                                    Image(
                                        painter = painterResource(id = R.drawable.round_share_24),
                                        modifier = Modifier.fillMaxSize(),
                                        colorFilter = ColorFilter.tint(MainTheme.colors.primaryText),
                                        contentDescription = "ProfileShare"
                                    )
                                }
                            }
                        }
                    },
                    backgroundColor = MainTheme.colors.primaryBackground,
                    sheetBackgroundColor = MainTheme.colors.primaryBackground,
                    sheetElevation = 10.dp,
                    scaffoldState = bottomSheetScaffoldState,
                    sheetContent = {
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .height(398.dp)
                            .padding(top = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val firstNameState = remember { mutableStateOf(state.user.firstName) }
                            val lastNameState = remember { mutableStateOf(state.user.lastName) }
                            val middleNameState = remember { mutableStateOf(state.user.middleName ?: "") }
                            val isFirstNameValid = remember { mutableStateOf(true) }
                            val isLastNameValid = remember { mutableStateOf(true) }
                            val isMiddleNameValid = remember { mutableStateOf(true) }

                            AddBasicTextField(
                                sizeWidth = 294,
                                sizeHeight = 48,
                                textStyle = MainTheme.typography.inputTextField,
                                placeholder = "Имя",
                                onValueChanged = {
                                    firstNameState.value = it
                                    isFirstNameValid.value = profileScreenViewModel.validateUserFields(it)
                                },
                                isError = !isFirstNameValid.value,
                                message = firstNameState.value,
                                errorMessage = ConstantsError.USER_FIRSTNAME_ERROR_VALIDATE
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            AddBasicTextField(
                                sizeWidth = 294,
                                sizeHeight = 48,
                                textStyle = MainTheme.typography.inputTextField,
                                placeholder = "Фамилия",
                                onValueChanged = {
                                    lastNameState.value = it
                                    isLastNameValid.value = profileScreenViewModel.validateUserFields(it)
                                },
                                isError = !isLastNameValid.value,
                                message = lastNameState.value,
                                errorMessage = ConstantsError.USER_LASTNAME_ERROR_VALIDATE
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            AddBasicTextField(
                                sizeWidth = 294,
                                sizeHeight = 48,
                                textStyle = MainTheme.typography.inputTextField,
                                placeholder = "Отчество",
                                onValueChanged = {
                                    middleNameState.value = it
                                    isMiddleNameValid.value = profileScreenViewModel.validateMiddleNameUserField(it)
                                },
                                isError = !isMiddleNameValid.value,
                                message = middleNameState.value,
                                errorMessage = ConstantsError.USER_MIDDLENAME_ERROR_VALIDATE
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            ButtonElement(
                                text = "Применить",
                                modifier = Modifier.size(274.dp, 48.dp),
                                backgroundColor = MainTheme.colors.auxiliaryColor,
                                isEnabled = isFirstNameValid.value && isLastNameValid.value && isMiddleNameValid.value)
                            {
                                val userInfo = hashMapOf<String, String?>(
                                    "first_name" to firstNameState.value,
                                    "last_name" to lastNameState.value,
                                    "middle_name" to middleNameState.value
                                )
                                profileScreenViewModel.editUserInfo(userInfo)
                                Toast.makeText(context, profileScreenViewModel.editInfoState.value.error, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                ){
                    LazyColumn(modifier = Modifier.fillMaxWidth()){
                        item{
                            ProfileInfo(profileScreenViewModel)
                            Divider(modifier = Modifier.fillMaxWidth(), color = MainTheme.colors.strokeColor)
                        }
                        items(getResumesState.resumes){ resume ->
                            ResumeItem(resume = resume){
                                controller.navigate(NavScreens.AddResumeScreen.route + "/${resume.id}")
                            }
                        }
                        item {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                                ButtonElement(
                                    text = "Добавить резюме",
                                    modifier = Modifier.padding(8.dp),
                                    backgroundColor = MainTheme.colors.auxiliaryColor
                                ) {
                                    //navigate to add

                                }
                            }
                        }
                    }
                }
            }
        }
    }
    if (state != null) {
        if(state.error.isNotBlank()){
            ErrorUseCaseElement(error = state.error) {

            }
        }
    }
    if (state != null) {
        if(state.isLoading){
            LoadingUseCaseElement()
        }
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun ProfileInfo(
    profileScreenViewModel: ProfileScreenViewModel,
){
    val userInfo by profileScreenViewModel.state.observeAsState(initial = ProfileScreenState())
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(108.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text =  userInfo.user?.lastName + " " + userInfo.user?.firstName + " " + userInfo.user?.middleName,
            style = MainTheme.typography.headerText,
            color = MainTheme.colors.primaryText
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = profileScreenViewModel.state.value?.user?.email.toString(),
            style = MainTheme.typography.smallText,
            color = MainTheme.colors.primaryText
        )
    }
}

@Composable
fun ResumeItem(resume: Resume, onItemClick: () -> Unit){
    Card(modifier = Modifier
        .wrapContentSize()
        .padding(16.dp)
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
                    Text(
                        text = resume.status?.name.toString(),
                        style = MainTheme.typography.bodyText1,
                        color = MainTheme.colors.auxiliaryColor,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Row(verticalAlignment = Alignment.Bottom){
                        Text(
                            text = resume.updatedAt.toString(),
                            style = MainTheme.typography.smallText,
                            color = MainTheme.colors.secondaryText
                        )
                    }
                }
                Row(modifier = Modifier.border(
                    1.dp,
                    MainTheme.colors.auxiliaryColor,
                    RoundedCornerShape(100.dp)
                )){
                    val image = painterResource(id = R.drawable.photo_resume_test)
                    Image(painter = image, contentDescription = "", modifier = Modifier
                        .clip(
                            RoundedCornerShape(100.dp)
                        )
                        .size(64.dp)
                        .scale(1.6f))
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview(){
    FINDWORKIT_Theme(darkTheme = true) {
        //ResumeItem(resume = Resume("1", "Kotlin разработчик", updatedAt = "Обновлено 12.05.23"))
    }
}