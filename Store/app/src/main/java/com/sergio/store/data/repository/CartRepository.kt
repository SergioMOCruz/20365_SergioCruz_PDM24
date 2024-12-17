package com.sergio.store.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.sergio.store.data.model.CartDto
import com.sergio.store.data.model.toDomainCartItems
import com.sergio.store.data.model.toDto
import com.sergio.store.model.CartItem
import kotlinx.coroutines.tasks.await

class CartRepository {
    private val db = FirebaseFirestore.getInstance()

    suspend fun saveCartToFirestore(ownerId: String, items: List<CartItem>): Result<String> = runCatching {
        val cartDto = items.toDto(ownerId)

        val docRef = db.collection("carts").add(cartDto).await()

        docRef.id
    }

    suspend fun loadCartFromFirestore(cartId: String): Result<List<CartItem>> = runCatching {
        val doc = db.collection("carts").document(cartId).get().await()

        if (!doc.exists()) throw Exception("Cart not found")

        val cartDto = doc.toObject(CartDto::class.java) ?: throw Exception("Cart data invalid")

        cartDto.toDomainCartItems()
    }
}
