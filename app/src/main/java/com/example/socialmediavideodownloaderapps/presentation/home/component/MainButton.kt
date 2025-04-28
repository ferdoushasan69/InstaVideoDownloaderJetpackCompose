package com.example.socialmediavideodownloaderapps.presentation.home.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.socialmediavideodownloaderapps.R
import com.example.socialmediavideodownloaderapps.ui.theme.selectedColor

@Composable
fun BottomSheetButton(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Int = R.color.white,
    bgColor: Int = R.color.mainColor,
    roundness: Int = 10,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            ,
        colors = ButtonDefaults.buttonColors(
            containerColor = selectedColor
        ),
        shape = RoundedCornerShape(roundness.dp)
    ) {
        Text(
           text,
            color = Color.White
        )
    }
}
