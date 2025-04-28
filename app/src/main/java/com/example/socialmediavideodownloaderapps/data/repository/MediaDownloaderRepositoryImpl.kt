package com.example.socialmediavideodownloaderapps.data.repository

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import com.example.socialmediavideodownloaderapps.data.model.DownloadVideoInfo
import com.example.socialmediavideodownloaderapps.domain.repository.MediaDownloaderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MediaDownloaderRepositoryImpl @Inject constructor(
    private val context: Context
) : MediaDownloaderRepository {

    private val downloadManager =
        context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager


    override fun downloadMedia(
        url: String,
        fileName: String,
        extension: String
    ): Long {
        val request = DownloadManager.Request(url.toUri())
            .setTitle("$fileName.$extension")
            .setMimeType("video/*")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "SocialMediaDownloader/${fileName.replace(":", "")}.$extension"
            )
        return downloadManager.enqueue(request)
    }

//    override suspend fun getDownloadInfoById(id: Long): DownloadVideoInfo? {
//        return  withContext(Dispatchers.IO) {
//            val query = DownloadManager.Query()
//                .setFilterById(id)
//            val cursor = downloadManager.query(query)
//
//            if (cursor.moveToFirst()){
//                val status = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS))
//                val downloadInfo = DownloadVideoInfo
//
//                val bytesDownload = cursor.getLong(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
//
//                val totalSize = cursor.getLong(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
//
//
//            }
//        }
//    }
}