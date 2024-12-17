package com.sergio.store.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sergio.store.domain.model.Product
import com.sergio.store.model.CartItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    cartItems: List<CartItem>,
    total: Double,
    onPay: () -> Unit,
    onBack: () -> Unit,
    onShare: () -> Unit,
    shareResult: Result<String>?,
    onRemoveItem: (product: Product) -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cart") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(cartItems) { cartItem ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = rememberAsyncImagePainter(cartItem.product.imageUrl),
                                contentDescription = cartItem.product.name,
                                modifier = Modifier.size(60.dp)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Column {
                                Text(text = cartItem.product.name, style = MaterialTheme.typography.titleMedium)
                                Text(text = "Unit: € ${cartItem.product.price}", style = MaterialTheme.typography.bodySmall)
                                Text(text = "Qty: ${cartItem.quantity}", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "€ ${cartItem.totalPrice}", style = MaterialTheme.typography.bodyMedium)

                            Spacer(modifier = Modifier.width(8.dp))

                            IconButton(onClick = { onRemoveItem(cartItem.product) }) {
                                Icon(Icons.Filled.Close, contentDescription = "Remove")
                            }
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Total:", style = MaterialTheme.typography.titleMedium)

                Text(text = "€ $total", style = MaterialTheme.typography.titleMedium)
            }

            if (shareResult != null && shareResult.isSuccess) {
                val cartId = shareResult.getOrThrow()
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("CartID", cartId)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(context, "ID copied to clipboard", Toast.LENGTH_SHORT).show()

            } else if (shareResult != null && shareResult.isFailure) {
                Text(
                    text = "Error sharing cart: ${shareResult.exceptionOrNull()?.localizedMessage}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Row(modifier = Modifier.padding(16.dp)) {
                Button(onClick = onPay, modifier = Modifier.weight(1f)) {
                    Text("Pay")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = onShare, modifier = Modifier.weight(1f)) {
                    Text("Share Cart")
                }
            }
        }
    }
}
