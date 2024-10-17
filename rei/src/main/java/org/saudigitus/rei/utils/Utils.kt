package org.saudigitus.rei.utils

import org.hisp.dhis.android.core.D2
import org.saudigitus.rei.utils.ObjectMapper.translateJsonToObject

object Utils {
    inline fun <reified T> fromJson(json: String?): T? = if (json != null) {
        translateJsonToObject()
            .readValue(
                json,
                T::class.java,
            )
    } else {
        null
    }

    fun <T> T.toJson(): String = translateJsonToObject().writeValueAsString(this)

    fun datastorePrograms(d2: D2): List<String> {
        return d2.reiModuleDatastore()?.programs?.map { it.uid } ?: emptyList()
    }
}
