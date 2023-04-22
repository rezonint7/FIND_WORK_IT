package com.example.find_work_it.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.find_work_it.R
import com.example.find_work_it.data.remote.dto.vacancy.models.Contacts
import com.example.find_work_it.data.remote.dto.vacancy.models.Employer
import com.example.find_work_it.data.remote.dto.vacancy.models.Phone
import com.example.find_work_it.domain.model.Vacancy
import com.example.find_work_it.domain.model.VacancyDetail
import com.example.find_work_it.presentation.navigation.NavScreens
import com.example.find_work_it.ui.theme.FINDWORKIT_Theme
import com.example.find_work_it.ui.theme.MainTheme
import com.example.find_work_it.ui.theme.Shapes
import java.util.concurrent.Executors

@Composable
fun AddBasicTextField(
    sizeWidth: Int,
    sizeHeight: Int,
    textStyle: TextStyle,
    placeholder: String = "",
    icon: ImageVector? = null,
    iconContentDescription: String = "",
    borderColor: Color = MainTheme.colors.strokeColor
){
    var message by remember { mutableStateOf(TextFieldValue("")) }
    val shape = RoundedCornerShape(size = 10.dp)

    val boxModifier = Modifier
        .size(sizeWidth.dp, sizeHeight.dp)
        .border(
            width = (0.5).dp,
            color = borderColor,
            shape = shape
        )
        .padding(start = 64.dp)
    val rowModifier = Modifier
        .size(sizeWidth.dp, sizeHeight.dp)
        .background(MainTheme.colors.secondaryBackground)
    val surfaceModifier = Modifier.background(Color.Transparent, shape = shape)

    Surface(shape = shape, modifier = surfaceModifier) {
        Row(modifier = rowModifier, verticalAlignment = Alignment.CenterVertically) {
            if(icon != null){
                Spacer(modifier = Modifier.width(width = 16.dp))
                Icon(
                    imageVector = icon,
                    contentDescription = iconContentDescription,
                    modifier = Modifier.size(32.dp, 32.dp),
                    tint = MainTheme.colors.hintTextFieldColor
                )
            }
            if (message.text.isEmpty()) {
                Spacer(modifier = Modifier.width(width = 16.dp))
                Text(
                    text = placeholder,
                    style = textStyle,
                    color = MainTheme.colors.hintTextFieldColor,
                )
            }
        }
        BasicTextField(
            value = message,
            onValueChange = {text ->
                message = text
            },
            textStyle = textStyle,
            decorationBox = {innerTextField ->
                Box(modifier = boxModifier, contentAlignment = Alignment.CenterStart) {
                    innerTextField()
                }
            },
            singleLine = true,
            cursorBrush = SolidColor(MainTheme.colors.hintTextFieldColor)
        )
    }
}

@Composable
fun FilterButton(
    size: Int,
    borderColor: Color = MainTheme.colors.strokeColor,
    onClick: () -> Unit
){
    val boxModifier = Modifier
        .size(size.dp)
        .background(MainTheme.colors.secondaryBackground)
        .border(
            width = (0.5).dp,
            color = borderColor,
            shape = Shapes.small
        )
        .clickable {
            onClick()
        }
    Box(modifier = boxModifier, contentAlignment = Alignment.Center) {
        Icon(
            painter = painterResource(id = R.drawable.filter_icon),
            contentDescription = "filter",
            modifier = Modifier.size(32.dp),
            tint = MainTheme.colors.hintTextFieldColor
        )
    }
}

@Composable
fun ButtonElement(
    text: String,
    modifier: Modifier,
    backgroundColor: Color,
    strokeColor: Color = backgroundColor,
    onClick: () -> Unit
){
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = MainTheme.colors.buttonText,
            disabledBackgroundColor = MainTheme.colors.secondaryBackground
        ),
        border = BorderStroke(1.dp, strokeColor),
        shape = Shapes.medium,
        modifier = modifier,
        onClick = {onClick()}
    ) {
        Text(text, style = MainTheme.typography.buttonText)
    }
}

