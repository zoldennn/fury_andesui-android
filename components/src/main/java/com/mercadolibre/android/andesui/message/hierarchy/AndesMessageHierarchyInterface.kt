package com.mercadolibre.android.andesui.message.hierarchy

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.button.hierarchy.BackgroundColorConfigMessage
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.message.AndesMessage
import com.mercadolibre.android.andesui.message.type.AndesMessageTypeInterface
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
    abstract fun backgroundColor(): AndesColor

    abstract fun backgroundColor(state: AndesMessageTypeInterface): AndesColor

    /**
     * Returns a [ColorStateList] that contains the data for the text color.
     * We are using [ColorStateList] because text color depends on the message type. E.g. text color for enabled type is different
     * than for the disabled type.
     *
     * @param context needed for accessing some resources.
     * @return a [ColorStateList] that contains the data for the text color.
     */
    abstract fun textColor(): AndesColor

    /**
     * Returns an [Int] representing a @ColorInt that will be used when tinting the icon.
     *
     * @param context needed for accessing some resources.
     * @return an [Int] representing a @ColorInt that will be used when tinting the icon.
     */
    abstract fun dismissableIconColor(): AndesColor

    /**
     * Returns the [Drawable] that will be used as the dismiss icon.
     *
     * @param hierarchy needed to obtain the color of the dismiss icon.
     * @param context needed for accessing some resources.
     */
    fun dismissableIcon(hierarchy: AndesMessageHierarchyInterface, context: Context) =
            buildColoredBitmapDrawable(
                    ContextCompat.getDrawable(context, R.drawable.andesui_ui_close_20) as BitmapDrawable,
                    context,
                    hierarchy.dismissableIconColor()
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
    fun bodyTypeface(context: Context): Typeface = context.getFontOrDefault(R.font.andesui_font_regular)

    /**
     * Returns the background color that the icon will have. It's the color of the container in which the icon will live.
     *
     * @param context needed for accessing some resources.
     * @param type needed because the background color is intimately related to the type of the Message.
     * @return the background color that the icon will have.
     */

    abstract fun iconBackgroundColor(state: AndesMessageTypeInterface): AndesColor?

    fun primaryActionBackgroundColor(state: AndesMessageTypeInterface): BackgroundColorConfigMessage {
        val iconBackgroundColor = iconBackgroundColor(state)
                ?: throw IllegalStateException("")

        return BackgroundColorConfigMessage(
                enabledColor = iconBackgroundColor,
                pressedColor = AndesColor(iconBackgroundColor.colorRes, 0.2f),
                focusedColor = iconBackgroundColor,
                hoveredColor = iconBackgroundColor,
                disabledColor = iconBackgroundColor,
                otherColor = iconBackgroundColor)
    }

    fun primaryActionTextColor() = AndesColor(R.color.andesui_white)

    fun secondaryActionBackgroundColor(state: AndesMessageTypeInterface): BackgroundColorConfigMessage {
        val backgroundColor = backgroundColor(state)
        return BackgroundColorConfigMessage(
                enabledColor = AndesColor(R.color.andesui_bu_transparent_idle),
                pressedColor = AndesColor(R.color.andesui_bu_transparent_idle, 0.2f),
                focusedColor = backgroundColor,
                hoveredColor = backgroundColor,
                disabledColor = backgroundColor,
                otherColor = backgroundColor)
    }

    abstract fun secondaryActionTextColor(state: AndesMessageTypeInterface): AndesColor
}

internal object AndesLoudMessageHierarchy : AndesMessageHierarchyInterface() {
    override fun backgroundColor() = throw IllegalStateException("Loud message cannot be colored without an AndesMessageState")
    override fun backgroundColor(state: AndesMessageTypeInterface) = state.primaryColor()
    override fun textColor() = AndesColor(R.color.andesui_message_loud_text)
    override fun dismissableIconColor() = AndesColor(R.color.andesui_message_loud_dismissable)
    override fun iconBackgroundColor(state: AndesMessageTypeInterface) = state.secondaryColor()
    override fun secondaryActionTextColor(state: AndesMessageTypeInterface) = AndesColor(R.color.andesui_white)
}

internal object AndesQuietMessageHierarchy : AndesMessageHierarchyInterface() {
    override fun backgroundColor() = AndesColor(R.color.andesui_message_quiet_bg) //TODO Check
    override fun backgroundColor(state: AndesMessageTypeInterface) = backgroundColor()
    override fun textColor() = AndesColor(R.color.andesui_message_quiet_text) //TODO Check
    override fun dismissableIconColor() = AndesColor(R.color.andesui_message_quiet_dismissable)
    override fun iconBackgroundColor(state: AndesMessageTypeInterface) = state.primaryColor()
    override fun secondaryActionTextColor(state: AndesMessageTypeInterface) = iconBackgroundColor(state)
}
