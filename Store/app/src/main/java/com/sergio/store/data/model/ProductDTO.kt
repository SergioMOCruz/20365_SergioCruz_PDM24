package com.sergio.store.data.model

import com.sergio.store.domain.model.Product

data class ProductDto(
    val name: String = "",
    val price: Double = 0.0,
    val description: String = "",
    val imageUrl: String = ""
)

fun ProductDto.toDomainProduct(id: String): Product {
    return Product(
        id = id,
        name = this.name,
        price = this.price,
        description = this.description,
        imageUrl = this.imageUrl
    )
}
