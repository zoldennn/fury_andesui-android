package com.mercadolibre.android.andesui.button.hierarchy

import android.graphics.drawable.Drawable

/**
 * Holds data about the icon a button can carry: Icon and Orientation.
 *
 * @property icon the icon itself
 * @property orientation an [AndesButtonIconOrientation] that can be [AndesButtonIconOrientation.LEFT] or [AndesButtonIconOrientation.RIGHT]
 */
class AndesButtonIcon(val icon: Drawable?, private val orientation: AndesButtonIconOrientation) {
    internal val leftIcon get() = getLeftIcon()
    internal val rightIcon get() = getRightIcon()

    /**
     * Based on the data this object has, decides if it has left icon or not.
     *
     * @return the left icon if it exists, null otherwise.
     */
    private fun getLeftIcon(): Drawable? {
        return when {
            icon != null && orientation == AndesButtonIconOrientation.LEFT -> {
                icon
            }
            else -> null
        }
    }

    /**
     * Based on the data this object has, decides if it has right icon or not.
     *
     * @return the right icon if it exists, null otherwise.
     */
    private fun getRightIcon(): Drawable? {
        return when {
            icon != null && orientation == AndesButtonIconOrientation.RIGHT -> {
                icon
            }
            else -> null
        }
    }
}
