package com.mercadolibre.android.andesui.typeface

import android.content.Context
import android.graphics.Typeface
import android.support.annotation.FontRes
import android.support.v4.content.res.ResourcesCompat
import android.util.Log

/**
 * Extension function that retrieves a desired typeface if it founds it.
 * Otherwise, it returns the (provided or not) default typeface.
 *
 * @param desiredTypeface the int res id that identifies typeface we want to retrieve
 * @param defaultTypeface the typeface we'd like to get if the desired is not available. By default is the system's default
 * @return the found typeface
 */
fun Context.getFontOrDefault(@FontRes desiredTypeface: Int, defaultTypeface: Typeface = Typeface.DEFAULT): Typeface {
    return try {
        ResourcesCompat.getFont(this, desiredTypeface) ?: defaultTypeface
    } catch (error: Exception) {
        Log.e("FontKtx", "Error solving typeface", error)
        defaultTypeface
    }
}