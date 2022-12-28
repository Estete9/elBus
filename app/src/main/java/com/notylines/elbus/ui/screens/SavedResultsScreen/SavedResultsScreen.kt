package com.notylines.elbus.ui.screens.SavedResultsScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun SavedResultsScreen(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxSize()) {

        val runs = listOf<String>(
            "run 1",
            "run 2",
            "run 3",
            "run 4",
            "run 5",
            "run 6",
            "run 7",
            "run 8",
            "run 9",
            "run 10",
            "run 11",
            "run 12",
            "run 13",
            "run 14"
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Text(
                    text = "Trayectos guardados",
                    style = MaterialTheme.typography.h5
                )
            }
            items(runs) {

            }
        }
    }
}