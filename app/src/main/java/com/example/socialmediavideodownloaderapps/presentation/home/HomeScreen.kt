package com.example.socialmediavideodownloaderapps.presentation.home

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.socialmediavideodownloaderapps.R
import com.example.socialmediavideodownloaderapps.core.Resource
import com.example.socialmediavideodownloaderapps.presentation.event.HomeEvent
import com.example.socialmediavideodownloaderapps.presentation.home.component.BottomSheetButton
import com.example.socialmediavideodownloaderapps.presentation.home.component.CustomTextField
import com.example.socialmediavideodownloaderapps.presentation.home.component.QualityItem
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import java.nio.file.WatchEvent

@SuppressLint("ResourceAsColor")
@OptIn(
    ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class,
    ExperimentalGlideComposeApi::class
)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val state by viewModel.state.collectAsStateWithLifecycle()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val permissionState =
            rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

        LaunchedEffect(permissionState.status.isGranted.not()) {
            if (permissionState.status.isGranted.not()) {
                permissionState.launchPermissionRequest()
            }
        }
    }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(10.dp)
        ) {
            if (state.parseState is Resource.Loading || state.parseState is Resource.Success) {
                ModalBottomSheet(onDismissRequest = {
                    viewModel.onEvent(HomeEvent.CloseBottomSheet)
                }) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        if (state.parseState is Resource.Loading) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator(color = Color(R.color.mainColor))
                                Spacer(Modifier.height(8.dp))
                                Text(text = "Parsing Url", fontSize = 14.sp)
                                Spacer(Modifier.height(20.dp))
                            }

                        } else {
                            val videoModel = state.parseState.data
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Row (
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(90.dp)
                                            .background(
                                                color = Color.Transparent,
                                                shape = RoundedCornerShape(14.dp)
                                            )
                                            .clip(RoundedCornerShape(10.dp)),
                                        verticalAlignment = Alignment.CenterVertically,

                                    ) {

                                            GlideImage(
                                                model = videoModel?.thumbnail,
                                                contentDescription = null,
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier.width(200.dp)
                                                    .fillMaxHeight()

                                            )
                                            Spacer(Modifier.width(8.dp))
                                            Text(
                                                videoModel?.title ?: "Unknown",
                                                fontSize = 14.sp,
                                                color = Color.Blue,

                                            )
                                    }
                                }
                                LazyRow {
                                    items(videoModel?.qualities ?: emptyList()) { quality ->
                                        QualityItem(
                                            title = quality.qualityName,
                                            desc = quality.qualityDesc,
                                            isSelected = state.selectQuality == quality
                                        ) {
                                            viewModel.onEvent(HomeEvent.ChooseQuality(quality))
                                        }
                                    }
                                }
                                Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                                    BottomSheetButton(
                                        text = "Cancel",
                                        modifier = Modifier
                                            .weight(1f)
                                        ,
                                    ) {
                                        viewModel.onEvent(HomeEvent.CloseBottomSheet)
                                    }
                                    Spacer(Modifier.width(9.dp))
                                    BottomSheetButton(
                                        text = "Download",
                                        modifier = Modifier
                                            .weight(1f)
                                        ,
                                    ) {
                                        Toast.makeText(
                                            context,
                                            "Download Started...",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        viewModel.onEvent(HomeEvent.DownloadVideo)
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(30.dp))
            CustomTextField(
                onValueChange = {
                    viewModel.onEvent(HomeEvent.EnterUrl(it))
                },
                url = state.url
            )
            Spacer(Modifier.height(10.dp))
            BottomSheetButton(
                text = "Start Parsing",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                viewModel.onEvent(HomeEvent.ParseUrl)
            }

        }
    }

}