package com.sergio.store.ui.navigation

import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sergio.store.ui.screens.CartScreen
import com.sergio.store.ui.screens.LoginScreen
import com.sergio.store.ui.screens.ProductScreen
import com.sergio.store.ui.screens.RegisterScreen
import com.sergio.store.ui.viewmodel.AuthViewModel
import com.sergio.store.ui.viewmodel.CartViewModel
import com.sergio.store.ui.viewmodel.ProductViewModel

@Composable
fun AppNavHost(
    authViewModel: AuthViewModel,
    productViewModel: ProductViewModel,
    cartViewModel: CartViewModel
) {
    val context = LocalContext.current
    val navController = rememberNavController()

    val isLoggedIn = authViewModel.isLoggedIn()
    val startDestination = if (isLoggedIn) "products" else "login"

    NavHost(navController = navController, startDestination = startDestination) {

        composable("login") {
            val loginState by authViewModel.loginState.collectAsState()

            val loginError = if (loginState?.isFailure == true) {
                loginState!!.exceptionOrNull()?.localizedMessage
            } else null

            LoginScreen(
                onLoginClick = { email, password ->
                    authViewModel.login(email, password)
                },
                onGoToRegister = {
                    navController.navigate("register")
                },
                loginError = loginError
            )

            if (loginState?.isSuccess == true) {
                navController.navigate("products") {
                    popUpTo("login") { inclusive = true }
                }
                authViewModel.resetLoginState()
            }
        }

        composable("register") {
            val registerState by authViewModel.registerState.collectAsState()
            val registerError = if (registerState?.isFailure == true) {
                registerState!!.exceptionOrNull()?.localizedMessage
            } else null

            RegisterScreen(
                onRegisterClick = { email, password ->
                    authViewModel.register(email, password)
                },
                onGoToLogin = {
                    navController.navigate("login")
                },
                registerError = registerError
            )

            if (registerState?.isSuccess == true) {
                navController.navigate("products") {
                    popUpTo("register") { inclusive = true }
                }
                authViewModel.resetRegisterState()
            }
        }

        composable("products") {
            val products by productViewModel.products.collectAsState()
            val error by productViewModel.error.collectAsState()
            val loadSharedCartResult by cartViewModel.loadSharedCartResult.collectAsState()

            productViewModel.loadProducts()

            ProductScreen(
                products = products,
                error = error,
                onAddToCart = { product ->
                    cartViewModel.addToCart(product)
                    Toast.makeText(context, "${product.name} added to cart!", Toast.LENGTH_SHORT).show()
                },
                onOpenCart = {
                    navController.navigate("cart")
                },
                onLoadCartFromClipboard = {
                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clipData = clipboard.primaryClip
                    if (clipData != null && clipData.itemCount > 0) {
                        val cartId = clipData.getItemAt(0).coerceToText(context).toString().trim()
                        if (cartId.isNotBlank()) {
                            cartViewModel.loadSharedCart(cartId)
                        } else {
                            Toast.makeText(context, "Clipboard empty or invalid", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "No cart ID in clipboard", Toast.LENGTH_SHORT).show()
                    }
                },
                onLogout = {
                    authViewModel.logout()
                    navController.navigate("login") {
                        popUpTo("products") { inclusive = true }
                    }
                }
            )

            LaunchedEffect(key1 = loadSharedCartResult) {
                loadSharedCartResult?.let { result ->
                    if (result.isSuccess) {
                        navController.navigate("cart")
                        cartViewModel.resetLoadSharedCartResult()
                    } else if (result.isFailure) {
                        Toast.makeText(context, "No cart found or error loading cart", Toast.LENGTH_SHORT).show()
                        cartViewModel.resetLoadSharedCartResult()
                    }
                }
            }
        }

        composable("cart") {
            val cartItems by cartViewModel.cartItems.collectAsState()
            val total = cartViewModel.calculateTotal()
            val shareResult by cartViewModel.sharedCartResult.collectAsState()

            CartScreen(
                cartItems = cartItems,
                total = total,
                onPay = {
                    Toast.makeText(context, "Paid", Toast.LENGTH_SHORT).show()
                    cartViewModel.clearCart()
                    navController.popBackStack()
                },
                onBack = {
                    navController.popBackStack()
                },
                onShare = {
                    val ownerId = authViewModel.getCurrentUserId()
                    if (ownerId != null) {
                        cartViewModel.saveCart(ownerId)
                    }
                },
                shareResult = shareResult,
                onRemoveItem = { product ->
                    cartViewModel.removeFromCart(product)
                }
            )
        }
    }
}
