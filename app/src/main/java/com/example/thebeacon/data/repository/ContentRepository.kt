package com.example.thebeacon.data.repository

import com.example.thebeacon.data.remote.api.ContentService
import com.example.thebeacon.data.remote.model.ContentItem

class ContentRepository(
    private val api: ContentService
) {
    suspend fun getContentDetail(id: String): ContentItem {
        return api.getContentDetail(id)
    }
}
