package com.sergio.store.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergio.store.domain.model.Product
import com.sergio.store.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel(private val productController: ProductRepository = ProductRepository()) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadProducts() {
        viewModelScope.launch {
            val result = productController.fetchProducts()

            if (result.isSuccess) {
                _products.value = result.getOrThrow()
            } else {
                _error.value = result.exceptionOrNull()?.localizedMessage
            }
        }
    }
}
