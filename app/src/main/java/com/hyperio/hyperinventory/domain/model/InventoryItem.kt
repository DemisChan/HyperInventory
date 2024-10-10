package com.hyperio.hyperinventory.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "InventoryTable")
data class InventoryItem(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    val name: String,
    var quantity: Int,
    val price: Double,
) {
    val totalPrice: Double
        get() = price.times(quantity)

    fun formatedPrice(): String {
        return "$$price"
    }

    fun formatedTotalPrice(): String {
        return "$$totalPrice"
    }
}