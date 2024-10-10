package com.hyperio.hyperinventory.ui.start

import androidx.lifecycle.ViewModel
import com.hyperio.hyperinventory.domain.model.InventoryItem

class StartViewModel: ViewModel() {
    companion object {
        private const val TAG = "StartViewModel"
        private const val TIME_INTERVAL = 1000L
    }

    /**
     * Ui State for HomeScreen
     */
    data class StartUiState(val itemList: List<InventoryItem> = listOf())
}