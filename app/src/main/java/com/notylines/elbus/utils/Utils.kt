package com.notylines.elbus.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.notylines.elbus.services.LocationService


fun checkPermissions(
    context: Context,
    onPermissionUpdate: (Boolean) -> Unit = {},
    onPermissionRequest: () -> Unit = {}
) {

    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PERMISSION_GRANTED
        && ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PERMISSION_GRANTED

    ) {
        onPermissionUpdate(true)
    } else {
        onPermissionRequest()
    }

}


fun Context.hasLocationPermissions(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
}

@Composable
fun GoogleMapView(isFirstUpdate: Boolean, updateFirstLocation: (Boolean) -> Unit) {

    val lastPosition by LocationService.currentPosition.collectAsState()
    val locations = remember { mutableListOf<LatLng>() }

    Log.d("GOOGLEVIEW", "outside launched effect locations: $locations")
    val cameraPositionState = rememberCameraPositionState {
        if (locations.isNotEmpty()) {
            position = CameraPosition.fromLatLngZoom(
                LatLng(locations.last().latitude, locations.last().longitude),
                18f
            )
        }
    }
    Log.d("GOOGLEVIEW", "location in map compose outside of launched effect is $locations")
    LaunchedEffect(key1 = lastPosition) {
        Log.d("GOOGLEVIEW", "location in map compose inside of launched effect is $locations")
        if (lastPosition != null) {
            locations.add(lastPosition!!)
        }
        Log.d("GOOGLEVIEW", "inside launched effect locations: $locations")
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
        Log.d("GOOGLEVIEW", "GoogleMapView: $isFirstUpdate")

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
            Polyline(points = locations.toList(), color = Color.Red)
        }
    }
}