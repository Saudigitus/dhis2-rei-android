package com.saudigitus.support_module.ui.manualScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saudigitus.support_module.data.local.ManualsRepository
import com.saudigitus.support_module.data.models.manuals.ManualItem
import com.saudigitus.support_module.ui.ManualsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManualViewModel @Inject internal constructor(
    private val manualsRepository: ManualsRepository,
)  : ViewModel() {
    private val _uiState = MutableStateFlow(ManualsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getManualsFromDataStore()
    }

    private fun getManualsFromDataStore(){
        viewModelScope.launch {
            manualsRepository.getManualsDataStore().collect{ manuals ->
                println("ALL_MANUALS_DETAILS: ${manuals.toString()}")
                _uiState.update {
                    it.copy(manualItems = manuals)
                }
            }
        }
    }

    /*fun getManualsFromDataStore(): Flow<List<ManualItem>> {
      var manuals: Flow<List<ManualItem>> =  emptyFlow();
        viewModelScope.launch {
            manuals = manualsRepository.getManualsDataStore();
            println("ALL_MANUALS_DETAILS: ${manuals.toString()}")
        }
        return manuals
    }*/

    fun getLocalMediaDetails(uid: String): ManualItem? {
        var details: ManualItem? = null
        viewModelScope.launch {
            details = manualsRepository.getLocalManualDetails(uid)
            println("ONE_MEDIA_DETAILS: ${details.toString()}")
        }
        return details
    }
}