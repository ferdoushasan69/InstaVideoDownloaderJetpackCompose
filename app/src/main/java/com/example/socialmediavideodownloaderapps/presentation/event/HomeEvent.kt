package com.example.socialmediavideodownloaderapps.presentation.event

import com.example.socialmediavideodownloaderapps.domain.model.VideoQuality

sealed interface HomeEvent {
    data class EnterUrl(val text : String) : HomeEvent
    data object ParseUrl : HomeEvent
    data class ChooseQuality(val videoQuality: VideoQuality) : HomeEvent
    data object DownloadVideo : HomeEvent
    data object CloseBottomSheet : HomeEvent
}