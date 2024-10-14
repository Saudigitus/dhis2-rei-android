package org.saudigitus.rei.utils

import org.saudigitus.rei.utils.ObjectMapper.translateJsonToObject

object Utils {
    inline fun <reified T> fromJson(json: String?): T? = if (json != null) {
        translateJsonToObject()
            .readValue(
                json,
                T::class.java
            )
    } else {
        null
    }

    fun <T> T.toJson(): String = translateJsonToObject().writeValueAsString(this)
}