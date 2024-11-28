package com.sergio.nytimes.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.sergio.nytimes.domain.model.NewsDetail
import com.sergio.nytimes.presentation.NewsDetailViewModel
import com.sergio.nytimes.views.utils.GoBackButton
import com.sergio.nytimes.views.utils.LoadingScreen

@Composable
fun NewsDetailScreen(
    newsId: String?,
    navController: NavController,
    newsDetailViewModel: NewsDetailViewModel = viewModel()
) {
    LaunchedEffect(newsId) {
        if (newsId != null) {
            newsDetailViewModel.fetchNewsDetail(newsId)
        }
    }

    val newsDetail by newsDetailViewModel.newsDetail.collectAsState()

    if (newsDetail == null) {
        LoadingScreen()
    } else {
        NewsDetailContent(newsDetail = newsDetail!!, navController)
    }
}

@Composable
fun NewsDetailContent(newsDetail: NewsDetail, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        GoBackButton(navController)

        Spacer(modifier = Modifier.height(16.dp))

        if (!newsDetail.image_url.isNullOrBlank()) {
            Image(
                painter = rememberImagePainter(newsDetail.image_url),
                contentDescription = "News Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = newsDetail.title ?: "No Title Available",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = newsDetail.description ?: "No Description Available",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        if (!newsDetail.published_at.isNullOrBlank()) {
            Text(
                text = "Published At: ${newsDetail.published_at}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        if (!newsDetail.categories.isNullOrEmpty()) {
            CategoriesRow(categories = newsDetail.categories)
        }

        if (!newsDetail.url.isNullOrBlank()) {
            Text(
                text = "Read more at ${newsDetail.source ?: "Source Unavailable"}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun CategoriesRow(categories: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categories.forEach { category ->
            Surface(
                modifier = Modifier.padding(4.dp),
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.secondaryContainer
            ) {
                Text(
                    text = category,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}