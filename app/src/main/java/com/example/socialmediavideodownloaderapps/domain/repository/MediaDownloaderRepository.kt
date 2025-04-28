package com.example.socialmediavideodownloaderapps.domain.repository

import com.example.socialmediavideodownloaderapps.data.model.DownloadVideoInfo

interface MediaDownloaderRepository {

    fun downloadMedia(url : String,fileName : String,extension: String) : Long

//    suspend fun getDownloadInfoById(id : Long) : DownloadVideoInfo?
}