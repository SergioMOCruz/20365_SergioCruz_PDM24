package com.sergio.store.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergio.store.data.repository.CartRepository
import com.sergio.store.domain.model.Product
import com.sergio.store.model.CartItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(private val cartController: CartRepository = CartRepository()) : ViewModel() {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    private val _loadSharedCartResult = MutableStateFlow<Result<Unit>?>(null)
    val loadSharedCartResult: StateFlow<Result<Unit>?> = _loadSharedCartResult

    private val _sharedCartResult = MutableStateFlow<Result<String>?>(null)
    val sharedCartResult: StateFlow<Result<String>?> = _sharedCartResult

    fun addToCart(product: Product) {
        val currentList = _cartItems.value.toMutableList()
        val existingIndex = currentList.indexOfFirst { it.product.id == product.id }
        if (existingIndex != -1) {
            val existingItem = currentList[existingIndex]
            currentList[existingIndex] = existingItem.copy(quantity = existingItem.quantity + 1)
        } else {
            currentList.add(CartItem(product, 1))
        }
        _cartItems.value = currentList
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }

    fun calculateTotal(): Double {
        return _cartItems.value.sumOf { it.totalPrice }
    }

    fun saveCart(ownerId: String) {
        viewModelScope.launch {
            val result = cartController.saveCartToFirestore(ownerId, _cartItems.value)
            _sharedCartResult.value = result
        }
    }

    fun loadSharedCart(cartId: String) {
        viewModelScope.launch {
            val result = cartController.loadCartFromFirestore(cartId)

            if (result.isSuccess) {
                _cartItems.value = result.getOrThrow()

                if (_cartItems.value.isEmpty()) {
                    _loadSharedCartResult.value = Result.failure(Exception("No cart found for ID: $cartId"))
                } else {
                    _loadSharedCartResult.value = Result.success(Unit)
                }

            } else {
                _loadSharedCartResult.value = Result.failure(result.exceptionOrNull() ?: Exception("Error loading cart"))
            }
        }
    }

    fun resetLoadSharedCartResult() {
        _loadSharedCartResult.value = null
    }

    fun removeFromCart(product: Product) {
        val currentList = _cartItems.value.toMutableList()
        val index = currentList.indexOfFirst { it.product.id == product.id }

        if (index != -1) {
            val existingItem = currentList[index]

            if (existingItem.quantity > 1) {
                currentList[index] = existingItem.copy(quantity = existingItem.quantity - 1)
            } else {
                currentList.removeAt(index)
            }

            _cartItems.value = currentList
        }
    }

}
