package com.zykrave.anirumy.core.common.utils

import com.zykrave.anirumy.core.base.UNKNOWN_CHAR
import java.text.Normalizer

object StringUtils {
    fun String?.orUnknown() = this ?: UNKNOWN_CHAR

    fun String.htmlStripped() = replace(Regex("<[^>]+>"), "")

    fun String.slugify() = Normalizer
        .normalize(this, Normalizer.Form.NFD)
        .replace(Regex("[^\\p{ASCII}]"), "")
        .replace(Regex("[^a-zA-Z0-9\\s]+"), "")
        .trim()
        .replace(Regex("\\s+"), "-")
}