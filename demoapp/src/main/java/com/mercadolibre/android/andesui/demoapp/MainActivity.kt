package com.mercadolibre.android.andesui.demoapp

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.andesui_demoapp_main.*

/**
 * Main activity class
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andesui_demoapp_main)

        setupButtons()
        setupMessages()
    }

    private fun setupButtons() {
        andesui_buttons.setOnClickListener {
            launchAndesButtonShowcase()
        }
    }

    private fun setupMessages() {
        andesui_messages.setOnClickListener {
            launchAndesMessageShowcase()
        }
    }

    private fun launchAndesButtonShowcase() {
        launchIntent("meli://andes/button")
    }

    private fun launchAndesMessageShowcase() {
        launchIntent("meli://andes/message")
    }

    private fun launchIntent(uri: String) {
        val launchIntent = Intent(ACTION_VIEW, Uri.parse(uri))
        startActivity(launchIntent)
    }
}