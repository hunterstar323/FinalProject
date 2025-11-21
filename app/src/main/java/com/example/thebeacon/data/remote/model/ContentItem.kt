package com.example.thebeacon.data.remote.model

data class ContentItem(
    val id: Int,
    val title: String,
    val summary: String,
    val genres: List<String>,
    val platforms: List<String>
)
