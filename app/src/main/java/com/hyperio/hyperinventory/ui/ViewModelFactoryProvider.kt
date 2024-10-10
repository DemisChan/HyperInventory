package com.hyperio.hyperinventory.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hyperio.hyperinventory.InventoryApplication
import com.hyperio.hyperinventory.ui.inventoryEntry.EntryDetailsViewModel
import com.hyperio.hyperinventory.ui.inventoryEntry.EntryEditViewModel
import com.hyperio.hyperinventory.ui.inventoryEntry.EntryViewModel
import com.hyperio.hyperinventory.ui.start.StartViewModel

object ViewModelFactoryProvider {
    val Factory = viewModelFactory {

        initializer {
            StartViewModel(inventoryApp().container.itemsRepository)
        }

        // initializer for EntryEditViewModel
        initializer {
            EntryDetailsViewModel(
                this.createSavedStateHandle(),
                inventoryApp().container.itemsRepository
            )
        }

        // initializer for EntryViewModel
        initializer {
            EntryViewModel(inventoryApp().container.itemsRepository)
        }

        initializer {
            EntryEditViewModel(
                this.createSavedStateHandle(),
                inventoryApp().container.itemsRepository
            )
        }
    }
}

fun CreationExtras.inventoryApp(): InventoryApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)