package com.example.socialmediavideodownloaderapps.presentation.state

import com.example.socialmediavideodownloaderapps.core.Resource
import com.example.socialmediavideodownloaderapps.domain.model.VideoMainInfoModel
import com.example.socialmediavideodownloaderapps.domain.model.VideoQuality

data class HomeState(
    val parseState : Resource<VideoMainInfoModel> = Resource.Idle(),
    val selectQuality : VideoQuality? =null,
    val url : String =""
)