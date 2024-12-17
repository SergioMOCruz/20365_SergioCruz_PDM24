package com.sergio.store.model

import com.sergio.store.domain.model.Product

data class CartItem(
    val product: Product,
    val quantity: Int = 1
) {
    val totalPrice: Double
        get() = product.price * quantity
}
