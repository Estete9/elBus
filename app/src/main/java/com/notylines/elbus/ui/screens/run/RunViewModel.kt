package com.notylines.elbus.ui.screens.run

import androidx.lifecycle.ViewModel
import com.notylines.elbus.services.LocationService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class RunViewModel : ViewModel() {

    private val _runUiState = MutableStateFlow(RunState())
    val runUiState: StateFlow<RunState> = _runUiState.asStateFlow()

    fun updatePolyline() {
        val lastPosition = LocationService.currentPosition.value
        if (lastPosition != null) {
            _runUiState.value.polyline.add(lastPosition)
        }
    }
}
