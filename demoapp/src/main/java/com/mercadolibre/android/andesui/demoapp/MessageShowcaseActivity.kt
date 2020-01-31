package com.mercadolibre.android.andesui.testapp

import android.content.Context
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.demoapp.PageIndicator
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.message.AndesMessage
import com.mercadolibre.android.andesui.message.hierarchy.AndesMessageHierarchy
import com.mercadolibre.android.andesui.message.state.AndesMessageType

class MessageShowcaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_showcase_main)

        val viewPager = findViewById<ViewPager>(R.id.andesui_viewpager)
        viewPager.adapter = AndesShowcasePagerAdapter(this)
        val indicator = findViewById<PageIndicator>(R.id.page_indicator)
        indicator.attach(viewPager)

        val adapter = viewPager.adapter as AndesShowcasePagerAdapter
        addMessage(adapter.views[0])
        addMessage(adapter.views[1])
    }

    private fun addMessage(container: View) {
        val linearLoud = container.findViewById<LinearLayout>(R.id.andesui_message_container)
    }

    class AndesShowcasePagerAdapter(private val context: Context) : PagerAdapter() {

        var views: List<View>

        init {
            views = initViews()
        }

        override fun instantiateItem(container: ViewGroup, position: Int): View {
            container.addView(views[position])
            return views[position]
        }

        override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
            container.removeView(view as View?)
        }

        override fun isViewFromObject(view: View, other: Any): Boolean {
            return view == other
        }

        override fun getCount(): Int = views.size

        private fun initViews(): List<View> {
            val inflater = LayoutInflater.from(context)
            val layoutMessages = inflater.inflate(R.layout.andesui_message_showcase, null, false) as ScrollView
            val layoutMessagesChange = inflater.inflate(R.layout.andesui_message_showcase_change, null, false) as ScrollView
            val button = layoutMessages.findViewById<AndesButton>(R.id.button)

            button.setOnClickListener {
                val message = layoutMessages.findViewById<AndesMessage>(R.id.message_loud)
                val hour = System.currentTimeMillis().toString()
                message.title = ("The current millis are: $hour")
                message.hierarchy = (AndesMessageHierarchy.LOUD)
                message.type = (AndesMessageType.SUCCESS)
                message.isDismissable = false
                message.body = "I insist. Current millis are: $hour"
            }

            val hierarchySpinner: Spinner = layoutMessagesChange.findViewById(R.id.hierarchy_spinner)
            ArrayAdapter.createFromResource(
                    context,
                    R.array.hierarchy_spinner,
                    android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                hierarchySpinner.adapter = adapter
            }

            val typeSpinner: Spinner = layoutMessagesChange.findViewById(R.id.state_spinner)
            ArrayAdapter.createFromResource(
                    context,
                    R.array.state_spinner,
                    android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                typeSpinner.adapter = adapter
            }

            val dismissableCheckbox = layoutMessagesChange.findViewById<CheckBox>(R.id.dismissable_checkbox)

            val bodyText = layoutMessagesChange.findViewById<EditText>(R.id.body_text)

            val titleText = layoutMessagesChange.findViewById<EditText>(R.id.title_text)


            val changeButton = layoutMessagesChange.findViewById<AndesButton>(R.id.change_button)
            val changeMessage = (layoutMessagesChange.getChildAt(0) as LinearLayout).getChildAt(1) as AndesMessage

            changeButton.setOnClickListener {

                changeMessage.isDismissable = dismissableCheckbox.isChecked
                changeMessage.title = titleText.text.toString()
                if (bodyText.text.toString() == "") {
                    Toast.makeText(context, "Body cannot be empty", Toast.LENGTH_SHORT).show()
                }
                else {
                    changeMessage.body = bodyText.text.toString()
                }

                when(typeSpinner.selectedItem.toString()){
                    "Highlight" -> changeMessage.type = AndesMessagetype.HIGHLIGHT
                    "Success" -> changeMessage.type = AndesMessageType.SUCCESS
                    "Warning" -> changeMessage.type = AndesMessageType.WARNING
                    "Error" -> changeMessage.type = AndesMessageType.ERROR
                }

                when(hierarchySpinner.selectedItem.toString()){
                    "Loud" -> changeMessage.hierarchy = AndesMessageHierarchy.LOUD
                    "Quiet" -> changeMessage.hierarchy = AndesMessageHierarchy.QUIET
                }

            }




        return listOf<View>(layoutMessages, layoutMessagesChange)
        }
    }
}
