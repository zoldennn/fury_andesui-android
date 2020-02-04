package com.mercadolibre.android.andesui.message.factory

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.button.hierarchy.BackgroundColorConfigMessage
import com.mercadolibre.android.andesui.message.hierarchy.AndesMessageHierarchyInterface
import com.mercadolibre.android.andesui.message.type.AndesMessageTypeInterface

internal data class AndesMessageConfiguration(
        val iconBackgroundColor: Int,
        val backgroundColor: Int,
        val pipeColor: Int,
        val textColor: Int,
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
        val dismissableIconColor: Int?,
        val primaryActionText: String?,
        val secondaryActionText: String?,
        val primaryActionBackgroundColor: BackgroundColorConfigMessage,
        val primaryActionTextColor: Int,
        val secondaryActionBackgroundColor: BackgroundColorConfigMessage,
        val secondaryActionTextColor: Int
)

internal object AndesMessageConfigurationFactory {

    fun create(context: Context, andesMessageAttrs: AndesMessageAttrs): AndesMessageConfiguration {
        return with(andesMessageAttrs) {
            AndesMessageConfiguration(
                    iconBackgroundColor = resolveIconBackgroundColor(andesMessageType.type, context),
                    backgroundColor = resolveBackgroundColor(andesMessageHierarchy.hierarchy, andesMessageType.type, context),
                    pipeColor = resolvePipeColor(andesMessageType.type, context),
                    textColor = resolveTextColor(andesMessageHierarchy.hierarchy, context),
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
                    dismissableIconColor = resolveDismissableIconColor(andesMessageHierarchy.hierarchy, context),
                    primaryActionText = null,
                    secondaryActionText = null,
                    primaryActionBackgroundColor = resolvePrimaryActionBackgroundColor(andesMessageHierarchy.hierarchy, andesMessageType.type, context),
                    primaryActionTextColor = resolvePrimaryActionTextColor(andesMessageHierarchy.hierarchy, context),
                    secondaryActionBackgroundColor = resolveSecondaryActionBackgroundColor(andesMessageHierarchy.hierarchy, andesMessageType.type, context),
                    secondaryActionTextColor = resolveSecondaryActionTextColor(andesMessageHierarchy.hierarchy, andesMessageType.type, context)
            )
        }
    }

    private fun resolveIconBackgroundColor(type: AndesMessageTypeInterface, context: Context) = type.iconBackgroundColor(context)
    private fun resolveBackgroundColor(hierarchy: AndesMessageHierarchyInterface, type: AndesMessageTypeInterface, context: Context) = hierarchy.backgroundColor(context, type)
    private fun resolvePipeColor(type: AndesMessageTypeInterface, context: Context) = type.pipeColor(context)
    private fun resolveTextColor(hierarchy: AndesMessageHierarchyInterface, context: Context) = hierarchy.textColor(context)
    private fun resolveTitleSize(context: Context) = context.resources.getDimension(R.dimen.andesui_message_title)
    private fun resolveBodySize(context: Context) = context.resources.getDimension(R.dimen.andesui_message_body)
    private fun resolveLineHeight(context: Context) = context.resources.getDimension(R.dimen.andesui_message_line_height).toInt()
    private fun resolveTitleTypeface(hierarchy: AndesMessageHierarchyInterface, context: Context) = hierarchy.titleTypeface(context)
    private fun resolveBodyTypeface(hierarchy: AndesMessageHierarchyInterface, context: Context) = hierarchy.bodyTypeface(context)
    private fun resolveIcon(type: AndesMessageTypeInterface, hierarchy: AndesMessageHierarchyInterface, context: Context) = type.icon(context, hierarchy)
    private fun resolveDismissableIcon(hierarchy: AndesMessageHierarchyInterface, context: Context) = hierarchy.dismissableIcon(hierarchy, context)
    private fun resolveDismissableIconColor(hierarchy: AndesMessageHierarchyInterface, context: Context) = hierarchy.dismissableIconColor(context)
    private fun resolvePrimaryActionBackgroundColor(hierarchy: AndesMessageHierarchyInterface, type: AndesMessageTypeInterface, context: Context) = hierarchy.primaryActionBackgroundColor(context, type)
    private fun resolvePrimaryActionTextColor(hierarchy: AndesMessageHierarchyInterface, context: Context) = hierarchy.primaryActionTextColor(context)
    private fun resolveSecondaryActionBackgroundColor(hierarchy: AndesMessageHierarchyInterface, type: AndesMessageTypeInterface, context: Context) = hierarchy.secondaryActionBackgroundColor(context, type)
    private fun resolveSecondaryActionTextColor(hierarchy: AndesMessageHierarchyInterface, type: AndesMessageTypeInterface, context: Context) = hierarchy.secondaryActionTextColor(context, type)
}
