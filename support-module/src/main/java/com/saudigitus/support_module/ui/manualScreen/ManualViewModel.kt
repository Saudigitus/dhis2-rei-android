package com.saudigitus.support_module.ui.manualScreen

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPdfReaderState
import com.saudigitus.support_module.data.local.ManualsRepository
import com.saudigitus.support_module.ui.ManualsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ManualViewModel @Inject internal constructor(
    private val manualsRepository: ManualsRepository,
    @ApplicationContext private val context: Context
)  : ViewModel() {
    private val _uiState = MutableStateFlow(ManualsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getManualsFromDataStore()
    }

    val _pdfVerticallReaderState = MutableStateFlow( VerticalPdfReaderState(
        resource = ResourceType.Local(Uri.parse("")),
        isZoomEnable = true
    ))
    val pdfVerticalReaderState = _pdfVerticallReaderState.asStateFlow()

    private fun getManualsFromDataStore(){
        viewModelScope.launch {
            _uiState.update {it.copy(isDownloading = true)}
            manualsRepository.getManualsDataStore().collect{ manuals ->
                manuals.forEach {
                    Timber.d("_CLICK_IS IN DOWNLOAD ${it.uid}")
                    manualsRepository.downloadManualToLocal(context = context, url= it.path ?: "", fileName = it.uid)
                }
                _uiState.update {
                    it.copy(manualItems = manuals, isDownloading = false)
                }
            }
        }
    }

    fun openPdf(file: File){
        Timber.d("_CLICK_IS IN OPEN_PDF ${file.absolutePath}")
            _pdfVerticallReaderState.value = VerticalPdfReaderState(
                resource = ResourceType.Local(Uri.fromFile(file)),
                isZoomEnable = true
            )
        _uiState.update {it.copy(hasFileLoaded = true)}
    }
    fun open(context: Context, fileName: String): File  {
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "$fileName.pdf")
        if (file.exists()) {
            return file
        } else {
            Toast.makeText(context, "Manual not downloaded yet!", Toast.LENGTH_SHORT).show()
        }
        return file
    }
}