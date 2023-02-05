package com.notylines.elbus.ui.screens.run

import com.google.android.gms.maps.model.LatLng

data class RunState(
    val polyline: MutableList<LatLng> = mutableListOf()
)