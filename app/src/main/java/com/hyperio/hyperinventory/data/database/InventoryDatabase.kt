package com.hyperio.hyperinventory.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hyperio.hyperinventory.domain.model.InventoryItem

@Database(entities = [InventoryItem::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun inventoryDao(): InventoryDao

    companion object {
        const val DATABASE_NAME = "inventory_database"

        @Volatile
        private var Instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }

    }

}