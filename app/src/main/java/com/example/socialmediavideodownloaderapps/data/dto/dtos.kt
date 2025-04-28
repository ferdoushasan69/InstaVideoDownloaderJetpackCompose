package com.example.socialmediavideodownloaderapps.data.dto

import com.example.socialmediadownloader.domain.model.Video
import com.example.socialmediavideodownloaderapps.domain.model.VideoMainInfoModel
import com.example.socialmediavideodownloaderapps.domain.model.VideoQuality

fun Video.toDomainVideoMainInfoModel() = VideoMainInfoModel(
    title = this.title,
    thumbnail = this.quality.getOrNull(0)?.qualityUrl?:"",
    qualities = this.quality.map { quality->
        VideoQuality(
            qualityUrl = quality.qualityUrl,
            qualityName = quality.qualityName,
            qualityDesc = quality.qualitySize
        )
    }
)