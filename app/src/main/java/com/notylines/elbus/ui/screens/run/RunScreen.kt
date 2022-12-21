package com.notylines.elbus.ui.screens.run

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun RunScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),

        ) {

        val casa = LatLng(-0.1964991,-78.5082015)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(casa, 19f)
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.padding(12.dp)) {

                GoogleMap(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(520.dp),
                    cameraPositionState = cameraPositionState
                ) {
                    Circle(
                        center = casa,
                        fillColor = MaterialTheme.colors.secondary,
                        radius = 2.0,
                        strokeColor = Color.Transparent
                    )
                }
            }
        }
    }

}