@Composable
fun BottomNavigationMenu(
    controller: NavController,
    items: List<NavScreens>
){
    BottomNavigation {
        val navBackStackEntry by controller.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter =  painterResource(id = screen.icon),
                        contentDescription = stringResource(id = screen.title),
                        modifier = Modifier.size(28.dp)
                    )
                },
                modifier = Modifier.background(MainTheme.colors.primaryBackground),
                label = { Text(stringResource(id = screen.title)) },
                selected = currentRoute == screen.route,
                selectedContentColor = MainTheme.colors.auxiliaryColor,
                unselectedContentColor = MainTheme.colors.secondaryText.copy(0.4f),
                alwaysShowLabel = true,
                onClick = {
                    controller.navigate(screen.route) {
                        controller.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun EmployerInfoItem(
    vacancy: VacancyDetail,
    onItemClick: () -> Unit
){
    Column(
        Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .background(MainTheme.colors.secondaryBackground, shape = Shapes.medium)
            .clickable { onItemClick() }
    ) {
        if(!vacancy.employer?.logoUrls?.x90.isNullOrEmpty()){

        }
        Row(Modifier.padding(4.dp)){
            val image = rememberImagePainter("https://hhcdn.ru/employer-logo/3790847.png"){
                this.build()
            }
            Image(painter = image, contentDescription = "logoEmployer")
        }
        Row(
            Modifier
                .padding(4.dp)
                .padding(horizontal = 20.dp)){
            Text(
                text = vacancy.employer?.name.toString(),
                style = MainTheme.typography.headerText,
                color = MainTheme.colors.primaryText
            )
            if(vacancy.employer?.trusted!!){
                Image(
                    modifier = Modifier
                        .size(18.dp)
                        .padding(top = 2.dp),
                    painter = painterResource(id = R.drawable.baseline_check_circle_outline_24),
                    contentDescription = "checkEmployer",
                    colorFilter = ColorFilter.tint(color = MainTheme.colors.auxiliaryColor)
                )
            }
        }
        if(vacancy.address?.raw != null){
            Row(modifier = Modifier.padding(8.dp)){
                Text(
                    text = vacancy.address.raw.toString(),
                    style = MainTheme.typography.headerText,
                    color = MainTheme.colors.primaryText
                )
            }
        }
        if(vacancy.contacts != null){
            Row(modifier = Modifier.fillMaxWidth()) {
                AddContacts(employerContacts = vacancy.contacts)
            }
        }
    }
}

@Composable
fun AddContacts(employerContacts: Contacts){
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(8.dp)){
            Text(
                text = employerContacts.name.toString(),
                style = MainTheme.typography.headerText,
                color = MainTheme.colors.primaryText
            )
        }

        LazyColumn(modifier = Modifier.fillMaxWidth(), userScrollEnabled = false){
            employerContacts.phones?.let {
                items(it.toMutableList()){phone ->
                    ItemContacts(phone = phone)
                }
            }
        }
    }
}

@Composable
fun ItemContacts(phone: Phone?){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
    ) {
        Text(
            text = phone?.number.toString(),
            style = MainTheme.typography.bodyText1,
            color = MainTheme.colors.approvedColor
        )
    }
}

@Composable
fun AddToFavoriteButton(onItemClick: () -> Unit){
    var isClicked by remember { mutableStateOf(false) }
    Column(modifier = Modifier.size(34.dp)) {
        Image(
            painter = painterResource(id = R.drawable.round_favorite_screen_icon),
            contentDescription = "addFavorite",
            modifier = Modifier
                .size(34.dp)
                .clickable {
                    isClicked = !isClicked
                    onItemClick()
                },
            colorFilter = if (isClicked) ColorFilter.tint(MainTheme.colors.refusedColor) else ColorFilter.tint(MainTheme.colors.secondaryText)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val list = listOf(
        NavScreens.MainScreen,
        NavScreens.FavoritesScreen,
        NavScreens.ResponsesVacancyScreen,
        NavScreens.ProfileScreen
    )
    FINDWORKIT_Theme(darkTheme = true) {
        BottomNavigationMenu(controller = rememberNavController(), items = list )
    }
}




