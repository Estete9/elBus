package com.notylines.elbus.ui.screens.setup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.notylines.elbus.ui.navigation.AppScreens


@Composable
fun SetupScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = { CustomFAB() }
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

@Preview(showBackground = true)
@Composable
fun CustomFAB() {
    FloatingActionButton(onClick = { /*TODO start run*/ }) {
        Icon(
            imageVector = Icons.Rounded.PlayArrow,
            contentDescription = "start run button",
            modifier = Modifier
                .size(35.dp),
            tint = Color.White
        )
    }
}