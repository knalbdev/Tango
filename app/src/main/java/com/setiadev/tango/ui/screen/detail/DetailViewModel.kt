package com.setiadev.tango.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setiadev.tango.data.PlayerRepository
import com.setiadev.tango.model.PlayerList
import com.setiadev.tango.ui.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: PlayerRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<PlayerList>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<PlayerList>>
    get() = _uiState

    fun getPlayerById(playerId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getPlayer(playerId))
        }
    }
}