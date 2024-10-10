package com.hyperio.hyperinventory.ui.inventoryEntry

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hyperio.hyperinventory.data.repository.ItemsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class EntryDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: ItemsRepository,
) : ViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[EntryDetailsDestination.entryIdArg])

    val uiState: StateFlow<EntryDetailsUiState> =
        itemsRepository.getItem(itemId)
            .filterNotNull()
            .map {
                EntryDetailsUiState(
                    outOfStock = it.quantity <= 0,
                    entryDetails = it.toEntryDetails()
                )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIME_INTERVAL),
                initialValue = EntryDetailsUiState()
            )

    suspend fun deleteEntry() {
        itemsRepository.deleteItem(uiState.value.entryDetails.toEntry())
    }

    companion object {
        private const val TIME_INTERVAL = 1000L
    }
}


data class EntryDetailsUiState(
    val outOfStock: Boolean = true,
    val entryDetails: EntryDetails = EntryDetails(),
)

