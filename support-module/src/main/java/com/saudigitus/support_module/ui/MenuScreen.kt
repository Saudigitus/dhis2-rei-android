package com.saudigitus.support_module.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saudigitus.support_module.R
import com.saudigitus.support_module.ui.components.CustomCard


@Composable
fun MenuScreen(
    //viewModel: MenuViewModel,
    //onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .background(color = Color(0xFFFFFFFF))
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // First Card
            CustomCard(imageResId = R.drawable.manual_icon, title = "Manuais")
            // Second Card
            CustomCard(imageResId = R.drawable.support, title = "Supporte")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyScreenPreview() {
    MenuScreen()
}