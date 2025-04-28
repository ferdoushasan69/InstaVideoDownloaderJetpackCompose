package com.example.socialmediavideodownloaderapps.domain.model

data class VideoMainInfoModel(
    val title: String,
    val thumbnail: String,
    val qualities: List<VideoQuality>
)