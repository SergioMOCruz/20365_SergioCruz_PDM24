package com.sergio.nytimes.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sergio.nytimes.domain.model.News
import com.sergio.nytimes.domain.model.NewsDetail

@Composable
fun NewsListScreen(newsListViewModel: NewsListViewModel = viewModel()) {
    val newsList by newsListViewModel.news.collectAsState()

    LaunchedEffect(Unit) {
        newsListViewModel.fetchNews()
    }

    if (newsList.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "No news available.")
        }
    } else {
        NewsList(newsList)
    }
}

@Composable
fun NewsList(newsList: List<News>) {
    val allNewsDetails = newsList.flatMap { it.results }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(allNewsDetails) { newsDetail ->
            NewsItem(newsDetail)
        }
    }
}

@Composable
fun NewsItem(newsDetail: NewsDetail) {
    Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
        Text(text = newsDetail.title)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = newsDetail.abstract)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = newsDetail.created_date)
    }
}