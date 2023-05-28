package com.iqbaltio.jetpacksubmission.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iqbaltio.jetpacksubmission.data.GamesRepository
import com.iqbaltio.jetpacksubmission.model.ListGame
import com.iqbaltio.jetpacksubmission.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: GamesRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<ListGame>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<ListGame>>>
        get() = _uiState

    fun getAllGames() {
        viewModelScope.launch {
            repository.getAllGames()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }
}
