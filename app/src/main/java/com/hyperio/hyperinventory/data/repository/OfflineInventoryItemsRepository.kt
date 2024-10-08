package com.hyperio.hyperinventory.data.repository

import com.hyperio.hyperinventory.data.database.InventoryDao
import com.hyperio.hyperinventory.domain.model.InventoryItem
import kotlinx.coroutines.flow.Flow

class OfflineInventoryItemsRepository(private val inventoryItemsDao: InventoryDao): ItemsRepository {

    override fun getAllItems(): Flow<List<InventoryItem>> = inventoryItemsDao.getItems()


    override fun getItem(uid: Int): Flow<InventoryItem> = inventoryItemsDao.getItem(uid)

    override suspend fun upserItem(item: InventoryItem) = inventoryItemsDao.upsertItem(item)

    override suspend fun deleteItem(item: InventoryItem) = inventoryItemsDao.deleteItem(item)
}