package com.mercadolibre.android.andesui.message.state

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import com.mercadolibre.android.andesui.R



internal sealed class AndesMessageStateInterface {

    @ColorInt
    abstract fun primaryColor(context: Context): Int

    @ColorInt
    abstract fun primaryDarkColor(context: Context): Int

    @ColorInt
    fun pipeColor(context: Context): Int = primaryColor(context)

    abstract fun icon(context: Context) : Drawable?

    @ColorInt
    fun iconBackgroundColor(context: Context) = primaryDarkColor(context)
}

internal object AndesHighlightMessageState : AndesMessageStateInterface() {
    override fun primaryColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_highlight_primary)
    override fun primaryDarkColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_highlight_primary_dark)
    override fun icon(context: Context) = ContextCompat.getDrawable(context, R.drawable.andesui_icon)
}

internal object AndesSuccessMessageState : AndesMessageStateInterface() {
    override fun primaryColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_success_primary)
    override fun primaryDarkColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_success_primary_dark)
    override fun icon(context: Context) = ContextCompat.getDrawable(context, R.drawable.andesui_icon)
}

internal object AndesWarningMessageState : AndesMessageStateInterface() {
    override fun primaryColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_warning_primary)
    override fun primaryDarkColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_warning_primary_dark)
    override fun icon(context: Context) = ContextCompat.getDrawable(context, R.drawable.andesui_icon)
}

internal object AndesErrorMessageState : AndesMessageStateInterface() {
    override fun primaryColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_error_primary)
    override fun primaryDarkColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_error_primary_dark)
    override fun icon(context: Context) = ContextCompat.getDrawable(context, R.drawable.andesui_icon)
}