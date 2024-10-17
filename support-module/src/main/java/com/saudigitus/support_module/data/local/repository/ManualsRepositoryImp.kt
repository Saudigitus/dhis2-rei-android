package com.saudigitus.support_module.data.local.repository

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.core.content.FileProvider
import com.saudigitus.support_module.data.local.ManualsRepository
import com.saudigitus.support_module.data.local.database.ManualsDao
import com.saudigitus.support_module.data.models.manuals.Manual
import com.saudigitus.support_module.data.models.manuals.ManualItem
import com.saudigitus.support_module.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import org.hisp.dhis.android.core.D2
import timber.log.Timber
import java.io.File

class ManualsRepositoryImp(
    private val  manualsDAO: ManualsDao,
    private val d2: D2
): ManualsRepository {
    override fun getManualsDataStore(): Flow<List<ManualItem>> {
        val dataStoreValue = d2.dataStoreModule().dataStore().byKey()
            .eq(Constants.MEDIA_DATA_STORE_KEY).blockingGet()
            .getOrNull(0)?.value()

        val dataStore = dataStoreValue?.let { Manual.fromJson(it) }

        return if (dataStore != null) {
            flowOf(dataStore)
        } else {
            flowOf(emptyList()) // Return an empty list if dataStore is null
        }
    }

    override suspend fun downloadManualToLocal(context: Context, url: String, fileName: String): Unit = withContext(Dispatchers.IO) {
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(url)
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
            "$fileName.pdf"
        )
        if (!file.exists()) {
            val request = DownloadManager.Request(uri).apply {
                setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                // Store the file in the app's private external files directory
                setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOCUMENTS, "$fileName.pdf")
            }
            downloadManager.enqueue(request)
        }
        return@withContext
    }

    override suspend fun getManualDetails(uid: String): ManualItem? {
        TODO("Not yet implemented")

    }

    override suspend fun storeLocalManualDetails(manual: ManualItem) = withContext(
        Dispatchers.IO)  {
        val resp = manualsDAO.create(manual)
        println("STORE_RESP: $resp")
    }

    override suspend fun getAllLocalManualDetails(): List<ManualItem?> = withContext(Dispatchers.IO)  {
        return@withContext manualsDAO.getAll()
    }

    override suspend fun getLocalManualDetails(uid: String): ManualItem? = withContext(Dispatchers.IO) {
        return@withContext manualsDAO.getDetailsById(uid)
    }

    override suspend fun openManual(context: Context, url: String, fileName: String): File? = withContext(Dispatchers.IO)  {
        // Get the file from the app's private storage
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "$fileName.pdf")
        if (file.exists()) {
            return@withContext file
        }
        return@withContext null
    }
}