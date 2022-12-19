package com.notylines.elbus.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    label: String = "email",
    placeHolder: String = "ejemplo@ejemplo.com",
    keyboardType: Int = 1,
    hasTrailingIcon: Boolean = false,
    openDropDownMenu: () -> Unit = {},
    textFieldValue: String,
    onTextFieldValueChange: (String) -> Unit
) {
val keyboardTypeOptions = listOf("email", "password", "number", "text")

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = onTextFieldValueChange,
        modifier = modifier,
        keyboardOptions =
        when (keyboardTypeOptions[keyboardType]) {
            "email" -> {
                KeyboardOptions(keyboardType = KeyboardType.Email)
            }
            "password" -> {
                KeyboardOptions(keyboardType = KeyboardType.Password)
            }
            "number" -> {
                KeyboardOptions(keyboardType = KeyboardType.Number)
            }
            else -> {
                KeyboardOptions(keyboardType = KeyboardType.Text)
            }
        },

        label = { Text(text = label) },
        placeholder = { Text(text = placeHolder) },
        visualTransformation =
        if (keyboardType == 1) PasswordVisualTransformation()
        else VisualTransformation.None,
        trailingIcon = {
            if (hasTrailingIcon) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "drop down menu",
                    modifier = Modifier.clickable { openDropDownMenu() }
                )
            }
        }

    )
}




















