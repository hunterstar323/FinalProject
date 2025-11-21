package com.example.thebeacon.ui.content

data class ContentItem(
    val id: String,
    val title: String,
    val summary: String,
    val genres: List<String>,
    val platforms: List<String>,
    val imageUrl: String? = null
)

val sampleContents = listOf(
    ContentItem(
        id = "c1",
        title = "TEST",
        summary = "AAAAA.",
        genres = listOf("Aventura", "Acción"),
        platforms = listOf("Netflix")
    ),
    ContentItem(
        id = "c2",
        title = "TEST2",
        summary = "AYYUDA.",
        genres = listOf("Comedia"),
        platforms = listOf("HBO", "Prime Video")
    )
)
