package com.example.viewtest.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.viewtest.ui.manualScreen.ErrosScreen

@Composable
fun ErrorComponent(error: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .border(1.dp, Color.Red, RoundedCornerShape(8.dp)) // Red border
    ) {
        // Applying background to the entire card content
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFCDD2))
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = error,
            )
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "title",
                modifier = Modifier
                    // .fillMaxWidth() // Ensures the text takes up the card width
                    .background(Color(0xFFFFCDD2))
                    .size(20.dp),

                tint = Color.Gray
            )
        }

    }
}
@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    ErrorComponent("Error It is a long established fact reader")
}