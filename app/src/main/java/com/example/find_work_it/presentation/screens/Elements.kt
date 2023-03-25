package com.example.find_work_it.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.find_work_it.R
import com.example.find_work_it.ui.theme.MainTheme

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
    val rowModifier = Modifier.size(sizeWidth.dp, sizeHeight.dp).background(MainTheme.colors.secondaryBackground)
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
    val shape = RoundedCornerShape(size = 10.dp)
    val boxModifier = Modifier
        .size(size.dp)
        .background(MainTheme.colors.secondaryBackground)
        .border(
            width = (0.5).dp,
            color = borderColor,
            shape = shape
        ).clickable {
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




