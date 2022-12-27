package com.setiadev.tango.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setiadev.tango.data.PlayerRepository
import com.setiadev.tango.model.PlayerList
import com.setiadev.tango.ui.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: PlayerRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<PlayerList>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<PlayerList>>>
    get() = _uiState

    fun getAllPlayers() {
        viewModelScope.launch {
            repository.getAllPlayers()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { playerList ->
                    _uiState.value = UiState.Success(playerList)
                }
        }
    }
}