package com.iqbaltio.jetpacksubmission.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iqbaltio.jetpacksubmission.data.GamesRepository
import com.iqbaltio.jetpacksubmission.model.ListGame
import com.iqbaltio.jetpacksubmission.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: GamesRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<ListGame>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<ListGame>>
        get() = _uiState

    fun getDetailById(gameId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getFavoriteGameById(gameId))
        }
    }
}