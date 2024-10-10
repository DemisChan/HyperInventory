package com.hyperio.hyperinventory.ui.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyperio.hyperinventory.data.repository.ItemsRepository
import com.hyperio.hyperinventory.domain.model.InventoryItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class StartViewModel(
    itemsRepository: ItemsRepository
) : ViewModel() {
    val homeUiState: StateFlow<StartUiState> =
        itemsRepository.getAllItems().map { StartUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIME_INTERVAL),
                initialValue = StartUiState()
            )

    companion object {
        private const val TAG = "StartViewModel"
        private const val TIME_INTERVAL = 5000L
    }

    /**
     * Ui State for HomeScreen
     */
    data class StartUiState(val itemList: List<InventoryItem> = listOf())
}