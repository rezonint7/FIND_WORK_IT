package com.example.find_work_it.presentation.ui.theme.elements

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.find_work_it.R
import com.example.find_work_it.presentation.ui.theme.BasicTextFieldStyle
import com.example.find_work_it.presentation.ui.theme.FIND_WORK_ITTheme
import com.example.find_work_it.ui.theme.customLightGray
import com.example.find_work_it.ui.theme.textFieldColor
import com.example.find_work_it.ui.theme.textFieldHintColor

//@Composable
//fun SearchElement(){
//    var responseSearch by remember { mutableStateOf(TextFieldValue("")) }
//    Row{
//        OutlinedTextField(
//            value = responseSearch,
//            onValueChange = {responseSearch = it},
//            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "searchIcon") },
//            placeholder = { Text(text = "Поиск") },
//            Modifier.border(border = BorderStroke(1.dp, Color.Transparent)),
//            )
//    }
//
//}

//@Composable
//fun AddTextField(
//    hint: String,
//    sizeWidth:Int,
//    sizeHeight: Int,
//    fontSize: Int = sizeHeight - 30,
//    singleLine: Boolean = true,
//    keyboardType: KeyboardType = KeyboardType.Text,
//) {
//    val message = remember { mutableStateOf(TextFieldValue("")) }
//    BasicTextField(
//        modifier = Modifier.size(sizeWidth.dp, sizeHeight.dp),
//        value = message.value,
//        onValueChange = { message.value = it },
//        textStyle = TextStyle(color = Color.Gray, fontSize = fontSize.sp),
//        placeholder = {
//                Text(hint,
//                style = TextStyle(color = Color.Gray, fontSize = fontSize.sp))},
//        leadingIcon = { Icon(imageVector = Icons.Default.Search,
//                contentDescription = "searchIcon",
//                Modifier.size((sizeHeight - 18).dp)) },
//        colors = TextFieldDefaults.outlinedTextFieldColors(
//            focusedBorderColor = Transparent,
//            unfocusedBorderColor = Transparent,
//            backgroundColor = Color.White,
//            leadingIconColor = Color.Gray,
//            cursorColor = Color.Red
//        ),
//        singleLine = singleLine,
//        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
//    )
//}

@Composable
fun AddBasicTextField(
    sizeWidth: Int,
    sizeHeight: Int,
    textStyle: TextStyle,
    placeholder: String = "",
    icon: ImageVector? = null,
    iconContentDescription: String = "",
    borderColor: Color = customLightGray
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
    val rowModifier = Modifier.size(sizeWidth.dp, sizeHeight.dp)
    val surfaceModifier = Modifier.background(Color.White, shape = shape)


    Surface(shape = shape, modifier = surfaceModifier) {
        Row(modifier = rowModifier, verticalAlignment = Alignment.CenterVertically) {
            if(icon != null){
                Spacer(modifier = Modifier.width(width = 16.dp))
                Icon(
                    imageVector = icon,
                    contentDescription = iconContentDescription,
                    modifier = Modifier.size(32.dp, 32.dp),
                    tint = textFieldColor
                )
            }
            if (message.text.isEmpty()) {
                Spacer(modifier = Modifier.width(width = 16.dp))
                Text(
                    text = placeholder,
                    style = textStyle,
                    color = textFieldHintColor,
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
            cursorBrush = SolidColor(textFieldColor)
        )
    }
}

@Composable
fun FilterButton(
    size: Int,
    borderColor: Color = customLightGray
){
    val shape = RoundedCornerShape(size = 10.dp)
    val surfaceModifier = Modifier
        .size(size.dp)
        .background(color = Color.White, shape = shape)
        .clip(shape)
    val boxModifier = Modifier
        .size(size.dp)
        .border(
            width = (0.5).dp,
            color = borderColor,
            shape = shape
        ).clickable {

        }

    Surface(modifier = surfaceModifier) {
        Box(modifier = boxModifier, contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(id = R.drawable.filter_icon),
                contentDescription = "filter",
                modifier = Modifier.size(32.dp),
                tint = textFieldColor
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FIND_WORK_ITTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)) {
            AddBasicTextField(
                sizeWidth = 318,
                sizeHeight = 48,
                textStyle = BasicTextFieldStyle,
                placeholder = "Поиск",
                icon = Icons.Default.Search,
                iconContentDescription = "iconSearch"
            )
            FilterButton(size = 48)

        }
    }
}