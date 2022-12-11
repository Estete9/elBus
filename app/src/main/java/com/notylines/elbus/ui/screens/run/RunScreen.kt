package com.notylines.elbus.ui.screens.run

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
fun RunScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),

        ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = AppScreens.RunScreen.name)

            Button(onClick = { navController.navigate(AppScreens.ResultScreen.name){
                popUpTo(AppScreens.SetupScreen.name)
            } }) {

                Text(text = "to result")
            }
        }
    }

}