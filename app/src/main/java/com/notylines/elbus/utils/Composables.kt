package com.notylines.elbus.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.RoundCap
import com.google.maps.android.compose.*
import com.notylines.elbus.services.LocationService


@Composable
fun GoogleMapView(isFirstUpdate: Boolean, updateFirstLocation: (Boolean) -> Unit) {

    val lastPosition by LocationService.currentPosition.collectAsState()
    val locations = remember { mutableListOf<LatLng>() }


    val cameraPositionState = rememberCameraPositionState {
        if (locations.isNotEmpty()) {
            position = CameraPosition.fromLatLngZoom(
                LatLng(locations.last().latitude, locations.last().longitude),
                18f
            )
        }
    }
//    checks if user finished the run and creates a Bound with the locations list and
//    a cameraUpdateFactory so the camera can zoom out and hold the complete polyline
    LaunchedEffect(key1 = LocationService.finishedRun.value) {
        if (LocationService.finishedRun.value && locations.isNotEmpty()) {
            val finalBounds = finishedRunBounds(locations)
            val finalCameraPosition =
                CameraUpdateFactory.newLatLngBounds(finalBounds, 30)

            cameraPositionState.animate(finalCameraPosition)
        }
    }
//    Checks if the camera state changes, then if the camera is not moving and the run is finished
//    it takes a screenshot and saves it
    LaunchedEffect(key1 = cameraPositionState.isMoving) {
        if (!cameraPositionState.isMoving && LocationService.finishedRun.value) {
//            TODO implement Room, save the image information to Room and navigate to ResultScreen
//              and display image and information

        }
    }

//      checks for updates of the position emitted by the Location Service, updates the list of locations
//      creates a location based on the last position on the list and updates the camera to it
    LaunchedEffect(key1 = lastPosition) {
        if (lastPosition != null) {
            locations.add(lastPosition!!)
        }
        if (locations.isNotEmpty()) {
            val update = CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    locations.last().latitude,
                    locations.last().longitude
                ),
                18f
            )

            if (isFirstUpdate) {
                cameraPositionState.move(update)
                updateFirstLocation(false)
                if (locations.isNotEmpty()) {
                    locations.removeAt(0)
                }
            } else {
                if (locations.size < 2) cameraPositionState.move(update)
                cameraPositionState.animate(update, 1000)
            }
        }

    }
    val mapProperties = MapProperties(isMyLocationEnabled = true)
    val mapUiSettings = MapUiSettings(zoomControlsEnabled = false)

    GoogleMap(
        modifier = Modifier.fillMaxWidth(),
        cameraPositionState = cameraPositionState,
        uiSettings = mapUiSettings,
        properties = mapProperties
    ) {
        if (locations.isNotEmpty()) {
            Polyline(
                points = locations.toList(),
                color = Color.Red,
                endCap = RoundCap(),
                startCap = RoundCap(),
                width = 20F
            )
        }

    }
}

//loops through the saved locations and selects the lowest and highest points to create
//the run bounds for the camera to update itself
private fun finishedRunBounds(locations: MutableList<LatLng>): LatLngBounds {
    var south = locations.first().latitude
    var north = locations.first().latitude
    var east = locations.first().longitude
    var west = locations.first().longitude

    for (location in locations) {
        if (location.latitude < south) south = location.latitude
        if (location.latitude > north) north = location.latitude
        if (location.longitude < west) west = location.longitude
        if (location.longitude > east) east = location.longitude
    }
    return LatLngBounds(LatLng(south, west), LatLng(north, east))
}
