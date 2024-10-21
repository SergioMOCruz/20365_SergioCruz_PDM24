package com.sergio.lazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import com.sergio.lazycolumn.ui.theme.LazyColumnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyColumnTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    /*LazyColumnFunc(
                        modifier = Modifier.padding(innerPadding)
                    )*/
                    
                    val myFirstList = mutableListOf("Hello", "You can ", "edit, ", "run, ", "and share this code")

                    LazyRowFunc(
                        myFirstList
                    )

                }
            }
        }
    }
}

@Composable
fun LazyColumnFunc(modifier: Modifier = Modifier) {
    LazyColumn {
        items(100) { index ->
            Text(
                text = index.toString()
            )
        }
    }
}

@Composable
fun LazyRowFunc(itemsList: List<String>) {
    LazyRow() {
        items(itemsList) { item ->
            Text(
                text = item
            )
        }
    }
}