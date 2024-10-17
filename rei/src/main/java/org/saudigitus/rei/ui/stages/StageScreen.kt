package org.saudigitus.rei.ui.stages

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun StageScreen(viewModel: StageViewModel) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    StageTab(
        modifier = Modifier.fillMaxWidth()
            .fillMaxSize(.3f),
        state = state,
        onAction = viewModel::loadStageData,
    )
}
