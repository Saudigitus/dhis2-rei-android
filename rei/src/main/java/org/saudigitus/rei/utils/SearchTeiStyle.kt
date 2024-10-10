package org.saudigitus.rei.utils

import androidx.compose.ui.graphics.Color
import org.dhis2.commons.data.SearchTeiModel
import org.hisp.dhis.android.core.D2
import org.hisp.dhis.android.core.event.EventStatus

class SearchTeiStyle(private val d2: D2) {
    fun getTeiCardBackground(searchTeiModel: SearchTeiModel): Pair<String, Color> {
        val enrollment = searchTeiModel.enrollments.getOrNull(0)

        return if (enrollment != null) {
            val event = d2.eventModule().events()
                .byEnrollmentUid().eq(enrollment.uid())
                .one().blockingGet()

            when (event?.status()) {
                EventStatus.COMPLETED -> Pair(EventStatus.COMPLETED.name.capital(), Color.Green)
                EventStatus.OVERDUE -> Pair(EventStatus.OVERDUE.name.capital(), Color.Yellow)
                EventStatus.SCHEDULE -> Pair(EventStatus.ACTIVE.name.capital(), Color.Red)
                else -> Pair(EventStatus.ACTIVE.name.capital(), Color.LightGray)
            }
        } else {
            Pair(EventStatus.ACTIVE.name.capital(), Color.LightGray)
        }
    }
}
