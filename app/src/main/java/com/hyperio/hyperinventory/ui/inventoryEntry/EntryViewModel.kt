package com.hyperio.hyperinventory.ui.inventoryEntry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.hyperio.hyperinventory.data.repository.ItemsRepository
import com.hyperio.hyperinventory.domain.model.InventoryItem

class EntryViewModel(
    private val itemsRepository: ItemsRepository
) : ViewModel() {

    var entryUiState by mutableStateOf(EntryUiState())
        private set

    fun updateEntryUiState(entryDetails: EntryDetails) {
        entryUiState = EntryUiState(
            entryDetails = entryDetails,
            isEntryValid = validateInput(entryDetails)
        )
    }

    suspend fun saveEntry() {
        if (validateInput()) {
            itemsRepository.upsertItem(entryUiState.entryDetails.toEntry())
        }
    }

    private fun validateInput(uiState: EntryDetails = entryUiState.entryDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && quantity.isNotBlank() && price.isNotBlank()
        }
    }
}

data class EntryUiState(
    val entryDetails: EntryDetails = EntryDetails(),
    val isEntryValid: Boolean = false
)

data class EntryDetails(
    val uid: Int = 0,
    val name: String = "",
    var quantity: String = "",
    val price: String = "",
)

fun EntryDetails.toEntry(): InventoryItem = InventoryItem(
    uid = uid,
    name = name,
    quantity = quantity.toIntOrNull() ?: 0,
    price = price.toDoubleOrNull() ?: 0.0
)

fun InventoryItem.toEntryUiState(isEntryValid: Boolean = false): EntryUiState = EntryUiState(
    entryDetails = this.toEntryDetails(),
    isEntryValid = isEntryValid
)

fun InventoryItem.toEntryDetails(): EntryDetails = EntryDetails(
    uid = uid,
    name = name,
    quantity = quantity.toString(),
    price = price.toString()
)