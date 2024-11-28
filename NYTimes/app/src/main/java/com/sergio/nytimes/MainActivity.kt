package com.sergio.nytimes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sergio.nytimes.presentation.NewsListScreen
import com.sergio.nytimes.presentation.NewsListViewModel
import com.sergio.nytimes.views.NewsDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(newsListViewModel: NewsListViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "news_list") {
        composable("news_list") {
            NewsListScreen(newsListViewModel, navController)
        }
        composable("news_detail/{newsId}") { backStackEntry ->
            val newsId = backStackEntry.arguments?.getString("newsId")
            NewsDetailScreen(newsId = newsId, navController)
        }
    }
}