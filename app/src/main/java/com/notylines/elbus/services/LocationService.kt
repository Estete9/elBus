package com.notylines.elbus.services

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.location.Location
import android.os.IBinder
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

typealias Polyline = MutableList<LatLng>
typealias Polylines = MutableList<Polyline>

class LocationService() : Service() {

    companion object {
        const val SERVICE_START = "SERVICE_START"
        const val SERVICE_STOP = "SERVICE_STOP"
//        TODO find a way to remove this starting pathPoint
        val pathPoints = MutableStateFlow(LatLng(-0.250789, -79.175610))
        val isTracking = mutableStateOf(true)

    }

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    lateinit var locationClient: LocationClient

    override fun onCreate() {
        super.onCreate()
        Log.d("LOCATIONSERVICESTART", "service created")
        locationClient = LocationClient(
            applicationContext,
            LocationServices.getFusedLocationProviderClient(applicationContext),
        )
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when (intent?.action) {
            SERVICE_START -> start()
            SERVICE_STOP -> stop()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        Log.d("LOCATIONSERVICESTART", "service start")
        isTracking.value = true

        locationClient.getLocationUpdates()
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
// TODO register the location information
                val pos = LatLng(location.latitude, location.longitude)
                pathPoints.value = pos
                Log.d("LOCATIONSERVICESTART", "path added $location")
                Log.d("LOCATIONSERVICESTART", "path list ${pathPoints}")
            }
            .launchIn(serviceScope)
    }

    private fun stop() {
        stopSelf()
        isTracking.value = false
    }

    @SuppressLint("MissingPermission")
    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

}
