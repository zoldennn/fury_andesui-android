package com.mercadolibre.android.andesui.message

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.mercadolibre.android.andesui.R
import com.mercadolibre.android.andesui.message.factory.AndesMessageConfiguration
import com.mercadolibre.android.andesui.message.factory.AndesMessageFactory
import com.mercadolibre.android.andesui.message.hierarchy.AndesMessageHierarchy
import com.mercadolibre.android.andesui.message.state.AndesMessageState

class AndesMessage : FrameLayout {

    companion object {
        private val HIERARCHY_DEFAULT = AndesMessageHierarchy.LOUD
        private val STATE_DEFAULT = AndesMessageState.HIGHLIGHT
        private val BODY_DEFAULT = "Andes Message Body"
        private val TITLE_DEFAULT = null
        private const val IS_DISMISSABLE_DEFAULT = false
    }

    private lateinit var messageContainer: ConstraintLayout
    private lateinit var titleComponent: TextView
    private lateinit var bodyComponent: TextView
    private lateinit var iconComponent: ImageView
    private lateinit var dismissableComponent: ImageView
    private lateinit var pipeComponent: View
    private lateinit var config: AndesMessageConfiguration

    constructor(context: Context) : super(context) {
        initAttrs(HIERARCHY_DEFAULT, STATE_DEFAULT, BODY_DEFAULT, TITLE_DEFAULT, IS_DISMISSABLE_DEFAULT)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context) {
        initAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context) {
        initAttrs(attrs)
    }

    constructor(context: Context, andesMessageHierarchy: AndesMessageHierarchy, andesMessageState: AndesMessageState, body: String, title: String? = null, isDismissable: Boolean = IS_DISMISSABLE_DEFAULT) : super(context) {
        initAttrs(andesMessageHierarchy, andesMessageState, body, title, isDismissable)
    }

    /**
     * Sets the proper [config] for this message based on the [attrs] received via XML.
     *
     * @param attrs attributes from the XML.
     */
    private fun initAttrs(attrs: AttributeSet?) {
        config = AndesMessageFactory.create(context, attrs)
        setupComponents()
    }


    private fun initAttrs(andesMessageHierarchy: AndesMessageHierarchy, andesMessageState: AndesMessageState, body: String, title: String?, andesMessageIsDismissable: Boolean) {
        config = AndesMessageFactory.create(context, andesMessageHierarchy, andesMessageState, body, title, andesMessageIsDismissable)
        setupComponents()
    }

    /**
     * Responsible for setting up all properties of each component that is part of this button.
     * Is like a choreographer ;)
     *
     */
    private fun setupComponents() {
        initComponents()
        setupViewId()

        setupTitleComponent()

        setupBodyComponent()

        setupBackground()

        setupPipe()

        setupIcon()

        setupDismissable()
    }


    /**
     * Creates all the views that are part of this message.
     * After a view is created then a view id is added to it.
     *
     */
    private fun initComponents() {
        val container = LayoutInflater.from(context).inflate(R.layout.andesui_layout_message, this, true)

        messageContainer = container.findViewById(R.id.andesui_message_container)
        titleComponent = container.findViewById(R.id.andesui_title)
        bodyComponent = container.findViewById(R.id.andesui_body)
        iconComponent = container.findViewById(R.id.andesui_icon)
        dismissableComponent = container.findViewById(R.id.andesui_dismissable)
        pipeComponent = container.findViewById(R.id.andesui_pipe)
    }

    /**
     * Sets a view id to this message.
     *
     */
    private fun setupViewId() {
        if (id == NO_ID) { //If this view has no id
            id = View.generateViewId()
        }
    }

    /**
     * Gets data from the config and sets to the text component of this button.
     *
     */
    private fun setupTitleComponent() {
        titleComponent.text = config.titleText
        if(titleComponent.text == "" || titleComponent.text == null) {
            titleComponent.visibility = View.GONE
        }
        titleComponent.setTextSize(TypedValue.COMPLEX_UNIT_PX, config.titleSize)
        titleComponent.setTextColor(config.textColor)
        titleComponent.typeface = config.titleTypeface
    }

    /**
     * Gets data from the config and sets to the text component of this button.
     *
     */
    private fun setupBodyComponent() {
        bodyComponent.text = config.bodyText
        bodyComponent.setTextSize(TypedValue.COMPLEX_UNIT_PX, config.bodySize)
        bodyComponent.setTextColor(config.textColor)
        bodyComponent.typeface = config.bodyTypeface
//        bodyComponent.lineHeight = config.lineHeight //FIXME Use TextViewCompat
    }

    private fun setupBackground() {
        messageContainer.setBackgroundColor(config.backgroundColor)
    }

    private fun setupPipe() {
        pipeComponent.setBackgroundColor(config.pipeColor)
    }

    private fun setupIcon() {
        iconComponent.setImageDrawable(config.icon)
        dismissableComponent.setImageDrawable(config.dismissableIcon)
    }

    private fun setupDismissable() {
        if (config.isDismissable) {
            dismissableComponent.visibility = View.VISIBLE
            dismissableComponent.setOnClickListener {
                visibility = View.GONE
            }
        } else {
            dismissableComponent.visibility = View.GONE
        }
    }

    fun setTitle(string: String) {
        titleComponent.text = string
    }

    fun setBody(string: String) {
        bodyComponent.text = string
    }
}