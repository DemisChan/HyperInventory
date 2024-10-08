package com.hyperio.hyperinventory.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hyperio.hyperinventory.data.model.InventoryItem

@Database(entities = [InventoryItem::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun inventoryDao(): InventoryDao
}