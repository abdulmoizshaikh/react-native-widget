package com.videocallwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;


public class TodoAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName thisAppWidget = new ComponentName(context.getPackageName(), TodoAppWidgetProvider.class.getName());
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);
            onUpdate(context, appWidgetManager, appWidgetIds);
        }
    }
    
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String todosJson = preferences.getString("todos", "[]");

        // Convert JSON string to list of todos
        Gson gson = new Gson();
        String[] todosArray = gson.fromJson(todosJson, String[].class);

        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.todo_app_widget);

            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.todo_text, pendingIntent);

            // Update widget view with todos
            views.setTextViewText(R.id.todo_text, String.join("\n", todosArray));

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
