package com.notylines.elbus.ui.screens.run

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.notylines.elbus.db.Run
import com.notylines.elbus.services.LocationService
import com.notylines.elbus.ui.navigation.AppScreens
import com.notylines.elbus.ui.screens.setup.sendCommandToService
import com.notylines.elbus.utils.GoogleMapView

@Composable
fun RunScreen(navController: NavController, viewModel: RunViewModel = viewModel()) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current
        val isFirstUpdate = remember { mutableStateOf(true) }
        val runState by viewModel.runUiState.collectAsState()

        Box(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier, contentAlignment = Alignment.Center
            ) {
                GoogleMapView(
                    isFirstUpdate = isFirstUpdate.value,
                    updateLocations = { viewModel.updatePolyline() },
                    locations = runState.polyline,
                    updateFirstLocation = { isFirstUpdate.value = it })

            }

            if (!LocationService.finishedRun.value) {

                Box(
                    modifier = Modifier
                        .padding(30.dp)
                        .align(Alignment.BottomCenter),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        elevation = 5.dp,
                        shape = RoundedCornerShape(10.dp)
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 25.dp, bottom = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = "00:00:00:00", fontSize = 45.sp
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
//                            user finishes route and wants to save the run
                                Button(onClick = {
                                    sendCommandToService(
                                        context,
                                        LocationService.SERVICE_STOP
                                    )
                                    LocationService.finishedRun.value = true
                                    viewModel.saveRun(
                                        Run(
                                            duration = "1000",
                                            maxSpeed = "100mk/h",
                                            maxSpeedDuration = "5s",
                                            numberMaxSpeed = 7
                                        )
                                    )
                                    navController.navigate(AppScreens.SavedResultsScreen.name)
                                }) {
                                    Text(text = "Terminar")
                                }
                                OutlinedButton(onClick = {
//                          user cancels the run
                                    sendCommandToService(
                                        context,
                                        LocationService.SERVICE_STOP
                                    )
//                           TODO think of what happens after they cancel the run
                                }) {
                                    Text(text = "Cancelar")
                                }

                            }
                        }
                    }
                }
            }
        }
    }

}

