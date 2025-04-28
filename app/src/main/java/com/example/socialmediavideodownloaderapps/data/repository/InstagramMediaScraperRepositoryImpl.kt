package com.example.socialmediavideodownloaderapps.data.repository

import com.example.socialmediadownloader.EasyDownloader
import com.example.socialmediadownloader.data.response.NetworkResponse
import com.example.socialmediadownloader.domain.model.Video
import com.example.socialmediavideodownloaderapps.core.Resource
import com.example.socialmediavideodownloaderapps.domain.repository.InstagramMediaScraperRepository
import javax.inject.Inject

class InstagramMediaScraperRepositoryImpl @Inject constructor (
    private val easyDownloader: EasyDownloader
): InstagramMediaScraperRepository {
    override suspend fun scrapingLink(url: String): Resource<Video> {
        return when(val response = easyDownloader.downloadVideo(url)){
            is NetworkResponse.Failure -> {
                Resource.Error(response.error)
            }
            is NetworkResponse.Idle -> {
                Resource.Idle()
            }
            is NetworkResponse.Loading ->{
                Resource.Loading()
            }
            is NetworkResponse.Success -> {
               response.data?.let {
                   Resource.Success(data = it)
               }?:run {
                   Resource.Error("No data found! ")
               }
            }
        }
    }
}