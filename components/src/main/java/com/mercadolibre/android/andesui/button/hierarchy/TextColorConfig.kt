package com.mercadolibre.android.andesui.button.hierarchy

import android.content.Context
import android.content.res.ColorStateList
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.button.AndesButton

/**
 * Holds the colors needed for the different text states.
 *
 * @property enabledColor
 * @property disabledColor
 */
data class TextColorConfig(
        @ColorRes val enabledColor: Int,
        @ColorRes val disabledColor: Int
)

/**
 * Returns a [ColorStateList] ready to be used as the text color of an [AndesButton].
 *
 * @param context needed for accessing some resources.
 * @param textColorConfig config colors. This changes based on the button style.
 */
internal fun getConfiguredTextColor(context: Context, textColorConfig: TextColorConfig) = ColorStateList(arrayOf(
        intArrayOf(-android.R.attr.state_enabled),  // Disabled
        intArrayOf(android.R.attr.state_enabled)    // Enabled
),
        intArrayOf(
                ContextCompat.getColor(context, textColorConfig.disabledColor),     // The color for the Disabled type
                ContextCompat.getColor(context, textColorConfig.enabledColor)        // The color for the Enabled type
        )
)

/**
 * Returns the proper [TextColorConfig] for the Loud Hierarchy button.
 *
 */
internal fun createTextColorConfigLoud() =
        TextColorConfig(R.color.andesui_button_loud_text, R.color.andesui_button_loud_text_disabled)

/**
 * Returns the proper [TextColorConfig] for the Quiet Hierarchy button.
 *
 */
internal fun createTextColorConfigQuiet() =
        TextColorConfig(R.color.andesui_button_quiet_text, R.color.andesui_button_quiet_text_disabled)

/**
 * Returns the proper [TextColorConfig] for the Transparent Hierarchy button.
 *
 */
internal fun createTextColorConfigTransparent() =
        TextColorConfig(R.color.andesui_button_transparent_text, R.color.andesui_button_transparent_text_disabled)