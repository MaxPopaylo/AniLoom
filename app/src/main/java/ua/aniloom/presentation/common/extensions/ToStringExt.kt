package ua.aniloom.presentation.common.extensions

import java.util.Locale

fun Int.formatToString(): String = String.format(Locale.getDefault(), "%d", this)
fun Double.formatToString(): String = String.format(Locale.getDefault(), "%.2f", this)