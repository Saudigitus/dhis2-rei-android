package com.saudigitus.support_module.ui.manualScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.saudigitus.support_module.R
import com.saudigitus.support_module.ui.components.BasicApp
import com.saudigitus.support_module.ui.components.ListCard
import com.saudigitus.support_module.ui.MenuScreen
import timber.log.Timber

@Composable
fun ManualScreen(
    navController: NavHostController,
    onBack: () -> Unit
) {
    val viewModel = hiltViewModel<ManualViewModel>()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    BasicApp(
        title = stringResource(id = R.string.manuals),
        onBack = onBack,
        content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFFFFFFF))
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.manual_title),
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(Modifier.height(20.dp))

            LazyColumn {
                items(uiState.manualItems) { manual ->
                    ListCard(
                        imageResId = R.drawable.manual_icon, title = manual.title ?: "title",
                        subtitle = manual.subtitle ?: "subtitle",
                        icon = Icons.Default.ArrowDownward,
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            ListCard(
                imageResId = R.drawable.manual_icon, title = "Manual title here",
                subtitle = "Manual subtitle here alfa",
                icon = Icons.Default.ArrowDownward,
            )
            Spacer(Modifier.height(10.dp))
            ListCard(
                imageResId = R.drawable.manual_icon, title = "Manual title here",
                subtitle = "Manual subtitle here alfa",
                icon = Icons.Default.ArrowDownward,
            )
        }


    })
}

@Preview(showBackground = true)
@Composable
fun MyScreenPreview() {
   ManualScreen(navController = NavHostController(LocalContext.current), onBack = {})
}