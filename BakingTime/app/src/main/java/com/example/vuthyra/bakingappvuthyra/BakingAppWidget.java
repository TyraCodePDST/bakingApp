package com.example.vuthyra.bakingappvuthyra;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

import com.example.vuthyra.bakingtime.DetailActivity;
import com.example.vuthyra.bakingtime.R;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidget extends AppWidgetProvider {

    public static final String INSTANCE_PREFERENCE = "Preferences";
    public static String EXTRA_RECIPE=
            "com.commonsware.android.appwidget.lorem.WORD";

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them


        int[] realAppWidgetIds = AppWidgetManager.getInstance(context)
                .getAppWidgetIds(new ComponentName(context, BakingAppWidget.class));
        for (int id : realAppWidgetIds) {


            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);
            Intent serviceIntent = new Intent(context, BakingAppWidgetService.class);
//            serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));
            views.setRemoteAdapter(R.id.widget_listView, serviceIntent);


            SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
            String movieTheaterTitle = appSharedPrefs.getString(DetailActivity.KEY_INGREDIENT_NAME, "");
            views.setTextViewText(R.id.recipe_list_name, movieTheaterTitle);

//            Intent intent = new Intent(context, DetailActivity.class);
//            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//            views.setOnClickPendingIntent(R.id.recipe_launcher_icon, pendingIntent);


                    appWidgetManager.updateAppWidget(appWidgetIds, views);



            }
        }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


}

