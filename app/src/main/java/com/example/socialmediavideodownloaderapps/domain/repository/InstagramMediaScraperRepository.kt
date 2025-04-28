package com.example.socialmediavideodownloaderapps.domain.repository

import com.example.socialmediadownloader.domain.model.Video
import com.example.socialmediavideodownloaderapps.core.Resource

interface InstagramMediaScraperRepository {

    suspend fun scrapingLink(url : String) : Resource<Video>
}