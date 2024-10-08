package com.hyperio.hyperinventory.data.model


data class InventoryItem(
    val name: String,
    val quantity: Int,
    val price: Double,
) {
    val totalPrice: Double
        get() = price * quantity

    val id: Int get() = hashCode()
}