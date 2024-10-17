package com.saudigitus.support_module.data.local.repository

import com.saudigitus.support_module.data.local.SyncErrorsRepository
import org.hisp.dhis.android.core.D2
import timber.log.Timber

class SyncErrorsRepositoryImpl(
    private val d2: D2
): SyncErrorsRepository {
    override fun getSyncErrors() {
        //val errors: MutableList<ErrorViewModel> = ArrayList()
      val err =  d2.maintenanceModule().d2Errors().blockingGet()

        Timber.d("ERRORS_LIST: $err")
    }
}