package com.example.socialmediavideodownloaderapps.domain.model

data class InProgressUiModel(
    val id: Long,
    val title: String,
    val mediaUrl: String,
    val thumbnail: String,
    val bytesDownloaded: Long,
    val totalSize: Long,
    val isDownloaded: Boolean,
    val isCancelled: Boolean,
    val isFailed: Boolean,
    val isInPause: Boolean,
)