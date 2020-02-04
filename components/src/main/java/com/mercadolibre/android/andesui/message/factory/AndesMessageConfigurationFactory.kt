package com.mercadolibre.android.andesui.message.factory

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.button.hierarchy.BackgroundColorConfigMessage
import com.mercadolibre.android.andesui.color.AndesColor
import com.mercadolibre.android.andesui.message.hierarchy.AndesMessageHierarchyInterface
import com.mercadolibre.android.andesui.message.type.AndesMessageTypeInterface

internal data class AndesMessageConfiguration(
        val iconBackgroundColor: AndesColor,
        val backgroundColor: AndesColor,
        val pipeColor: AndesColor,
        val textColor: AndesColor,
        val titleText: String? = null,
        val bodyText: String? = null,
        val titleSize: Float,
        val bodySize: Float,
        val lineHeight: Int,
        val titleTypeface: Typeface?,
        val bodyTypeface: Typeface?,
        val icon: Drawable?,
        val isDismissable: Boolean,
        val dismissableIcon: Drawable?,
        val dismissableIconColor: AndesColor?,
        val primaryActionText: String?,
        val secondaryActionText: String?,
        val primaryActionBackgroundColor: BackgroundColorConfigMessage,
        val primaryActionTextColor: AndesColor,
        val secondaryActionBackgroundColor: BackgroundColorConfigMessage,
        val secondaryActionTextColor: AndesColor
)

internal object AndesMessageConfigurationFactory {

    fun create(context: Context, andesMessageAttrs: AndesMessageAttrs): AndesMessageConfiguration {
        return with(andesMessageAttrs) {
            AndesMessageConfiguration(
                    iconBackgroundColor = resolveIconBackgroundColor(andesMessageType.type),
                    backgroundColor = resolveBackgroundColor(andesMessageHierarchy.hierarchy, andesMessageType.type),
                    pipeColor = resolvePipeColor(andesMessageType.type),
                    textColor = resolveTextColor(andesMessageHierarchy.hierarchy),
                    titleText = title,
                    bodyText = body,
                    titleSize = resolveTitleSize(context),
                    lineHeight = resolveLineHeight(context),
                    bodySize = resolveBodySize(context),
                    titleTypeface = resolveTitleTypeface(andesMessageHierarchy.hierarchy, context),
                    bodyTypeface = resolveBodyTypeface(andesMessageHierarchy.hierarchy, context),
                    icon = resolveIcon(andesMessageType.type, andesMessageHierarchy.hierarchy, context),
                    isDismissable = isDismissable,
                    dismissableIcon = resolveDismissableIcon(andesMessageHierarchy.hierarchy, context),
                    dismissableIconColor = resolveDismissableIconColor(andesMessageHierarchy.hierarchy),
                    primaryActionText = null,
                    secondaryActionText = null,
                    primaryActionBackgroundColor = resolvePrimaryActionBackgroundColor(andesMessageHierarchy.hierarchy, andesMessageType.type, context),
                    primaryActionTextColor = resolvePrimaryActionTextColor(andesMessageHierarchy.hierarchy),
                    secondaryActionBackgroundColor = resolveSecondaryActionBackgroundColor(andesMessageHierarchy.hierarchy, andesMessageType.type, context),
                    secondaryActionTextColor = resolveSecondaryActionTextColor(andesMessageHierarchy.hierarchy, andesMessageType.type, context)
            )
        }
    }

    private fun resolveIconBackgroundColor(type: AndesMessageTypeInterface) = type.iconBackgroundColor()
    private fun resolveBackgroundColor(hierarchy: AndesMessageHierarchyInterface, type: AndesMessageTypeInterface) = hierarchy.backgroundColor(type)
    private fun resolvePipeColor(type: AndesMessageTypeInterface) = type.pipeColor()
    private fun resolveTextColor(hierarchy: AndesMessageHierarchyInterface) = hierarchy.textColor()
    private fun resolveTitleSize(context: Context) = context.resources.getDimension(R.dimen.andesui_message_title)
    private fun resolveBodySize(context: Context) = context.resources.getDimension(R.dimen.andesui_message_body)
    private fun resolveLineHeight(context: Context) = context.resources.getDimension(R.dimen.andesui_message_line_height).toInt()
    private fun resolveTitleTypeface(hierarchy: AndesMessageHierarchyInterface, context: Context) = hierarchy.titleTypeface(context)
    private fun resolveBodyTypeface(hierarchy: AndesMessageHierarchyInterface, context: Context) = hierarchy.bodyTypeface(context)
    private fun resolveIcon(type: AndesMessageTypeInterface, hierarchy: AndesMessageHierarchyInterface, context: Context) = type.icon(context, hierarchy)
    private fun resolveDismissableIcon(hierarchy: AndesMessageHierarchyInterface, context: Context) = hierarchy.dismissableIcon(hierarchy, context)
    private fun resolveDismissableIconColor(hierarchy: AndesMessageHierarchyInterface) = hierarchy.dismissableIconColor()
    private fun resolvePrimaryActionBackgroundColor(hierarchy: AndesMessageHierarchyInterface, state: AndesMessageTypeInterface, context: Context) = hierarchy.primaryActionBackgroundColor(state)
    private fun resolvePrimaryActionTextColor(hierarchy: AndesMessageHierarchyInterface) = hierarchy.primaryActionTextColor()
    private fun resolveSecondaryActionBackgroundColor(hierarchy: AndesMessageHierarchyInterface, state: AndesMessageTypeInterface, context: Context) = hierarchy.secondaryActionBackgroundColor(state)
    private fun resolveSecondaryActionTextColor(hierarchy: AndesMessageHierarchyInterface, state: AndesMessageTypeInterface, context: Context) = hierarchy.secondaryActionTextColor(state)

}
