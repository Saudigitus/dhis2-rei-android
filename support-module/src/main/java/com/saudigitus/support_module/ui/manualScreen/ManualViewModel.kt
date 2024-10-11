package com.saudigitus.support_module.ui.manualScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saudigitus.support_module.data.local.ManualsRepository
import com.saudigitus.support_module.data.models.manuals.ManualItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManualViewModel @Inject internal constructor(
    private val manualsRepository: ManualsRepository,
)  : ViewModel() {

    fun getLocalMediaDetails(uid: String): ManualItem? {
        var details: ManualItem? = null
        viewModelScope.launch {
            details = manualsRepository.getLocalManualDetails(uid)
            println("ONE_MEDIA_DETAILS: ${details.toString()}")
        }
        return details
    }
}