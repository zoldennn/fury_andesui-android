package com.mercadolibre.android.andesui.demoapp

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex

/**
 * Main Application class that extends from Application to execute the start method only once.
 */
class MainApplication : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

        // No need for productFlavors, as proguard will remove all multidex related code in non-debug builds.
        if (BuildConfig.BUILD_TYPE.contentEquals("debug")) {
            MultiDex.install(this)
        }
    }
}