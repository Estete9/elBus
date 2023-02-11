package com.notylines.elbus.ui.screens.run

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.notylines.elbus.db.Run
import com.notylines.elbus.db.RunDatabase
import com.notylines.elbus.repository.RunRepository
import com.notylines.elbus.services.LocationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class RunViewModel(application: Application) : AndroidViewModel(application) {

    private val _runUiState = MutableStateFlow(RunState())
    val runUiState: StateFlow<RunState> = _runUiState.asStateFlow()

    private val _savedRunsState = MutableStateFlow(listOf<Run>())
    val savedRunsState: StateFlow<List<Run>> = _savedRunsState.asStateFlow()

    private val repository = RunRepository(RunDatabase(application.applicationContext))
    fun updatePolyline() {
        val lastPosition = LocationService.currentPosition.value
        if (lastPosition != null) {
            _runUiState.value.polyline.add(lastPosition)
        }
    }

    fun getAllRuns() =
        viewModelScope.launch(Dispatchers.IO) { _savedRunsState.value = repository.getSavedRuns() }

    fun saveRun(run: Run) = viewModelScope.launch { repository.addRun(run) }
    fun deleteRun(run: Run) = viewModelScope.launch { repository.deleteRun(run) }
}
