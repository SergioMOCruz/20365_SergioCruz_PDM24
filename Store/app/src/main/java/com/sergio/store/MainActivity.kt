package com.sergio.store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.sergio.store.ui.viewmodel.AuthViewModel
import com.sergio.store.ui.viewmodel.CartViewModel
import com.sergio.store.ui.viewmodel.ProductViewModel
import com.sergio.store.ui.navigation.AppNavHost

class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels()
    private val productViewModel: ProductViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost(
                authViewModel = authViewModel,
                productViewModel = productViewModel,
                cartViewModel = cartViewModel
            )
        }
    }
}

