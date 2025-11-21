package com.example.thebeacon.data.remote.api

import com.example.thebeacon.data.remote.model.ContentItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ContentService {

    @GET("content/{id}")
    suspend fun getContentDetail(
        @Path("id") id: String
    ): ContentItem
}