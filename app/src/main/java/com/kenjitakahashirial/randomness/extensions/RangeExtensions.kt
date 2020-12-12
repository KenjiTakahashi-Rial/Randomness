package com.kenjitakahashirial.randomness.extensions

fun IntRange.isValid(includeStart: Boolean = true, includeEnd: Boolean = true): Boolean =
    start < endInclusive && (endInclusive - start > 1 || includeStart || includeEnd) ||
            (start == endInclusive && includeStart && includeEnd)