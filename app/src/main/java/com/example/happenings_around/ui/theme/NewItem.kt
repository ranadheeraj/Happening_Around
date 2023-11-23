package com.example.happenings_around.ui.theme

data class NewsItem(
    val title: String?,
    val link: String?,
    val photoUrl: String?,
    val publishedDatetimeUtc: String?,
    val sourceUrl: String?,
    val sourceLogoUrl: String?,
    val sourceFaviconUrl: String?
)

data class NewsResponse(
    val newsItems: List<NewsItem>
)