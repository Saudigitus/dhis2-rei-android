package com.saudigitus.support_module.data.local

import com.saudigitus.support_module.data.models.erros.ErrorViewModel
import com.saudigitus.support_module.data.models.manuals.ManualItem
import kotlinx.coroutines.flow.Flow

interface SyncErrorsRepository {
    fun getSyncErrors(): Flow<List<ErrorViewModel>>
}