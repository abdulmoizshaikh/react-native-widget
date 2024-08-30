package com.videocallwidget

import android.content.Intent
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class WidgetUpdateModule(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "WidgetUpdate"
    }

    @ReactMethod
    fun sendBroadcast(action: String) {
        val intent = Intent(action)
        reactContext.sendBroadcast(intent)
    }
}
