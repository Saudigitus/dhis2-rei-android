package org.saudigitus.rei.utils

import androidx.compose.ui.graphics.Color
import org.dhis2.commons.data.SearchTeiModel
import org.hisp.dhis.android.core.D2
import org.hisp.dhis.android.core.event.EventStatus
import org.saudigitus.rei.ui.theme.Light_Error
import org.saudigitus.rei.ui.theme.Light_Success
import org.saudigitus.rei.ui.theme.Light_Warning

class SearchTeiStyle(private val d2: D2) {
    fun getTeiCardBackground(searchTeiModel: SearchTeiModel): Pair<String, Color> {
        val enrollment = searchTeiModel.enrollments.getOrNull(0)

        return if (enrollment != null) {
            val event = d2.eventModule().events()
                .byEnrollmentUid().eq(enrollment.uid())
                .one().blockingGet()

            when (event?.status()) {
                EventStatus.COMPLETED -> Pair(EventStatus.COMPLETED.name.capital(), Light_Success.copy(.2f))
                EventStatus.OVERDUE -> Pair(EventStatus.OVERDUE.name.capital(), Light_Warning.copy(.2f))
                EventStatus.SCHEDULE -> Pair(EventStatus.ACTIVE.name.capital(), Light_Error.copy(.2f))
                else -> Pair(EventStatus.ACTIVE.name.capital(), Color.Transparent)
            }
        } else {
            Pair(EventStatus.ACTIVE.name.capital(), Color.Transparent)
        }
    }
}
