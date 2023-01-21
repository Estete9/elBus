package com.notylines.elbus.ui.screens.run

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.notylines.elbus.services.LocationService

@Composable
fun RunScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {

        val isFirstUpdate = remember { mutableStateOf(true) }
        Log.d("RUNSCREEN", "isFirstUpdate is ${isFirstUpdate.value}")


        Box(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier, contentAlignment = Alignment.Center
            ) {

                GoogleMapView(
                    isFirstUpdate = isFirstUpdate.value,
                    updateFirstUpdate = { isFirstUpdate.value = it })
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
//                                sendCommandToService(
//                                    context,
//                                    LocationService.SERVICE_STOP
//                                )
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

@Composable
private fun GoogleMapView(isFirstUpdate: Boolean, updateFirstUpdate: (Boolean) -> Unit) {
    val location by LocationService.pathPoints.collectAsState()
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(location.latitude, location.longitude),
            18f
        )
    }
    Log.d("GOOGLEVIEW", "location in map compose outside of launched effect is $location")
    LaunchedEffect(key1 = location) {
        Log.d("GOOGLEVIEW", "location in map compose inside of launched effect is $location")

        val update = CameraUpdateFactory.newLatLng(LatLng(location.latitude, location.longitude))

        if (isFirstUpdate) {
            cameraPositionState.move(update)
            updateFirstUpdate(false)
        } else {
            cameraPositionState.animate(update, 500)
        }

    }
    val mapProperties = MapProperties(isMyLocationEnabled = true)
    val mapUiSettings = MapUiSettings(zoomControlsEnabled = false)

    GoogleMap(
        modifier = Modifier.fillMaxWidth(),
        cameraPositionState = cameraPositionState,
        uiSettings = mapUiSettings,
        properties = mapProperties
    )
}