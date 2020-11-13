package com.kenjitakahashirial.randomness.utilities

fun IntRange.isValid(includeStart: Boolean = true, includeEnd: Boolean = true): Boolean =
    start < endInclusive && (endInclusive - start > 1 || includeStart || includeEnd) ||
            (start == endInclusive && includeStart && includeEnd)