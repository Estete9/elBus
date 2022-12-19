package com.notylines.elbus.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.notylines.elbus.R
import com.notylines.elbus.components.CustomTextField
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
                .fillMaxSize(),
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

                CustomTextField(
                    textFieldValue = emailTextFieldValue,
                    onTextFieldValueChange = { emailTextFieldValue = it }
                )

                Spacer(modifier = Modifier.height(15.dp))

                CustomTextField(
                    textFieldValue = passwordTextFieldValue,
                    onTextFieldValueChange = { passwordTextFieldValue = it },
                    label = "contrasena",
                    placeHolder = "*********",
                    keyboardType = 1,
                )

                if (!isRegistered) {
                    Spacer(modifier = Modifier.height(15.dp))

                    CustomTextField(
                        textFieldValue = repeatPasswordTextFieldValue,
                        onTextFieldValueChange = { repeatPasswordTextFieldValue = it },
                        label = "repite la contrasena",
                        placeHolder = "*********",
                        keyboardType = 1,
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


