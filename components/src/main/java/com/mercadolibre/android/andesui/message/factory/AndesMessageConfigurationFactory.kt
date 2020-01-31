package com.mercadolibre.android.andesui.message.factory

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import com.mercadolibre.android.andesui.R
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
        val secondaryActionText: String?
)

internal object AndesMessageConfigurationFactory {

    fun create(context: Context, andesMessageAttrs: AndesMessageAttrs): AndesMessageConfiguration {
        return with(andesMessageAttrs) {
            AndesMessageConfiguration(
                    iconBackgroundColor = resolveIconBackgroundColor(andesMessageType.state, context),
                    backgroundColor = resolveBackgroundColor(andesMessageHierarchy.hierarchy, andesMessageType.state, context),
                    pipeColor = resolvePipeColor(andesMessageType.state, context),
                    textColor = resolveTextColor(andesMessageHierarchy.hierarchy, context),
                    titleText = title,
                    bodyText = body,
                    titleSize = resolveTitleSize(context),
                    lineHeight = resolveLineHeight(context),
                    bodySize = resolveBodySize(context),
                    titleTypeface = resolveTitleTypeface(andesMessageHierarchy.hierarchy, context),
                    bodyTypeface = resolveBodyTypeface(andesMessageHierarchy.hierarchy, context),
                    icon = resolveIcon(andesMessageType.state, andesMessageHierarchy.hierarchy, context),
                    isDismissable = isDismissable,
                    dismissableIcon = resolveDismissableIcon(andesMessageHierarchy.hierarchy, context),
                    dismissableIconColor = resolveDismissableIconColor(andesMessageHierarchy.hierarchy, context),
                    primaryActionText = null,
                    secondaryActionText = null

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
}
