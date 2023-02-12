package com.notylines.elbus.ui.screens.run

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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

    private val _savedRunsState = mutableStateListOf<Run>()
    val savedRunsState: List<Run> = _savedRunsState

    val isLoading = mutableStateOf(true)
    val updatingPolyline = mutableStateOf<Boolean?>(null)

    private val repository = RunRepository(RunDatabase(application.applicationContext))


    fun updatePolyline() {
        updatingPolyline.value = true
        val lastPosition = LocationService.currentPosition.value
        if (lastPosition != null) {
            _runUiState.value.polyline.add(lastPosition)
        }
        updatingPolyline.value = false
    }

    fun getAllRuns() =
        viewModelScope.launch(Dispatchers.IO) {
        isLoading.value = true
            _savedRunsState.addAll(repository.getSavedRuns())
        isLoading.value = false
        }

    fun saveRun(run: Run) = viewModelScope.launch { repository.addRun(run) }
    fun deleteRun(run: Run) = viewModelScope.launch { repository.deleteRun(run) }

    fun removeFromList(run: Run) = _savedRunsState.remove(run)

}
