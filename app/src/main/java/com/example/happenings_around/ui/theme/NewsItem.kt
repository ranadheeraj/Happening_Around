package com.example.happenings_around.ui.theme
data class NewsItem(
    val title: String,
    val body: String,
    val date: String,
    val url: String,
    val source: String,
    val image: String
)

data class NewsResponse(
    val page: Int,
    val news: List<NewsItem>
)

