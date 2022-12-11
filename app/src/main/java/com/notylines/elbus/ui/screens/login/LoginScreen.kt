package com.notylines.elbus.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.notylines.elbus.R

@Composable
fun LoginScreen(navController: NavController) {

    val isRegistered by remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_directions_bus_24),
                modifier = Modifier.size(110.dp, 110.dp),
                contentDescription = "App Logo"
            )

            Spacer(modifier = Modifier.height(70.dp))

            Text(
                text = if (isRegistered) "Te hemos extranado" else "Bienvenido!",
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField()

            Spacer(modifier = Modifier.height(15.dp))

            CustomTextField(
                label = "contrasena",
                placeHolder = "*********",
                isPassword = true
            )

            if (!isRegistered) {
                Spacer(modifier = Modifier.height(15.dp))

                CustomTextField(
                    label = "repite la contrasena",
                    placeHolder = "*********",
                    isPassword = true
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Button(onClick = { /*TODO check login info*/ }
            ) {

                Text(text = if (isRegistered) "Ingresa" else "Registrate")
            }
            if (isRegistered) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    style = MaterialTheme.typography.caption,
                    text = "No tienes cuenta? haz click aqui",
                    color = MaterialTheme.colors.primary
                )
            } else {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    style = MaterialTheme.typography.caption,
                    text = "Ya tienes cuenta? haz click aqui",
                    color = MaterialTheme.colors.primary
                )

            }
        }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomTextField(
    label: String = "email",
    placeHolder: String = "ejemplo@ejemplo.com",
    isPassword: Boolean = false
) {
    val email = remember { mutableStateOf("") }


    OutlinedTextField(
        value = email.value,
        onValueChange = {
            email.value = it
        },
        keyboardOptions = if (isPassword) {
            KeyboardOptions(keyboardType = KeyboardType.Password)
        } else {
            KeyboardOptions(keyboardType = KeyboardType.Email)
        },
        label = { Text(text = label) },
        placeholder = { Text(text = placeHolder) },
        modifier = Modifier.height(60.dp)
    )
}