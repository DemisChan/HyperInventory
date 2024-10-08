package com.hyperio.hyperinventory.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.hyperio.hyperinventory.domain.model.InventoryItem
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDao {

    @Query("SELECT * FROM InventoryTable")
    fun getItems(): Flow<List<InventoryItem>>

    @Query("SELECT * FROM InventoryTable WHERE uid = :uid")
    fun getItem(uid: Int): Flow<InventoryItem>

    @Query("UPDATE InventoryTable SET quantity = :newQuantity WHERE uid = :uid")
    suspend fun updateQuantity(uid: Int, newQuantity: Int)

    @Upsert
    suspend fun upsertItem(item: InventoryItem)


    @Delete
    suspend fun deleteItem(item: InventoryItem)



}