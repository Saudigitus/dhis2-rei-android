package com.saudigitus.support_module.data.local.repository

import com.saudigitus.support_module.data.local.SyncErrorsRepository
import com.saudigitus.support_module.data.models.erros.ErrorViewModel
import com.saudigitus.support_module.utils.ErrorModelMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.hisp.dhis.android.core.D2
import timber.log.Timber

class SyncErrorsRepositoryImpl(
    private val d2: D2,
    private val errorMapper: ErrorModelMapper,
): SyncErrorsRepository {
    /*override fun getSyncErrors() {

        val errors: MutableList<ErrorViewModel> = ArrayList()
        errors.addAll(
            errorMapper.mapD2Error(d2.maintenanceModule().d2Errors().blockingGet()),
        )
        errors.addAll(
            errorMapper.mapConflict(
                d2.importModule().trackerImportConflicts().blockingGet(),
            ),
        )
        errors.addAll(
            errorMapper.mapFKViolation(
                d2.maintenanceModule().foreignKeyViolations().blockingGet(),
            ),
        )
        //errors
        Timber.d("ERRORS_LIST: $errors")

    }*/

    override fun getSyncErrors(): Flow<List<ErrorViewModel>> {

        val errors: MutableList<ErrorViewModel> = ArrayList()
        errors.addAll(
            errorMapper.mapD2Error(d2.maintenanceModule().d2Errors().blockingGet()),
        )
        errors.addAll(
            errorMapper.mapConflict(
                d2.importModule().trackerImportConflicts().blockingGet(),
            ),
        )
        errors.addAll(
            errorMapper.mapFKViolation(
                d2.maintenanceModule().foreignKeyViolations().blockingGet(),
            ),
        )
        //errors
        Timber.d("ERRORS_LIST: $errors")
       return flowOf(errors.toList())
    }
}