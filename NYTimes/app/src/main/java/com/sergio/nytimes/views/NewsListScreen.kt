package com.sergio.nytimes.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.sergio.nytimes.domain.model.News
import com.sergio.nytimes.domain.model.NewsDetail
import com.sergio.nytimes.views.utils.LoadingScreen

@Composable
fun NewsListScreen(
    newsListViewModel: NewsListViewModel = viewModel(),
    navController: NavController
) {
    val newsList by newsListViewModel.news.collectAsState()

    LaunchedEffect(Unit) {
        newsListViewModel.fetchNews()
    }

    if (newsList.isEmpty()) {
        LoadingScreen()
    } else {
        NewsListContent(newsList = newsList, navController = navController)
    }
}

@Composable
fun NewsListContent(newsList: List<News>, navController: NavController) {
    val allNewsDetails = newsList.flatMap { it.data }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(allNewsDetails) { newsDetail ->
            NewsItemCard(newsDetail = newsDetail, navController = navController)
        }
    }
}

@Composable
fun NewsItemCard(newsDetail: NewsDetail, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("news_detail/${newsDetail.uuid}")
            },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            if (!newsDetail.image_url.isNullOrBlank()) {
                NewsItemImage(imageUrl = newsDetail.image_url)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = newsDetail.title ?: "No Title Available",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = newsDetail.description ?: "No Description Available",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Published on: ${newsDetail.published_at ?: "Unknown Date"}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
fun NewsItemImage(imageUrl: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Image(
            painter = rememberImagePainter(imageUrl),
            contentDescription = "News Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
