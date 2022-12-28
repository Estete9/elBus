package com.notylines.elbus.ui.screens.run

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun RunScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {

        val casa = LatLng(-0.1964991, -78.5082015)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(casa, 19f)
        }

        val mapUiSettings = MapUiSettings(zoomControlsEnabled = false, zoomGesturesEnabled = true)

        Box(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center
            ) {

                GoogleMap(
                    modifier = Modifier
                        .fillMaxWidth(),
                    cameraPositionState = cameraPositionState,
                    uiSettings = mapUiSettings
                ) {
                    Circle(
                        center = casa,
                        fillColor = MaterialTheme.colors.secondary,
                        radius = 2.0,
                        strokeColor = Color.Transparent
                    )
                }
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
                            .padding(top = 20.dp, bottom = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "00:00:00:00",
                            fontSize = 45.sp
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {

                            OutlinedButton(onClick = { /*TODO*/ }) {
                                Text(text = "Detener")
                            }
                            Button(onClick = { /*TODO*/ }) {
                                Text(text = "Terminar")
                            }
                        }
                    }
                }
            }
        }
    }

}