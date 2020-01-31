package com.mercadolibre.android.andesui.message.factory

import android.content.Context
import android.util.AttributeSet
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.message.hierarchy.AndesMessageHierarchy
import com.mercadolibre.android.andesui.message.state.AndesMessageType

/**
 * The data class that contains the public components of the message.
 */
internal data class AndesMessageAttrs(val andesMessageHierarchy: AndesMessageHierarchy,
                                      val andesMessageType: AndesMessageType,
                                      val body: String,
                                      val title: String?,
                                      val isDismissable: Boolean)

/**
 * This object parse the attribute set and return an instance of AndesMessageAttrs to be used by AndesMessage
 */
internal object AndesMessageAttrsParser {

    private const val ANDES_MESSAGE_HIERARCHY_LOUD = "1000"
    private const val ANDES_MESSAGE_HIERARCHY_QUIET = "1001"

    private const val ANDES_MESSAGE_STATE_NEUTRAL = "2000"
    private const val ANDES_MESSAGE_STATE_SUCCESS = "2001"
    private const val ANDES_MESSAGE_STATE_WARNING = "2002"
    private const val ANDES_MESSAGE_STATE_ERROR = "2003"

    fun parse(context: Context, attr: AttributeSet?): AndesMessageAttrs {
        val typedArray = context.obtainStyledAttributes(attr, R.styleable.AndesMessage)

        val hierarchy = when (typedArray.getString(R.styleable.AndesMessage_andesMessageHierarchy)) {
            ANDES_MESSAGE_HIERARCHY_LOUD -> AndesMessageHierarchy.LOUD
            ANDES_MESSAGE_HIERARCHY_QUIET -> AndesMessageHierarchy.QUIET
            else -> AndesMessageHierarchy.LOUD
        }

        val state = when (typedArray.getString(R.styleable.AndesMessage_andesMessageType)) {
            ANDES_MESSAGE_STATE_NEUTRAL -> AndesMessageType.NEUTRAL
            ANDES_MESSAGE_STATE_SUCCESS -> AndesMessageType.SUCCESS
            ANDES_MESSAGE_STATE_WARNING -> AndesMessageType.WARNING
            ANDES_MESSAGE_STATE_ERROR -> AndesMessageType.ERROR
            else -> AndesMessageType.NEUTRAL
        }

        return AndesMessageAttrs(
                andesMessageHierarchy = hierarchy,
                andesMessageType = state,
                body = typedArray.getString(R.styleable.AndesMessage_andesMessageBodyText) ?: throw IllegalArgumentException("Body is a mandatory parameter"),
                title = typedArray.getString(R.styleable.AndesMessage_andesMessageTitleText),
                isDismissable = typedArray.getBoolean(R.styleable.AndesMessage_andesMessageDismissable, false)
        ).also { typedArray.recycle() }
    }
}