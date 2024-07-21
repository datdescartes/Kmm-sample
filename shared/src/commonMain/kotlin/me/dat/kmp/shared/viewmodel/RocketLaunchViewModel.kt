package me.dat.kmp.shared.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.dat.kmp.shared.data.SpaceXRepository
import me.dat.kmp.shared.domain.model.RocketLaunchModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RocketLaunchViewModel: ViewModel(), KoinComponent {
    private val repository: SpaceXRepository by inject()

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        loadLaunches()
    }

    fun loadLaunches() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, launches = emptyList())
            try {
                val launches = repository.getAllLaunches(forceReload = true)
                _state.value = _state.value.copy(isLoading = false, launches = launches)
            } catch (e: Exception) {
                e.printStackTrace()
                _state.value = _state.value.copy(isLoading = false, launches = emptyList())
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val launches: List<RocketLaunchModel> = emptyList()
    )
}