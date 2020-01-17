package com.mercadolibre.android.andesui.button

import android.os.Build
import android.support.v4.content.ContextCompat
import com.mercadolibre.android.andesui.BuildConfig
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.button.size.AndesButtonSize
import com.mercadolibre.android.andesui.button.hierarchy.AndesButtonHierarchy
import com.mercadolibre.android.andesui.typeface.getFontOrDefault
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import android.support.constraint.ConstraintLayout
import com.mercadolibre.android.andesui.button.hierarchy.AndesButtonIcon
import com.mercadolibre.android.andesui.button.hierarchy.AndesButtonIconOrientation


@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [Build.VERSION_CODES.LOLLIPOP])
class AndesButtonTest {
    private var context = RuntimeEnvironment.application
    private lateinit var andesButton: AndesButton

    @Test
    fun `Only context constructor`() {
        andesButton = AndesButton(context)
        val textParams = andesButton.textComponent.layoutParams as ConstraintLayout.LayoutParams

        assertEquals(andesButton.textComponent.textSize, 16F)
        assertEquals(textParams.goneStartMargin, 8)
        assertEquals(textParams.goneEndMargin, 8)
        assertEquals(andesButton.paddingRight, 16)
        assertEquals(andesButton.paddingLeft, 16)
        assertEquals(andesButton.textComponent.typeface, context.getFontOrDefault(R.font.andesui_font_semibold))
        assertNull(andesButton.leftIconComponent.drawable)
        assertNull(andesButton.rightIconComponent.drawable)
    }

    @Test
    fun `Hierarchy and size constructor`() {
        andesButton = AndesButton(context, AndesButtonSize.MEDIUM, AndesButtonHierarchy.QUIET)
        val textParams = andesButton.textComponent.layoutParams as ConstraintLayout.LayoutParams

        assertEquals(andesButton.textComponent.textSize, 14F)
        assertEquals(textParams.goneStartMargin, 0)
        assertEquals(textParams.goneEndMargin, 0)
        assertEquals(andesButton.paddingRight, 12)
        assertEquals(andesButton.paddingLeft, 12)
        assertEquals(andesButton.textComponent.typeface, context.getFontOrDefault(R.font.andesui_font_semibold))
        assertNull(andesButton.leftIconComponent.drawable)
        assertNull(andesButton.rightIconComponent.drawable)
    }

    @Test
    fun `Hierarchy, size and icon constructor`() {
        val icon = AndesButtonIcon(ContextCompat.getDrawable(context, R.drawable.andesui_icon), AndesButtonIconOrientation.LEFT)
        andesButton = AndesButton(context, AndesButtonSize.LARGE, AndesButtonHierarchy.TRANSPARENT, icon)
        val textParams = andesButton.textComponent.layoutParams as ConstraintLayout.LayoutParams
        val leftIconParams = andesButton.leftIconComponent.layoutParams as ConstraintLayout.LayoutParams

        assertEquals(andesButton.textComponent.textSize, 16F)
        assertEquals(leftIconParams.marginStart, 0)
        assertEquals(textParams.marginStart, 12)
        assertEquals(textParams.goneEndMargin, 8)
        assertEquals(andesButton.paddingRight, 16)
        assertEquals(andesButton.paddingLeft, 16)
        assertEquals(andesButton.textComponent.typeface, context.getFontOrDefault(R.font.andesui_font_semibold))
        assertThat(andesButton.leftIconComponent.drawable).isEqualToComparingOnlyGivenFields(icon)
        assertNull(andesButton.rightIconComponent.drawable)
    }

    @Test
    fun `Hierarchy, size and right icon constructor`() {
        val icon = AndesButtonIcon(ContextCompat.getDrawable(context, R.drawable.andesui_icon), AndesButtonIconOrientation.RIGHT)
        andesButton = AndesButton(context, AndesButtonSize.LARGE, AndesButtonHierarchy.TRANSPARENT, icon)
        val textParams = andesButton.textComponent.layoutParams as ConstraintLayout.LayoutParams
        val rightIconParams = andesButton.rightIconComponent.layoutParams as ConstraintLayout.LayoutParams

        assertEquals(andesButton.textComponent.textSize, 16F)
        assertEquals(textParams.marginEnd, 12)
        assertEquals(rightIconParams.marginEnd, 0)
        assertEquals(textParams.goneStartMargin, 8)
        assertEquals(andesButton.paddingRight, 16)
        assertEquals(andesButton.paddingLeft, 16)
        assertEquals(andesButton.textComponent.typeface, context.getFontOrDefault(R.font.andesui_font_semibold))
        assertNull(andesButton.leftIconComponent.drawable)
        assertThat(andesButton.rightIconComponent.drawable).isEqualToComparingOnlyGivenFields(icon)
    }
}