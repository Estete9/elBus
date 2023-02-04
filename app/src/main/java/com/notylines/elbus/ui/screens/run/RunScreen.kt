package com.notylines.elbus.ui.screens.run

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.notylines.elbus.db.RunDatabase
import com.notylines.elbus.repository.RunRepository
import com.notylines.elbus.services.LocationService
import com.notylines.elbus.ui.screens.setup.sendCommandToService
import com.notylines.elbus.utils.GoogleMapView

@Composable
fun RunScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current
        val isFirstUpdate = remember { mutableStateOf(true) }

        val repository = RunRepository(RunDatabase(context))

        Log.d("RUNSCREEN", "isFirstUpdate is ${isFirstUpdate.value}")


        Box(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier, contentAlignment = Alignment.Center
            ) {
                GoogleMapView(
                    isFirstUpdate = isFirstUpdate.value,
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
                                    Log.d("RUNSCREEN", "RunScreen: finished run")

                                }) {
                                    Text(text = "Terminar")
                                }
                                OutlinedButton(onClick = {
//                          user cancels the run
                                    sendCommandToService(
                                        context,
                                        LocationService.SERVICE_STOP
                                    )
//                          TODO think of what happens after they cancel the run
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

