package com.hyperio.hyperinventory.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "InventoryTable")
data class InventoryItem(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val name: String,
    var quantity: Int,
    val price: BigDecimal,
) {
    val totalPrice: BigDecimal
        get() = price.times(BigDecimal(quantity))
}