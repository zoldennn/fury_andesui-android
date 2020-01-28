package com.mercadolibre.android.andesui.testapp

import android.content.Context
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import com.mercadolibre.android.andesui.button.AndesButton
import com.mercadolibre.android.andesui.demoapp.PageIndicator
import com.mercadolibre.android.andesui.demoapp.R
import com.mercadolibre.android.andesui.message.AndesMessage
import com.mercadolibre.android.andesui.message.hierarchy.AndesMessageHierarchy
import com.mercadolibre.android.andesui.message.state.AndesMessageState

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
    }

    private fun addMessage(container: View) {
        //TODO Create a Message view

        val linearLoud = container.findViewById<LinearLayout>(R.id.andes_loud_container)

        //TODO Add messages to the linear layout
        //linearLoud.addView(andesMessage)
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
            val button = layoutMessages.findViewById<AndesButton>(R.id.button)

            button.setOnClickListener {
                val message = (layoutMessages.getChildAt(0) as LinearLayout).getChildAt(2) as AndesMessage
                message.title =("Soy un titulo muy largo y hago muchas cosas como setear la hora " +  System.currentTimeMillis())
                message.hierarchy = (AndesMessageHierarchy.LOUD)
                message.state = (AndesMessageState.SUCCESS)
                message.isDismissable = false
                message.body = "cambie mi body"
            }

            return listOf<View>(layoutMessages)
        }
    }
}
