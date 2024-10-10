package com.hyperio.hyperinventory.ui.inventoryEntry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyperio.hyperinventory.data.repository.ItemsRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EntryEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: ItemsRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            entryUiState = itemsRepository.getItem(itemId)
                .filterNotNull()
                .first()
                .toEntryUiState(true)
        }
    }

    var entryUiState by mutableStateOf(EntryUiState())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[EntryEditDestination.entryIdArg])

    suspend fun updateEntry() {
        if (validateInput(entryUiState.entryDetails)) {
            itemsRepository.upsertItem(entryUiState.entryDetails.toEntry())
        }
    }

    fun updateEntryUiState(entryDetails: EntryDetails) {
        entryUiState =
            EntryUiState(entryDetails = entryDetails, isEntryValid = validateInput(entryDetails))
    }

    private fun validateInput(uiState: EntryDetails = entryUiState.entryDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}