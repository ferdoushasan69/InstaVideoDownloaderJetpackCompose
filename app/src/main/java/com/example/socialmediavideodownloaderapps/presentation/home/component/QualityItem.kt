package com.example.socialmediavideodownloaderapps.presentation.home.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.socialmediavideodownloaderapps.R
import com.example.socialmediavideodownloaderapps.ui.theme.bgColor
import com.example.socialmediavideodownloaderapps.ui.theme.selectedColor
import com.example.socialmediavideodownloaderapps.ui.theme.unSelectedColor

@SuppressLint("ResourceAsColor")
@Composable
fun QualityItem(
    title: String,
    desc: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding ( 6.dp)
            .border(
                width = 1.dp,
                color = if (isSelected) {
                    selectedColor
                } else {
                    unSelectedColor

                },
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                bgColor
            )
            .clickable {
                onClick.invoke()
            }
            .padding(horizontal = 10.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text(text = desc, fontSize = 13.sp)

    }
}