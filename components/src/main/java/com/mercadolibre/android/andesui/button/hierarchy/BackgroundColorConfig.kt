package com.mercadolibre.android.andesui.button.hierarchy

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.graphics.drawable.shapes.Shape
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.color.AndesColor

/**
 * Useful class that holds all the possible colors for the [AndesButton] background.
 *
 * @property enabledColor
 * @property pressedColor
 * @property focusedColor
 * @property hoveredColor
 * @property disabledColor
 * @property otherColor
 */
data class BackgroundColorConfig(
        @ColorRes val enabledColor: Int,
        @ColorRes val pressedColor: Int,
        @ColorRes val focusedColor: Int,
        @ColorRes val hoveredColor: Int,
        @ColorRes val disabledColor: Int,
        @ColorRes val otherColor: Int?
)


internal data class BackgroundColorConfigMessage(
        val enabledColor: AndesColor,
        val pressedColor: AndesColor,
        val focusedColor: AndesColor,
        val hoveredColor: AndesColor,
        val disabledColor: AndesColor,
        val otherColor: AndesColor?
)

/**
 * Returns a [Drawable] ready to be used as the background of an [AndesButton]. Output is based on the received [colorConfig].
 *
 * @param context needed for accessing some resources.
 * @param cornerRadius radius of the button corner. This is needed because it depends on the button size.
 * @param colorConfig config that contains the specification of each required color.
 * @return
 */
internal fun getConfiguredBackground(context: Context, cornerRadius: Float, colorConfig: BackgroundColorConfig): Drawable {
    val contentOuterRadii = floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius)
    val buttonShape = RoundRectShape(contentOuterRadii, null, null)

    val pressedShape = ShapeDrawable(buttonShape)
    pressedShape.paint.color = ContextCompat.getColor(context, colorConfig.pressedColor)

    val enabledShape = ShapeDrawable(buttonShape)
    enabledShape.paint.color = ContextCompat.getColor(context, colorConfig.enabledColor)

    val disabledShape = ShapeDrawable(buttonShape)
    disabledShape.paint.color = ContextCompat.getColor(context, colorConfig.disabledColor)

    val hoveredShape = ShapeDrawable(buttonShape)
    hoveredShape.paint.color = ContextCompat.getColor(context, colorConfig.hoveredColor)

    val focusedShape = ShapeDrawable(buttonShape)
    focusedShape.paint.color = ContextCompat.getColor(context, colorConfig.focusedColor)

    if (colorConfig.otherColor != null) {
        val otherShape = ShapeDrawable(buttonShape)
        otherShape.paint.color = ContextCompat.getColor(context, colorConfig.otherColor)
    }

    val colorState = StateListDrawable()
    colorState.addState(intArrayOf(android.R.attr.state_pressed), pressedShape)
    colorState.addState(intArrayOf(android.R.attr.state_enabled), enabledShape)
    colorState.addState(intArrayOf(-android.R.attr.state_enabled), disabledShape)
    colorState.addState(intArrayOf(android.R.attr.state_hovered), hoveredShape)
    colorState.addState(intArrayOf(android.R.attr.state_focused), focusedShape)

    return colorState
}

internal fun getConfiguredBackgroundMessage(context: Context, cornerRadius: Float, colorConfig: BackgroundColorConfigMessage): Drawable {
    val buttonShape = RoundRectShape(getOuterRadii(cornerRadius), null, null)
    return StateListDrawable().apply {
        addState(intArrayOf(android.R.attr.state_pressed), createShapeDrawable(context, buttonShape, colorConfig.pressedColor))
        addState(intArrayOf(android.R.attr.state_enabled), createShapeDrawable(context, buttonShape, colorConfig.enabledColor))
        addState(intArrayOf(-android.R.attr.state_enabled), createShapeDrawable(context, buttonShape, colorConfig.disabledColor))
        addState(intArrayOf(android.R.attr.state_hovered), createShapeDrawable(context, buttonShape, colorConfig.hoveredColor))
        addState(intArrayOf(android.R.attr.state_focused), createShapeDrawable(context, buttonShape, colorConfig.focusedColor))
    }
}

private fun getOuterRadii(cornerRadius: Float) =
        floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius)

private fun createShapeDrawable(context: Context, shape: Shape, color: AndesColor) =
        ShapeDrawable(shape).apply { paint.color = color.colorInt(context) }

/**
 * Returns the proper [BackgroundColorConfig] for the Loud Hierarchy button.
 *
 */
internal fun createBackgroundColorConfigLoud() =
        BackgroundColorConfig(R.color.andesui_button_loud_bg, R.color.andesui_button_loud_bg_pressed, R.color.andesui_button_loud_bg_focused, R.color.andesui_button_loud_bg_hovered, R.color.andesui_button_loud_bg_disabled, R.color.andesui_button_loud_bg_other)

/**
 * Returns the proper [BackgroundColorConfig] for the Quiet Hierarchy button.
 *
 */
internal fun createBackgroundColorConfigQuiet() =
        BackgroundColorConfig(R.color.andesui_button_quiet_bg, R.color.andesui_button_quiet_bg_pressed, R.color.andesui_button_quiet_bg_focused, R.color.andesui_button_quiet_bg_hovered, R.color.andesui_button_quiet_bg_disabled, R.color.andesui_button_quiet_bg_other)

/**
 * Returns the proper [BackgroundColorConfig] for the Transparent Hierarchy button.
 *
 */
internal fun createBackgroundColorConfigTransparent() =
        BackgroundColorConfig(R.color.andesui_button_transparent_bg, R.color.andesui_button_transparent_bg_pressed, R.color.andesui_button_transparent_bg_focused, R.color.andesui_button_transparent_bg_hovered, R.color.andesui_button_transparent_bg_disabled, null)
