package com.hyperio.hyperinventory.data.repository

import android.content.Context
import com.hyperio.hyperinventory.data.database.InventoryDatabase


/**
 * App container for Dependency injection.
 */
interface RepositoryContainer {
    val itemsRepository: ItemsRepository
}

/**
 * [RepositoryContainer] implementation that provides instance of [OfflineInventoryItemsRepository]
 */
class RepositoryImpl(private val context: Context) : RepositoryContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val itemsRepository: ItemsRepository by lazy {
        OfflineInventoryItemsRepository(InventoryDatabase.getDatabase(context).inventoryDao())
    }
}