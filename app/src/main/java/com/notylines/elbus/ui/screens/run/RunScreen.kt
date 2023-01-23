package com.notylines.elbus.ui.screens.run

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.*
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
        Log.d("RUNSCREEN", "isFirstUpdate is ${isFirstUpdate.value}")


        Box(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier, contentAlignment = Alignment.Center
            ) {
                GoogleMapView(
                    isFirstUpdate = isFirstUpdate.value,
                    updateFirstLocation = { isFirstUpdate.value = it })
            }
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
                            Button(onClick = { /*TODO*/ }) {
                                Text(text = "Terminar")
                            }
                            OutlinedButton(onClick = {
//                                TODO find a way to hoist the send command to service
//                                 fun and use this button to stop the service
                                sendCommandToService(
                                    context,
                                    LocationService.SERVICE_STOP
                                )

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

