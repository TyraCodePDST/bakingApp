package com.example.vuthyra.bakingappvuthyra;

import android.content.Context;
import android.content.SharedPreferences;
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

public class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {


        public static final String TAG = ListRemoteViewsFactory.class.getSimpleName();
        Context mContext;
        List<Ingredients> mListIngredients;


        public ListRemoteViewsFactory(Context applicationContext) {
        this.mContext = applicationContext;
       mListIngredients = getAllListIngredient();
    }


        @Override
        public void onCreate () {


    }

        @Override
        public void onDataSetChanged () {

        mListIngredients = getAllListIngredient();

    }

        private List<Ingredients> getAllListIngredient () {

        mListIngredients = null;

        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);

        if (appSharedPrefs != null) {


            String responseList = appSharedPrefs.getString(DetailActivity.KEY_LIST_INGREDIENTS, "");
            String responseName = appSharedPrefs.getString(DetailActivity.KEY_INGREDIENT_NAME, "");
            Gson gson = new Gson();
            mListIngredients = gson.fromJson(responseList, new TypeToken<ArrayList<Ingredients>>() {
            }.getType());
//            Log.d(TAG, "This is LIST inside the list      :" + mListIngredients.toString());
//
//            Log.d(TAG, "This is NAME inside the list      :" + responseName);



        }


        return mListIngredients;

    }


        @Override
        public void onDestroy () {

    }

        @Override
        public int getCount () {
        return mListIngredients.size();
    }

        @Override
        public RemoteViews getViewAt ( int position){


        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.baking_app_widget_item);

        String holder;

        if ((holder = String.valueOf(mListIngredients.get(position).getQuantity())) != null) {
            views.setTextViewText(R.id.widget_unit, holder );
        }
            if ((holder = mListIngredients.get(position).getMeasure()) != null) {
                views.setTextViewText(R.id.widget_measurement, holder );
            }

        if ((holder = mListIngredients.get(position).getIngredient()) != null) {
            views.setTextViewText(R.id.widget_information, holder);

            Log.d(TAG, "This is value    " + holder);

        }


        return (views);

    }

        @Override
        public RemoteViews getLoadingView () {
        return null;
    }

        @Override
        public int getViewTypeCount () {
        return 1;
    }

        @Override
        public long getItemId ( int position){
        return 0;
    }

        @Override
        public boolean hasStableIds () {
        return false;
    }


}


