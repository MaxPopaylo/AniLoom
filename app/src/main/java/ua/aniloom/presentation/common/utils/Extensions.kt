package ua.aniloom.presentation.common.utils

import java.util.Locale

fun Int.formatToString(): String = String.format(Locale.getDefault(), "%d", this)
fun Double.formatToString(): String = String.format(Locale.getDefault(), "%d", this)