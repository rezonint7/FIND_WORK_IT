package com.example.find_work_it.presentation.screens.profile_screen

import android.annotation.SuppressLint
import android.widget.Space
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.find_work_it.R
import com.example.find_work_it.presentation.navigation.NavScreens
import com.example.find_work_it.presentation.screens.AddBasicTextField
import com.example.find_work_it.presentation.screens.ButtonElement
import com.example.find_work_it.presentation.screens.main_screen.TopBar
import com.example.find_work_it.ui.theme.FINDWORKIT_Theme
import com.example.find_work_it.ui.theme.MainTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    controller: NavController,
    profileScreenViewModel: ProfileScreenViewModel = hiltViewModel(),
){
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState =
    BottomSheetState(BottomSheetValue.Collapsed))
//    BackHandler(bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
//        coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.collapse() }
//    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MainTheme.colors.primaryBackground)) {
        BottomSheetScaffold(
            topBar = {TopBar(screenName = "Профиль")},
            backgroundColor = MainTheme.colors.primaryBackground,
            scaffoldState = bottomSheetScaffoldState,
            sheetContent = {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(308.dp)
                    .background(MainTheme.colors.primaryBackground),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AddBasicTextField(
                        sizeWidth = 274,
                        sizeHeight = 48,
                        textStyle = MainTheme.typography.inputTextField,
                        placeholder = "Имя"
                    )
                    AddBasicTextField(
                        sizeWidth = 274,
                        sizeHeight = 48,
                        textStyle = MainTheme.typography.inputTextField,
                        placeholder = "Фамилия"
                    )
                    AddBasicTextField(
                        sizeWidth = 274,
                        sizeHeight = 48,
                        textStyle = MainTheme.typography.inputTextField,
                        placeholder = "Отчество"
                    )
                }
            }
        ){
            LazyColumn(modifier = Modifier
                .fillMaxWidth()){
                item{
                    ProfileInfo(bottomSheetScaffoldState, coroutineScope)
                    Spacer(modifier = Modifier
                        .height(1.dp)
                        .background(MainTheme.colors.strokeColor))
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileInfo(
    bottomSheetScaffoldState : BottomSheetScaffoldState,
    coroutineScope: CoroutineScope
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(208.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.size(28.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.round_edit_24),
                    modifier = Modifier.fillMaxSize(),
                    colorFilter = ColorFilter.tint(MainTheme.colors.primaryText),
                    contentDescription = "ProfileEdit"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Row(modifier = Modifier.size(108.dp)) {

            }
            Spacer(modifier = Modifier.width(8.dp))
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
                    painter = painterResource(id = R.drawable.round_share_24),
                    modifier = Modifier.fillMaxSize(),
                    colorFilter = ColorFilter.tint(MainTheme.colors.primaryText),
                    contentDescription = "ProfileShare"
                )
            }
        }
        Text(
            text = "Кунавин Антон Вячеславович",
            style = MainTheme.typography.headerText,
            color = MainTheme.colors.primaryText
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "rezonint@gmail.com",
            style = MainTheme.typography.smallText,
            color = MainTheme.colors.primaryText
        )
    }
}

@Composable
fun ResumeItem(){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable { }, elevation = 8.dp
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(MainTheme.colors.secondaryBackground)
            .height(height = 88.dp)
            .padding(8.dp)) {
            Text(
                text = "Kotlin Android Developer",
                style = MainTheme.typography.bodyText1,
                color = MainTheme.colors.primaryText
            )
            Text(
                text = "700000",
                style = MainTheme.typography.bodyText1,
                color = MainTheme.colors.primaryText
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun preview(){
    FINDWORKIT_Theme(darkTheme = true) {
        //ProfileInfo()
    }
}