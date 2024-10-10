package org.saudigitus.rei.utils

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
