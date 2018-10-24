package com.example.vuthyra.bakingappvuthyra;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.vuthyra.bakingtime.DetailActivity;
import com.example.vuthyra.bakingtime.R;
import com.example.vuthyra.bakingtime.model.Ingredients;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class BakingAppWidgetService extends RemoteViewsService {

    public static final String TAG = BakingAppWidgetService.class.getSimpleName();

    @Override
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory (Intent intent) {
        return new ListRemoteViewsFactory(this.getApplicationContext());
    }
}



