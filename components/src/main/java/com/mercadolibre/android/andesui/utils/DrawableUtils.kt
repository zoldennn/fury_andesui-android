package com.mercadolibre.android.andesui.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable





/**
 * Receives a [BitmapDrawable] which will suffer some look overhauling that includes scaling and tinting.
 * When the polishing ends, it will return a new [BitmapDrawable].
 * Size of the icon is based on Andes Specification.
 *
 * @param image image of the icon. Ok: The icon.
 * @param context needed for accessing some resources like size, you know.
 * @param color we said we will be tinting the icon and this is the color.
 * @return a complete look overhauled [BitmapDrawable].
 */
internal fun buildScaledColoredBitmapDrawable(image: BitmapDrawable, context: Context, dstWidth: Int, dstHeight: Int, color: Int? = null): BitmapDrawable {
    val scaledBitmap = Bitmap.createScaledBitmap(
            image.bitmap,
            dstWidth,
            dstHeight,
            true)
    return BitmapDrawable(context.resources, scaledBitmap)
            .apply {
                color?.let { setColorFilter(it, PorterDuff.Mode.SRC_IN) }
            }
}

internal fun buildColoredBitmapDrawable(image: BitmapDrawable, context: Context, color: Int? = null): BitmapDrawable {
    return BitmapDrawable(context.resources, image.bitmap)
            .apply {
                color?.let { setColorFilter(it, PorterDuff.Mode.SRC_IN) }
            }
}

internal fun buildColoredCircularShapeWithIconDrawable(image: BitmapDrawable, context: Context, iconColor: Int? = null, shapeColor: Int? = null, diameter : Int): Drawable {
    val icon = BitmapDrawable(context.resources, image.bitmap)
            .apply {
                iconColor?.let { setColorFilter(it, PorterDuff.Mode.SRC_IN) }
            }
    val biggerCircle = ShapeDrawable(OvalShape())
    val size = diameter
    biggerCircle.intrinsicHeight = size
    biggerCircle.intrinsicWidth = size
    biggerCircle.paint.color = shapeColor!!
    biggerCircle.bounds = Rect(0, 0, size, size)
    return LayerDrawable(arrayOf(biggerCircle, icon ))
    //FIXME Refactor this method


}