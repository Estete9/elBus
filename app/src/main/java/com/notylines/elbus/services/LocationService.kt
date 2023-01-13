package com.notylines.elbus.services

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.location.Location
import android.os.IBinder
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

typealias Polyline = MutableList<LatLng>
typealias Polylines = MutableList<Polyline>

class LocationService() : Service() {

    companion object {
        const val SERVICE_START = "SERVICE_START"
        const val SERVICE_STOP = "SERVICE_STOP"

        val pathPoints = MutableLiveData<Polylines>()
    }

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    lateinit var locationClient: LocationClient

    override fun onCreate() {
        super.onCreate()
        setInitialValues()

        locationClient = LocationClient(
            applicationContext,
            LocationServices.getFusedLocationProviderClient(applicationContext),
            addPathPoint = { addPathPoint(it) }
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
        locationClient.getLocationUpdates()
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
// TODO register the location information
            }
            .launchIn(serviceScope)
    }

    private fun stop() {
        stopSelf()
    }

    @SuppressLint("MissingPermission")
    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }


    private fun setInitialValues() {
        pathPoints.postValue(mutableListOf())
    }

    private fun addPathPoint(location: Location) {
        val pos = LatLng(location.latitude, location.longitude)
        pathPoints.value?.apply {
            last().add(pos)
            pathPoints.postValue(this)
        }
    }


}
