package com.saudigitus.support_module.data.local

import android.content.Context
import com.saudigitus.support_module.data.models.manuals.ManualItem
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

interface ManualsRepository {
    fun getManualsDataStore(): Flow<List<ManualItem>>
    suspend fun getManualDetails(uid: String): ManualItem?
    suspend fun storeLocalManualDetails(manual: ManualItem)
    suspend fun getAllLocalManualDetails(): List<ManualItem?>
    suspend fun getLocalManualDetails(uid: String): ManualItem?

    suspend fun downloadManualToLocal(
        context: Context,
        url: String,
        fileName: String
    )
}