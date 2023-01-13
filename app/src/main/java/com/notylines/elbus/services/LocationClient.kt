package com.notylines.elbus.services

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.provider.Settings
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.notylines.elbus.utils.hasLocationPermissions
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class LocationClient(
    private val context: Context,
    private val locationClient: FusedLocationProviderClient,
    val addPathPoint: (Location) -> Unit
) {

    @SuppressLint("MissingPermission")
    fun getLocationUpdates(): Flow<Location> {
        return callbackFlow {
            if (!context.hasLocationPermissions()) {
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                context.startActivity(intent)

                throw Exception("Missing location permissions")
            }

            val locationManager =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            if (hasProvidersEnabled(locationManager)) {
                throw Exception("GPS is disabled")
            }

            val request = LocationRequest.Builder(5000L)
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .setWaitForAccurateLocation(false)
                .setMinUpdateIntervalMillis(2000L)
                .build()

            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                    result.locations.lastOrNull()?.let { location ->
//    TODO change this so we register the location somewhere and we use it to move camera to current position
//                        launch { send(location) } (this might be necessary because of coroutine)
                        addPathPoint(location)
                    }
                }
            }

            locationClient.requestLocationUpdates(
                request,
                locationCallback,
                Looper.getMainLooper()
            )
            awaitClose {
                locationClient.removeLocationUpdates(locationCallback)
            }
        }
    }

    private fun hasProvidersEnabled(locationManager: LocationManager): Boolean {
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled =
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        return !isGpsEnabled && !isNetworkEnabled

    }

//    private fun addPathPoint(location: Location) {
//        val pos = LatLng(location.latitude, location.longitude)
//        pathPoints.value?.apply {
//            last().add(pos)
//            pathPoints.postValue(this)
//        }
//    }

}