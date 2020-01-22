package com.mercadolibre.android.andesui.message.state

import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape

internal fun getConfiguredBackgroundState(cornerRadius: Float, color: Int): Drawable {
    val contentOuterRadii = floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius)
    val buttonShape = ShapeDrawable(RoundRectShape(contentOuterRadii, null, null))
    buttonShape.paint.color = color

    return buttonShape


    //TODO Unused
}