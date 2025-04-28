package com.example.socialmediavideodownloaderapps.domain.usecase
import com.example.socialmediadownloader.domain.model.Quality
import com.example.socialmediavideodownloaderapps.domain.model.VideoMainInfoModel
import com.example.socialmediavideodownloaderapps.domain.model.VideoQuality
import com.example.socialmediavideodownloaderapps.domain.repository.MediaDownloaderRepository
import javax.inject.Inject

class DownloadVideoUseCase @Inject constructor(
    private val mediaDownloaderRepository: MediaDownloaderRepository
) {

    suspend operator fun invoke(
        videoMainInfoModel: VideoMainInfoModel,
        qualities: List<VideoQuality>
    ){
        val safeTitle = sanitizeFileName(videoMainInfoModel.title?: "Video")
        qualities.forEach { quality ->
            mediaDownloaderRepository.downloadMedia(
                url = quality.qualityUrl,
                fileName = safeTitle,
                extension = "mp4" // or whatever extension you expect
            )
        }
    }

}