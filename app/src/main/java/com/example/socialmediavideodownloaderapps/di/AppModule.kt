package com.example.socialmediavideodownloaderapps.di

import android.content.Context
import com.example.socialmediadownloader.EasyDownloader
import com.example.socialmediavideodownloaderapps.data.repository.InstagramMediaScraperRepositoryImpl
import com.example.socialmediavideodownloaderapps.data.repository.MediaDownloaderRepositoryImpl
import com.example.socialmediavideodownloaderapps.domain.repository.InstagramMediaScraperRepository
import com.example.socialmediavideodownloaderapps.domain.repository.MediaDownloaderRepository
import com.example.socialmediavideodownloaderapps.domain.usecase.DownloadVideoUseCase
import com.example.socialmediavideodownloaderapps.domain.usecase.InstagramScrapLinkUseCase
import com.example.socialmediavideodownloaderapps.presentation.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideMediaDownloaderRepo(@ApplicationContext context: Context): MediaDownloaderRepository =
        MediaDownloaderRepositoryImpl(context = context)


    @Provides
    @Singleton
    fun provideEasyDownloader(): EasyDownloader = EasyDownloader()

    @Provides
    @Singleton
    fun provideMediaScraperRepo(
        @ApplicationContext context: Context,
        easyDownloader: EasyDownloader
    ): InstagramMediaScraperRepository =
        InstagramMediaScraperRepositoryImpl(
            easyDownloader = easyDownloader
        )

    @Provides
    @Singleton
    fun provideDownloadVideoUseCase(mediaDownloaderRepository: MediaDownloaderRepository): DownloadVideoUseCase =
        DownloadVideoUseCase(mediaDownloaderRepository)


    @Provides
    @Singleton
    fun provideInstagramScrapLinkUseCase(instagramMediaScraperRepository: InstagramMediaScraperRepository): InstagramScrapLinkUseCase =
        InstagramScrapLinkUseCase(instagramMediaScraperRepository)


}