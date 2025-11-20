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
        title = "La Gran Aventura",
        summary = "Una épica travesía por mundos increíbles en un mundo mágico.",
        genres = listOf("Aventura", "Acción"),
        platforms = listOf("Netflix")
    ),
    ContentItem(
        id = "c2",
        title = "Comedia del Siglo",
        summary = "Situaciones ridículas que te harán reír durante horas.",
        genres = listOf("Comedia"),
        platforms = listOf("HBO", "Prime Video")
    )
)
