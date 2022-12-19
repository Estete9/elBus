package com.notylines.elbus.ui.screens.setup

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.notylines.elbus.components.CustomTextField
import com.notylines.elbus.ui.navigation.AppScreens


@Composable
fun SetupScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = { CustomFAB(navController) }
    ) {

        var busCompany by rememberSaveable { mutableStateOf("") }
        var busId by rememberSaveable { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Que bus vas a coger?")

            Text(text = "Compania")

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                var isExpanded by remember { mutableStateOf(false) }
                Column {

                    CustomTextField(
                        textFieldValue = busCompany,
                        onTextFieldValueChange = { busCompany = it },
                        label = "Compania de Bus",
                        placeHolder = "Serviagosto",
                        keyboardType = 3,
                        hasTrailingIcon = true,
                        openDropDownMenu = { isExpanded = true },
                    )
                    DropdownMenu(expanded = isExpanded, onDismissRequest = {
                        isExpanded = false
                    }) {
                        DropdownMenuItem(onClick = { Log.d("DDMenu", "menu item 1 clicked") }) {
                            Text(text = "item 1")
                        }
                        DropdownMenuItem(onClick = { Log.d("DDMenu", "menu item 2 clicked") }) {
                            Text(text = "item 2")
                        }
                    }
                }
            }
            Text(text = "Numero de bus")
            CustomTextField(
                textFieldValue = busId,
                onTextFieldValueChange = { busId = it },
                label = "Numero de Bus",
                placeHolder = "0000",
                keyboardType = 2,
            )
        }
    }

}

@Composable
fun CustomFAB(navController: NavController) {
    FloatingActionButton(onClick = { /*TODO start run*/ navController.navigate(AppScreens.RunScreen.name) }) {
        Icon(
            imageVector = Icons.Rounded.PlayArrow,
            contentDescription = "start run button",
            modifier = Modifier
                .size(35.dp),
            tint = Color.White
        )
    }
}