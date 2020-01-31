package com.mercadolibre.android.andesui.message.type

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.message.hierarchy.AndesMessageHierarchyInterface
import com.mercadolibre.android.andesui.utils.buildColoredCircularShapeWithIconDrawable


internal sealed class AndesMessageTypeInterface {

    @ColorInt
    abstract fun primaryColor(context: Context): Int

    @ColorInt
    abstract fun secondaryColor(context: Context): Int

    @ColorInt
    fun pipeColor(context: Context): Int = primaryColor(context)

    abstract fun icon(context: Context, hierarchy: AndesMessageHierarchyInterface): Drawable?

    @ColorInt
    fun iconBackgroundColor(context: Context) = secondaryColor(context)
}

internal object AndesNeutralMessageType : AndesMessageTypeInterface() {
    override fun primaryColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_highlight_primary)
    override fun secondaryColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_highlight_primary_dark)
    override fun icon(context: Context, hierarchy: AndesMessageHierarchyInterface) = buildColoredCircularShapeWithIconDrawable(
            ContextCompat.getDrawable(context, R.drawable.andesui_ui_feedback_info_16) as BitmapDrawable,
            context,
            ContextCompat.getColor(context, R.color.andesui_white), hierarchy.iconBackgroundColor(context, this),
            context.resources.getDimension(R.dimen.andesui_message_icon_diameter).toInt()
    )
}

internal object AndesSuccessMessageType : AndesMessageTypeInterface() {
    override fun primaryColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_success_primary)
    override fun secondaryColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_success_primary_dark)
    override fun icon(context: Context, hierarchy: AndesMessageHierarchyInterface) = buildColoredCircularShapeWithIconDrawable(
            ContextCompat.getDrawable(context, R.drawable.andesui_ui_feedback_success_16) as BitmapDrawable,
            context,
            ContextCompat.getColor(context, R.color.andesui_white), hierarchy.iconBackgroundColor(context, this),
            context.resources.getDimension(R.dimen.andesui_message_icon_diameter).toInt()
    )
}

internal object AndesWarningMessageType : AndesMessageTypeInterface() {
    override fun primaryColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_warning_primary)
    override fun secondaryColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_warning_primary_dark)
    override fun icon(context: Context, hierarchy: AndesMessageHierarchyInterface) = buildColoredCircularShapeWithIconDrawable(
            ContextCompat.getDrawable(context, R.drawable.andesui_ui_feedback_warning_16) as BitmapDrawable,
            context,
            ContextCompat.getColor(context, R.color.andesui_white), hierarchy.iconBackgroundColor(context, this),
            context.resources.getDimension(R.dimen.andesui_message_icon_diameter).toInt()
    )
}

internal object AndesErrorMessageType : AndesMessageTypeInterface() {
    override fun primaryColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_error_primary)
    override fun secondaryColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_error_primary_dark)
    override fun icon(context: Context, hierarchy: AndesMessageHierarchyInterface) = buildColoredCircularShapeWithIconDrawable(
            ContextCompat.getDrawable(context, R.drawable.andesui_ui_feedback_warning_16) as BitmapDrawable,
            context,
            ContextCompat.getColor(context, R.color.andesui_white), hierarchy.iconBackgroundColor(context, this),
            context.resources.getDimension(R.dimen.andesui_message_icon_diameter).toInt()
    )
}