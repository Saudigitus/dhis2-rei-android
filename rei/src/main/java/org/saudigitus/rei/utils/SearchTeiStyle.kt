package org.saudigitus.rei.utils

import androidx.compose.ui.graphics.Color
import org.dhis2.commons.data.SearchTeiModel
import org.hisp.dhis.android.core.D2
import org.saudigitus.rei.R
import org.saudigitus.rei.data.model.AppConfig
import org.saudigitus.rei.ui.theme.Light_Error
import org.saudigitus.rei.ui.theme.Light_Success
import org.saudigitus.rei.ui.theme.Light_Warning

class SearchTeiStyle(private val d2: D2) {

    private var appConfig: AppConfig? = null

    init {
        appConfig = d2.reiModuleDatastore()
    }

    private fun isCompletelyVaccinated(enrollment: String): Boolean {
        val event = d2.eventOrderedByDateDesc(enrollment)

        val eventValue = d2.trackedEntityModule().trackedEntityDataValues()
            .byEvent().eq(event?.uid())
            .byDataElement().eq(appConfig?.ccvDataElement)
            .one().blockingGet()

        return eventValue?.value()?.toBoolean() == true
    }

    fun getTeiCardBackground(searchTeiModel: SearchTeiModel): Pair<Int, Color> {
        val enrollment = searchTeiModel.enrollments.getOrNull(0)

        return if (enrollment != null) {
            val isOverdue = d2.isEventOverdue(enrollment.uid(), appConfig?.stageVaccination ?: "")

            if (isOverdue) {
                Pair(R.string.overdue, Light_Warning.copy(.2f))
            } else if (isCompletelyVaccinated(enrollment.uid())) {
                Pair(R.string.vaccinated, Light_Success.copy(.2f))
            } else {
                Pair(R.string.not_vaccinated, Light_Error.copy(.2f))
            }

        } else {
            Pair(R.string.active, Color.Transparent)
        }
    }
}
