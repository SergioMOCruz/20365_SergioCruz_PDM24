import com.sergio.nytimes.domain.model.NewsDetail

data class NewsDetailDto(
    val uuid: String,
    val title: String,
    val description: String?,
    val keywords: String?,
    val snippet: String?,
    val url: String,
    val image_url: String?,
    val language: String?,
    val published_at: String?,
    val source: String?,
    val categories: List<String>?,
    val relevance_score: Double?,
    val locale: String?
) {
    fun toNewsDetail(): NewsDetail {
        return NewsDetail(
            uuid = uuid,
            title = title,
            description = description,
            keywords = keywords,
            snippet = snippet,
            url = url,
            image_url = image_url,
            language = language,
            published_at = published_at,
            source = source,
            categories = categories,
            relevance_score = relevance_score,
            locale = locale
        )
    }
}
