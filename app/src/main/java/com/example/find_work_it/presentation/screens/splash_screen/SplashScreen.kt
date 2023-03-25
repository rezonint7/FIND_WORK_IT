package com.example.find_work_it.presentation.screens.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.find_work_it.presentation.navigation.NavScreens
import com.example.find_work_it.R
import com.example.find_work_it.ui.theme.AppNameStyle
import com.example.find_work_it.ui.theme.FINDWORKIT_Theme
import com.example.find_work_it.ui.theme.MainTheme
import com.example.find_work_it.ui.theme.SmallStyle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(controller: NavController){
    val constraints = ConstraintSet{
        val blockLogo = createRefFor("blockLogo")
        val blockHH = createRefFor("blockHH")
        val guideLineTop = createGuidelineFromTop(0.3f)

        constrain(blockLogo){
            top.linkTo(guideLineTop)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }
        constrain(blockHH){
            bottom.linkTo(parent.bottom, margin = 88.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }

//    val systemUiController = rememberSystemUiController()
//    systemUiController.setSystemBarsColor(Color.Black)
    LaunchedEffect(key1 = true){
        delay(3000)
        controller.popBackStack()
        controller.navigate(NavScreens.MainScreen.route)
    }

    ConstraintLayout(constraints, modifier = Modifier.fillMaxSize().background(MainTheme.colors.primaryBackground)){
        val logo = painterResource(id = R.drawable.logo2)
        val logoHH = painterResource(id = R.drawable.min_hh_red)

        val textColor = MainTheme.colors.primaryText
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.layoutId("blockLogo")) {
            Image(painter = logo,
                contentDescription = "AppLogo",
                Modifier.size(128.dp, 128.dp))
            Text(text = stringResource(R.string.app_name),
                style = AppNameStyle,
                color = MainTheme.colors.secondaryText
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .size(268.dp, 48.dp)
            .layoutId("blockHH")){
            Image(painter = logoHH, contentDescription = "LogoHH",
                modifier = Modifier.size(32.dp))
            Text(text = stringResource(R.string.hh_info_load),
                style = SmallStyle,
                color = textColor,
                modifier = Modifier.padding(start = 8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FINDWORKIT_Theme(darkTheme = true) {
        SplashScreen(controller = rememberNavController())
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    FINDWORKIT_Theme(darkTheme = false) {
        SplashScreen(controller = rememberNavController())
    }
}

