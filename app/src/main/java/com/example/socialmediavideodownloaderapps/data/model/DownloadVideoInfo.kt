package com.example.socialmediavideodownloaderapps.data.model

data class DownloadVideoInfo(
    var id: Long = -1,
    var url: String = "",
    var bytesDownloaded: Long = 0,
    var totalSize: Long = 0,
    var isDownloaded: Boolean = false,
    var isCancelled: Boolean = false,
    var isFailed: Boolean = false,
    var isInPause: Boolean = false
)