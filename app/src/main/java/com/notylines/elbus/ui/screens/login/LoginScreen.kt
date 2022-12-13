package com.notylines.elbus.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.notylines.elbus.R
import com.notylines.elbus.ui.navigation.AppScreens

@Composable
fun LoginScreen(navController: NavController) {

    var isRegistered by remember { mutableStateOf(false) }
    var emailTextFieldValue by remember { mutableStateOf("") }
    var passwordTextFieldValue by remember { mutableStateOf("") }
    var repeatPasswordTextFieldValue by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .border(width = 2.dp, color = Color.Cyan),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.weight(0.5f))

//            Logo and welcome text
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_directions_bus_24),
                    modifier = Modifier.size(110.dp, 110.dp),
                    contentDescription = "App Logo"
                )

                Text(
                    text = if (isRegistered) "Bienvenido de vuelta!" else "Bienvenido!",
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.h5
                )
            }

// Text fields
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                CustomTextField(textFieldValue = emailTextFieldValue,
                    onTextFieldValueChange = {
                        emailTextFieldValue = it
                    })

                Spacer(modifier = Modifier.height(15.dp))

                CustomTextField(
                    label = "contrasena",
                    placeHolder = "*********",
                    isPassword = true,
                    textFieldValue = passwordTextFieldValue,
                    onTextFieldValueChange = { passwordTextFieldValue = it }
                )

                if (!isRegistered) {
                    Spacer(modifier = Modifier.height(15.dp))

                    CustomTextField(
                        label = "repite la contrasena",
                        placeHolder = "*********",
                        isPassword = true,
                        textFieldValue = repeatPasswordTextFieldValue,
                        onTextFieldValueChange = { repeatPasswordTextFieldValue = it }
                    )
                }
            }

//Button and change isRegister button
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {

                Button(onClick = { /*TODO check login info*/ navController.navigate(AppScreens.SetupScreen.name) }) {

                    Text(text = if (isRegistered) "Ingresa" else "Registrate")
                }

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    style = MaterialTheme.typography.caption,
                    text =
                    if (isRegistered) "No tienes cuenta? haz click aqui"
                    else "Ya tienes cuenta? haz click aqui",
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.clickable {
                        isRegistered = !isRegistered
                        emailTextFieldValue = ""
                        passwordTextFieldValue = ""
                        repeatPasswordTextFieldValue = ""
                    }
                )
            }

        }
    }

}


@Composable
fun CustomTextField(
    label: String = "email",
    placeHolder: String = "ejemplo@ejemplo.com",
    isPassword: Boolean = false,
    textFieldValue: String,
    onTextFieldValueChange: (String) -> Unit
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = onTextFieldValueChange,
        keyboardOptions =
        if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password)
        else KeyboardOptions(keyboardType = KeyboardType.Email),
        label = { Text(text = label) },
        placeholder = { Text(text = placeHolder) },
        modifier = Modifier.height(60.dp),
        visualTransformation =
        if (isPassword) PasswordVisualTransformation()
        else VisualTransformation.None
    )
}