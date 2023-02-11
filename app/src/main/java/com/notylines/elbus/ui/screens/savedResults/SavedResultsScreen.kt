package com.notylines.elbus.ui.screens.savedResults

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.notylines.elbus.components.SavedRunCard
import com.notylines.elbus.ui.screens.run.RunViewModel

@Composable
fun SavedResultsScreen(navController: NavController, viewModel: RunViewModel) {
    Scaffold(modifier = Modifier.fillMaxSize()) {

        viewModel.getAllRuns()
        val runs = viewModel.savedRunsState.collectAsState()
        Column {

            Text(
                text = "Trayectos Guardados",
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 10.dp),
                textAlign = TextAlign.Center
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(runs.value) { run ->
                    SavedRunCard()
                }
            }
        }
    }
}