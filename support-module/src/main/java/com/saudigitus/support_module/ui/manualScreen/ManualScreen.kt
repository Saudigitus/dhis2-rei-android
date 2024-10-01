package com.example.viewtest.ui.manualScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.viewtest.R
import com.example.viewtest.ui.components.BasicApp
import com.example.viewtest.ui.components.ListCard
import com.saudigitus.support_module.ui.MenuScreen

@Composable
fun ManualScreen(
    //viewModel: MenuViewModel,
    //onBack: () -> Unit
) {
    BasicApp(title = "Manuais", content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFFFFFFF))
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Manuais de utilizadores",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(Modifier.height(20.dp))
            ListCard(
                imageResId = R.drawable.manual_icon, title = "Manual title here alfa omega zeta Yotta",
                subtitle = "Manual subtitle here alfas",
                icon = Icons.Default.KeyboardArrowRight,
            )
            Spacer(Modifier.height(10.dp))
            ListCard(
                imageResId = R.drawable.manual_icon, title = "Manual title here",
                subtitle = "Manual subtitle here alfa",
                icon = Icons.Default.KeyboardArrowRight,
            )
            Spacer(Modifier.height(10.dp))
            ListCard(
                imageResId = R.drawable.manual_icon, title = "Manual title here",
                subtitle = "Manual subtitle here alfa",
                icon = Icons.Default.KeyboardArrowRight,
            )
        }


    })
}

@Preview(showBackground = true)
@Composable
fun MyScreenPreview() {
    ManualScreen()
}