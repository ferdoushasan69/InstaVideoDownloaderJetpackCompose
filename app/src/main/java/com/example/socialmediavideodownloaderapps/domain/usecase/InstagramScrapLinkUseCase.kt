package com.example.socialmediavideodownloaderapps.domain.usecase

import com.example.socialmediavideodownloaderapps.core.Resource
import com.example.socialmediavideodownloaderapps.data.dto.toDomainVideoMainInfoModel
import com.example.socialmediavideodownloaderapps.data.repository.InstagramMediaScraperRepositoryImpl
import com.example.socialmediavideodownloaderapps.domain.model.VideoMainInfoModel
import com.example.socialmediavideodownloaderapps.domain.repository.InstagramMediaScraperRepository
import javax.inject.Inject

class InstagramScrapLinkUseCase @Inject constructor(
    private val repository: InstagramMediaScraperRepository
) {

    suspend operator fun invoke(videoLink : String) : Resource<VideoMainInfoModel>{
        return when(val response = repository.scrapingLink(videoLink)){
            is Resource.Error<*> -> {
                Resource.Error(error = response.error)
            }
            is Resource.Idle<*> -> {
                Resource.Idle()

            }
            is Resource.Loading<*> -> {
                Resource.Loading()

            }
            is Resource.Success<*> -> {
                response.data?.let {
                    Resource.Success(data = it.toDomainVideoMainInfoModel())
                }?: run {
                    Resource.Error("")
                }
            }
        }
    }
}