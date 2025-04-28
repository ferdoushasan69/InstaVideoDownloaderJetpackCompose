package com.example.socialmediavideodownloaderapps.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmediavideodownloaderapps.core.Resource
import com.example.socialmediavideodownloaderapps.domain.usecase.DownloadVideoUseCase
import com.example.socialmediavideodownloaderapps.domain.usecase.InstagramScrapLinkUseCase
import com.example.socialmediavideodownloaderapps.presentation.event.HomeEvent
import com.example.socialmediavideodownloaderapps.presentation.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val downloadVideoUseCase: DownloadVideoUseCase,
    private val instagramScrapLinkUseCase: InstagramScrapLinkUseCase
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()


    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.ChooseQuality -> {
                _state.update { it.copy(selectQuality = event.videoQuality) }
            }
            is HomeEvent.CloseBottomSheet -> {
                _state.update {
                    it.copy(
                        parseState = Resource.Idle(),
                        selectQuality = null
                    )
                }
            }
            is HomeEvent.DownloadVideo -> {
                startDownload()

            }
            is HomeEvent.EnterUrl -> {
                _state.update {
                    it.copy(url = event.text)
                }
            }
            is HomeEvent.ParseUrl -> {
                startParsingUrl()
            }
        }

    }

    private fun startParsingUrl() {
        viewModelScope.launch {
            if (state.value.url?.isNotBlank() == true){
                _state.update { it.copy(parseState = Resource.Loading()) }
                val response = instagramScrapLinkUseCase(state.value.url)
                _state.update {
                    it.copy(parseState = response)
                }
            }
        }
    }

    private fun startDownload() {
        if (state.value.selectQuality != null){
            val videoModel = state.value.parseState.data
            if (videoModel != null){
                viewModelScope.launch {
                    downloadVideoUseCase(
                        videoMainInfoModel = videoModel,
                        qualities = listOf(state.value.selectQuality!!)
                    )
                    _state.update { it.copy(parseState = Resource.Idle()) }
                }
            }
        }
    }
}