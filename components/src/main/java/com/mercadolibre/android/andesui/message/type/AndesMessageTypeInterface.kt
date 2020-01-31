package com.mercadolibre.android.andesui.message.type

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.message.hierarchy.AndesMessageHierarchyInterface
import com.mercadolibre.android.andesui.utils.buildColoredCircularShapeWithIconDrawable


internal sealed class AndesMessageTypeInterface {

    abstract fun primaryColor(): AndesColor

    abstract fun secondaryColor(): AndesColor

    fun pipeColor(): AndesColor = primaryColor()

    abstract fun icon(context: Context, hierarchy: AndesMessageHierarchyInterface): Drawable?

    fun iconBackgroundColor() = secondaryColor()
}

internal object AndesNeutralMessageType : AndesMessageTypeInterface() {
    override fun primaryColor() = AndesColor(R.color.andesui_message_highlight_primary)
    override fun secondaryColor() = AndesColor(R.color.andesui_message_highlight_primary_dark)
    override fun icon(context: Context, hierarchy: AndesMessageHierarchyInterface) = buildColoredCircularShapeWithIconDrawable(
            ContextCompat.getDrawable(context, R.drawable.andesui_ui_feedback_info_16) as BitmapDrawable,
            context,
            AndesColor(R.color.andesui_white),
            hierarchy.iconBackgroundColor(this)?.colorInt(context),
            context.resources.getDimension(R.dimen.andesui_message_icon_diameter).toInt()
    )
}

internal object AndesSuccessMessageState : AndesMessageTypeInterface() {
    override fun primaryColor() = AndesColor(R.color.andesui_message_success_primary)
    override fun secondaryColor() = AndesColor(R.color.andesui_message_success_primary_dark)
    override fun icon(context: Context, hierarchy: AndesMessageHierarchyInterface) = buildColoredCircularShapeWithIconDrawable(
            ContextCompat.getDrawable(context, R.drawable.andesui_ui_feedback_success_16) as BitmapDrawable,
            context,
            AndesColor(R.color.andesui_white),
            hierarchy.iconBackgroundColor(this)?.colorInt(context),
            context.resources.getDimension(R.dimen.andesui_message_icon_diameter).toInt()
    )
}

internal object AndesWarningMessageState : AndesMessageTypeInterface() {
    override fun primaryColor() = AndesColor(R.color.andesui_message_warning_primary)
    override fun secondaryColor() = AndesColor(R.color.andesui_message_warning_primary_dark)
    override fun icon(context: Context, hierarchy: AndesMessageHierarchyInterface) = buildColoredCircularShapeWithIconDrawable(
            ContextCompat.getDrawable(context, R.drawable.andesui_ui_feedback_warning_16) as BitmapDrawable,
            context,
            AndesColor(R.color.andesui_white),
            hierarchy.iconBackgroundColor(this)?.colorInt(context),
            context.resources.getDimension(R.dimen.andesui_message_icon_diameter).toInt()
    )
}

internal object AndesErrorMessageState : AndesMessageTypeInterface() {
    override fun primaryColor() = AndesColor(R.color.andesui_message_error_primary)
    override fun secondaryColor() = AndesColor(R.color.andesui_message_error_primary_dark)
    override fun icon(context: Context, hierarchy: AndesMessageHierarchyInterface) = buildColoredCircularShapeWithIconDrawable(
            ContextCompat.getDrawable(context, R.drawable.andesui_ui_feedback_warning_16) as BitmapDrawable,
            context,
            AndesColor(R.color.andesui_white),
            hierarchy.iconBackgroundColor(this)?.colorInt(context),
            context.resources.getDimension(R.dimen.andesui_message_icon_diameter).toInt()
    )
}