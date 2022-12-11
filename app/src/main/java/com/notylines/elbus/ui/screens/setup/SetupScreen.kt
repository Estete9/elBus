package com.notylines.elbus.ui.screens.setup

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.notylines.elbus.ui.navigation.AppScreens

@Composable
fun SetupScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),

        ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = AppScreens.SetupScreen.name)

            Button(onClick = { navController.navigate(AppScreens.RunScreen.name) }) {
                Text(text = "to run")
            }
        }
    }

}