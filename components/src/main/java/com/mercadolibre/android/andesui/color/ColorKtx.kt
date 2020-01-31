package com.mercadolibre.android.andesui.color

import android.content.Context
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat

@ColorInt
internal fun Int.toColor(context: Context): Int {
    return ContextCompat.getColor(context, this)
}