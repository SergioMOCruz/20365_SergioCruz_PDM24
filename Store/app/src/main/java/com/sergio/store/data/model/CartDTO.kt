package com.sergio.store.data.model

import com.sergio.store.domain.model.Product
import com.sergio.store.model.CartItem

data class CartDto(
    val ownerId: String = "",
    val items: List<CartItemDto> = emptyList(),
    val timestamp: com.google.firebase.Timestamp? = null,
    val shared: Boolean = false
)

fun CartDto.toDomainCartItems(): List<CartItem> {
    return items.map { dto ->
        CartItem(
            product = Product(
                id = dto.productId,
                name = dto.name,
                price = dto.price,
                imageUrl = dto.imageUrl
            ),
            quantity = dto.quantity
        )
    }
}