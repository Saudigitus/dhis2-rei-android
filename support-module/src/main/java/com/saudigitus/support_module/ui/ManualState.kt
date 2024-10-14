package com.saudigitus.support_module.ui

import com.saudigitus.support_module.data.models.manuals.ManualItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ManualsUiState(
    val isLoading: Boolean = false,
    val manualItems : List<ManualItem> = emptyList(),

    )