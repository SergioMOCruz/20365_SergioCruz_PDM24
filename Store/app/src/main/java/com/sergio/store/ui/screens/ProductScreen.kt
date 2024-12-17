package com.sergio.store.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import coil.compose.rememberImagePainter
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sergio.store.domain.model.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    products: List<Product>,
    error: String?,
    onAddToCart: (Product) -> Unit,
    onOpenCart: () -> Unit,
    onLoadCartFromClipboard: () -> Unit,
    onLogout: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Products") },
                actions = {
                    IconButton(onClick = onOpenCart) {
                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Cart"
                        )
                    }
                    IconButton(onClick = onLoadCartFromClipboard) {
                        Icon(
                            imageVector = Icons.Filled.CloudDownload,
                            contentDescription = "Load Shared Cart from Clipboard"
                        )
                    }
                    IconButton(onClick = onLogout) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "Logout"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            if (error != null) {
                Text(text = "Error: $error", color = MaterialTheme.colorScheme.error)
            }
            LazyColumn {
                items(products) { product ->
                    Card(
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    ) {
                        Row(modifier = Modifier.padding(16.dp)) {
                            Image(
                                painter = rememberAsyncImagePainter(product.imageUrl),
                                contentDescription = product.name,
                                modifier = Modifier.size(80.dp)
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(text = product.name, style = MaterialTheme.typography.titleMedium)
                                Text(text = "€ ${product.price}", style = MaterialTheme.typography.bodyMedium)
                            }

                            Button(
                                onClick = { onAddToCart(product) },
                                modifier = Modifier.align(Alignment.CenterVertically)
                            ) {
                                Text("Add to Cart")
                            }
                        }
                    }
                }
            }
        }
    }
}
