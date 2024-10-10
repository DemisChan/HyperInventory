package com.hyperio.hyperinventory.data.repository

import com.hyperio.hyperinventory.domain.model.InventoryItem
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {

    fun getAllItems(): Flow<List<InventoryItem>>
    fun getItem(uid: Int): Flow<InventoryItem>

    suspend fun upsertItem(item: InventoryItem)

    suspend fun deleteItem(item: InventoryItem)

}