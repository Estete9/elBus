package com.notylines.elbus.ui.screens.savedResults

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.DismissValue.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.notylines.elbus.components.SavedRunCard
import com.notylines.elbus.db.Run
import com.notylines.elbus.ui.screens.run.RunViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SavedResultsScreen(navController: NavController, viewModel: RunViewModel) {
    Scaffold(modifier = Modifier.fillMaxSize()) {

        val isLoading = remember { mutableStateOf(viewModel.isLoading.value) }
        Log.d(
            "GETALLRUNS",
            "SavedResultsScreen: isloading ${isLoading.value}"
        )
        LaunchedEffect(key1 = viewModel.updatingPolyline.value) {
            Log.d(
                "GETALLRUNS",
                "SavedResultsScreen: updatingpolyline changed and get all runs running"
            )
            if (!viewModel.updatingPolyline.value!!) viewModel.getAllRuns()
        }

        val runs = viewModel.savedRunsState

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
                items(runs, { runList: Run -> runList.uid!! }) { run ->
                    val dismissState = rememberDismissState()
                    if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                        viewModel.deleteRun(run)
                        viewModel.removeFromList(run)
                    }
                    SwipeToDismiss(
                        state = dismissState,
                        background = {

                            val color by animateColorAsState(
                                when (dismissState.targetValue) {
                                    Default -> Color.Transparent
                                    DismissedToStart -> Color.Red.copy(alpha = 0.8f)
                                    DismissedToEnd -> Color.Transparent
                                }
                            )
                            val scale by animateFloatAsState(if (dismissState.targetValue == Default) 0.75f else 1.5f)

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(color)
                                    .padding(20.dp),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    modifier = Modifier.scale(scale),
                                    contentDescription = "delete icon"
                                )
                            }

                        },
                        dismissThresholds = { direction ->
                            FractionalThreshold(if (direction == DismissDirection.EndToStart) 0.2f else 0.05f)
                        },
                        directions = setOf(DismissDirection.EndToStart)
                    ) {

                        SavedRunCard(run)
                    }
                }
            }
        }
    }


}