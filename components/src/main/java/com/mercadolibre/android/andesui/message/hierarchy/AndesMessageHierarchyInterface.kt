package com.mercadolibre.android.andesui.message.hierarchy

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.ColorUtils
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.button.hierarchy.BackgroundColorConfigMessage
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
    fun bodyTypeface(context: Context): Typeface = context.getFontOrDefault(R.font.andesui_font_regular)

    /**
     * Returns the background color that the icon will have. It's the color of the container in which the icon will live.
     *
     * @param context needed for accessing some resources.
     * @param state needed because the background color is intimately related to the state of the Message.
     * @return the background color that the icon will have.
     */
    abstract fun iconBackgroundColor(context: Context, state: AndesMessageStateInterface): Int?

    fun primaryActionBackgroundColor(context: Context, state: AndesMessageStateInterface) = BackgroundColorConfigMessage(iconBackgroundColor(context, state)!!, ColorUtils.blendARGB(iconBackgroundColor(context, state)!!, Color.BLACK, 0.2f)!!,
            iconBackgroundColor(context, state)!!,iconBackgroundColor(context, state)!!, iconBackgroundColor(context, state)!!, iconBackgroundColor(context, state)!!)

    fun primaryActionTextColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_white)

    fun secondaryActionBackgroundColor(context: Context, state: AndesMessageStateInterface) = BackgroundColorConfigMessage(ContextCompat.getColor(context, R.color.andesui_bu_transparent_idle), ColorUtils.blendARGB(ContextCompat.getColor(context, R.color.andesui_bu_transparent_idle), Color.BLACK, 0.2f), backgroundColor(context, state),
            backgroundColor(context, state), backgroundColor(context, state), backgroundColor(context, state))

    abstract fun secondaryActionTextColor(context: Context, state: AndesMessageStateInterface) : Int



}

internal object AndesLoudMessageHierarchy : AndesMessageHierarchyInterface() {
    override fun backgroundColor(context: Context) = throw IllegalStateException("Loud message cannot be colored without an AndesMessageState")
    override fun backgroundColor(context: Context, state: AndesMessageStateInterface) = state.primaryColor(context)
    override fun textColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_loud_text)
    override fun dismissableIconColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_loud_dismissable)
    override fun iconBackgroundColor(context: Context, state: AndesMessageStateInterface) = state.secondaryColor(context)
    override fun secondaryActionTextColor(context: Context, state: AndesMessageStateInterface): Int = ContextCompat.getColor(context, R.color.andesui_white)
}

internal object AndesQuietMessageHierarchy : AndesMessageHierarchyInterface() {
    override fun backgroundColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_quiet_bg) //TODO Check
    override fun backgroundColor(context: Context, state: AndesMessageStateInterface) = backgroundColor(context)
    override fun textColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_quiet_text) //TODO Check
    override fun dismissableIconColor(context: Context) = ContextCompat.getColor(context, R.color.andesui_message_quiet_dismissable)
    override fun iconBackgroundColor(context: Context, state: AndesMessageStateInterface) = state.primaryColor(context)
    override fun secondaryActionTextColor(context: Context, state: AndesMessageStateInterface): Int = iconBackgroundColor(context, state)
}
