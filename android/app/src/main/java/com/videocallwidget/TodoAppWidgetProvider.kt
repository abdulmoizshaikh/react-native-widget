package com.videocallwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.google.gson.Gson

class TodoAppWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        val preferences = context.getSharedPreferences("com.videocallwidget.preferences", Context.MODE_PRIVATE)
        val todosJson = preferences.getString("todos", "[]")

        // Convert JSON string to list of todos
        val gson = Gson()
        val todosArray: Array<String> = gson.fromJson(todosJson, Array<String>::class.java)

        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.todo_app_widget)
            // Update widget view with todos
            views.setTextViewText(R.id.todo_text, todosArray.joinToString("\n"))

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if ("com.videocallwidget.UPDATE_WIDGET" == intent.action) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val thisAppWidget = ComponentName(context.packageName, TodoAppWidgetProvider::class.java.name)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget)
            onUpdate(context, appWidgetManager, appWidgetIds)
        }
    }
}
