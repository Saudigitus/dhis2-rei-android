package com.saudigitus.support_module.ui.SupportScreen

import android.content.Context
import androidx.lifecycle.ViewModel
import com.saudigitus.support_module.data.local.ManualsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ErrorsViewModel @Inject internal constructor(
    private val manualsRepository: ManualsRepository,
    @ApplicationContext private val context: Context
)  : ViewModel() {

}