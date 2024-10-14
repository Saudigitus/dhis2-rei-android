package org.saudigitus.rei.utils

import org.dhis2.commons.date.DateUtils
import org.hisp.dhis.android.core.D2
import org.hisp.dhis.android.core.event.EventStatus
import java.util.Locale

fun String.capital() = this.replaceFirstChar {
    if (it.isLowerCase()) {
        it.titlecase(
            Locale.ENGLISH,
        )
    } else {
        this
    }
}

fun D2.countEventsByStatusToday(
    program: String,
    stage: String,
    eventStatus: EventStatus,
): Int = eventModule().events()
    .byProgramUid().eq(program)
    .byProgramStageUid().eq(stage)
    .byEventDate().eq(DateUtils.getInstance().today)
    .byStatus().eq(eventStatus)
    .blockingCount()
