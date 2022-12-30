package com.notylines.elbus.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.notylines.elbus.R

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

@Preview(showBackground = true)
@Composable
fun SavedRunCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .height(140.dp),
        elevation = 5.dp

    ) {
        Row(Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.cropped_map),
                contentDescription = "Trayecto guardado",
                modifier = Modifier
                    .height(200.dp)
                    .width(150.dp)
                    .padding(4.dp),
                contentScale = ContentScale.Crop

            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .height(200.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Fecha: ")
                    }
                    append("06/02/2022")
                })

                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Compania: ")
                    }
                    append("Reino de Quito")
                })

                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Unidad: ")
                    }
                    append("1022")
                })

                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Duracion: ")
                    }
                    append("28 mins.")
                })
            }
        }
    }
}

















