package com.sergio.nytimes.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergio.nytimes.data.remote.api.RetrofitInstance
import com.sergio.nytimes.data.repository.NewsRepositoryImpl
import com.sergio.nytimes.domain.model.News
import com.sergio.nytimes.domain.use_case.GetNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsListViewModel : ViewModel() {

    private val _api = RetrofitInstance.api
    private val _repository = NewsRepositoryImpl(_api)
    private val _getNewsUseCase = GetNewsUseCase(_repository)

    private val _news = MutableStateFlow<List<News>>(emptyList())
    val news: StateFlow<List<News>> = _news

    private var _hasFetchedNews = false

    fun fetchNews() {
        if (_hasFetchedNews) return
        _hasFetchedNews = true

        viewModelScope.launch {
            try {
                _news.value = _getNewsUseCase()
            } catch (e: Exception) {
                Log.e("NewsListViewModel", "Error fetching news", e)
                _news.value = emptyList()
            }
        }
    }
}
