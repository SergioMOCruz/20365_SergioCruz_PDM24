package com.sergio.store.data.model

import com.sergio.store.model.CartItem

data class CartItemDto(
    val productId: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val quantity: Int = 0,
    val imageUrl: String = ""
)

fun List<CartItem>.toDto(ownerId: String): CartDto {
    val dtoItems = this.map { cartItem ->
        CartItemDto(
            productId = cartItem.product.id,
            name = cartItem.product.name,
            price = cartItem.product.price,
            quantity = cartItem.quantity,
            imageUrl = cartItem.product.imageUrl
        )
    }

    return CartDto(
        ownerId = ownerId,
        items = dtoItems,
        timestamp = com.google.firebase.Timestamp.now(),
        shared = false
    )
}