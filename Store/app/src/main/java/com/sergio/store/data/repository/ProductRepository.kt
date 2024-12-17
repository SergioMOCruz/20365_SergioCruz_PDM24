package com.sergio.store.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.sergio.store.data.model.ProductDto
import com.sergio.store.data.model.toDomainProduct
import com.sergio.store.domain.model.Product
import kotlinx.coroutines.tasks.await

class ProductRepository {
    private val db = FirebaseFirestore.getInstance()

    suspend fun fetchProducts(): Result<List<Product>> = runCatching {
        val result = db.collection("products").get().await()

        result.documents.map { doc ->
            val productDto = doc.toObject(ProductDto::class.java)
                ?: throw Exception("Invalid product data in document ${doc.id}")
            productDto.toDomainProduct(doc.id)
        }
    }
}
