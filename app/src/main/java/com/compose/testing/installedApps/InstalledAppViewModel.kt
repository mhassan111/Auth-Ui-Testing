package com.compose.testing.installedApps

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.testing.installedApps.repository.InstalledAppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InstalledAppViewModel @Inject constructor(
    private val installedAppRepository: InstalledAppRepository
) : ViewModel() {

    private val _state = mutableStateOf(InstalledAppsState())
    val state: State<InstalledAppsState> = _state

//    init {
//        loadInstalledApps()
//    }

    fun loadInstalledApps() {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            val apps = installedAppRepository.getInstalledApps()
            _state.value = state.value.copy(
                isLoading = false,
                installedApps = apps
            )
        }
    }

    data class InstalledAppsState(
        val isLoading: Boolean = false,
        val installedApps: List<InstalledApp> = emptyList()
    )
}