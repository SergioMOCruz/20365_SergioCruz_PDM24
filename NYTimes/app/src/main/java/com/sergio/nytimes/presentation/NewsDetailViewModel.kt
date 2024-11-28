package com.sergio.nytimes.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergio.nytimes.data.remote.api.RetrofitInstance
import com.sergio.nytimes.data.repository.NewsDetailRepositoryImpl
import com.sergio.nytimes.domain.model.NewsDetail
import com.sergio.nytimes.domain.use_case.GetNewsDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsDetailViewModel : ViewModel() {
    private val _api = RetrofitInstance.api
    private val _repository = NewsDetailRepositoryImpl(_api)
    private val _getNewsDetailCase = GetNewsDetailUseCase(_repository)

    private val _newsDetail = MutableStateFlow<NewsDetail?>(null)
    val newsDetail: StateFlow<NewsDetail?> get() = _newsDetail

    private var _hasFetchedNewsDetail = false

    fun fetchNewsDetail(newsId: String) {
        if (_hasFetchedNewsDetail) return
        _hasFetchedNewsDetail = true

        viewModelScope.launch {
            try {
                _newsDetail.value = _getNewsDetailCase(newsId)
            } catch (e: Exception) {
                Log.e("NewsListViewModel", "Error fetching news", e)
                _newsDetail.value = null
            }
        }
    }
}
