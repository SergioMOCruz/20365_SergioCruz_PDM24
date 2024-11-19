package com.sergio.nytimes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sergio.nytimes.presentation.NewsListScreen
import com.sergio.nytimes.presentation.NewsListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val newsListViewModel: NewsListViewModel = viewModel()
    NewsListScreen(newsListViewModel)
}
