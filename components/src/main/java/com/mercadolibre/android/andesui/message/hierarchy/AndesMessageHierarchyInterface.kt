package com.mercadolibre.android.andesui.message.hierarchy

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.message.AndesMessage
import com.mercadolibre.android.andesui.message.state.AndesMessageStateInterface
import com.mercadolibre.android.andesui.typeface.getFontOrDefault
import com.mercadolibre.android.andesui.utils.buildColoredBitmapDrawable

/**
 * Defines all style related properties that an [AndesMessage] needs to be drawn properly.
 * Those properties change depending on the style of the message.
 *
 */
internal sealed class AndesMessageHierarchyInterface {
    /**
     * Returns a [Drawable] that contains the color data for the message background.
     * This includes color of the bg for normal, pressed, hover and focused states.
     *
     * @param context needed for accessing some resources.
     * @return a [Drawable] that contains the color data for the message background.
     */
    abstract fun backgroundColor(context: Context): Int

    abstract fun backgroundColor(context: Context, state: AndesMessageStateInterface): Int

    /**
     * Returns a [ColorStateList] that contains the data for the text color.
     * We are using [ColorStateList] because text color depends on the message state. E.g. text color for enabled state is different
     * than for the disabled state.
     *
     * @param context needed for accessing some resources.
     * @return a [ColorStateList] that contains the data for the text color.
     */
    @ColorInt
    abstract fun textColor(context: Context): Int

    /**
     * Returns an [Int] representing a @ColorInt that will be used when tinting the icon.
     *
     * @param context needed for accessing some resources.
     * @return an [Int] representing a @ColorInt that will be used when tinting the icon.
     */
    @ColorInt
    abstract fun dismissableIconColor(context: Context): Int

    //TODO Documentar
    fun dismissableIcon(hierarchy: AndesMessageHierarchyInterface, context: Context) =
            buildColoredBitmapDrawable(
                    ContextCompat.getDrawable(context, R.drawable.andesui_ui_close_20) as BitmapDrawable,
                    context,
                    hierarchy.dismissableIconColor(context)
            )

    /**
     * Returns the [Typeface] that should be used for the title inside the [AndesMessage].
     *
     * @param context needed for accessing some resources. In this case, for accessing the kotlin extension defines for the context.
     * @return the [Typeface] that should be used for the text inside the [AndesMessage].
     */
    fun titleTypeface(context: Context): Typeface = context.getFontOrDefault(R.font.andesui_font_semibold)

    /**
     * Returns the [Typeface] that should be used for the text inside the [AndesMessage].
     *
     * @param context needed for accessing some resources. In this case, for accessing the kotlin extension defines for the context.
     * @return the [Typeface] that should be used for the text inside the [AndesMessage].
     */
    fun descriptionTypeface(context: Context): Typeface = context.getFontOrDefault(R.font.andesui_font_regular)

}

internal object AndesLoudMessageHierarchy : AndesMessageHierarchyInterface() {
    override fun backgroundColor(context: Context) = throw IllegalStateException("Loud message cannot be colored without an AndesMessageState")
    override fun backgroundColor(context: Context, state: AndesMessageStateInterface) = state.primaryColor(context)
    override fun textColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_loud_text)
    override fun dismissableIconColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_loud_dismissable)
}

internal object AndesQuietMessageHierarchy : AndesMessageHierarchyInterface() {
    override fun backgroundColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_quiet_bg) //TODO Check
    override fun backgroundColor(context: Context, state: AndesMessageStateInterface) = backgroundColor(context)
    override fun textColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_quiet_text) //TODO Check
    override fun dismissableIconColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_quiet_dismissable)
}
