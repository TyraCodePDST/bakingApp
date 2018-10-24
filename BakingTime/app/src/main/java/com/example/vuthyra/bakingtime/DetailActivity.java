package com.example.vuthyra.bakingtime;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.vuthyra.bakingappvuthyra.BakingAppWidget;
import com.example.vuthyra.bakingtime.fragment.Fragment_ingredients;
import com.example.vuthyra.bakingtime.fragment.Fragment_steps;
import com.example.vuthyra.bakingtime.fragment.Fragment_video;
import com.example.vuthyra.bakingtime.idlingresource.TestIdlingResource;
import com.example.vuthyra.bakingtime.model.Ingredients;
import com.example.vuthyra.bakingtime.model.ItemPojo;
import com.example.vuthyra.bakingtime.model.Steps;
import com.google.gson.Gson;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements  Fragment_steps.OnStepClickListener{

    public static final String INSTANCE_RECIPES = "recipe";
    public static final String INSTANCE_RECIPES_NAME = "recipe_name";
    public static final String INSTANCE_RECIPES_POSITION = "recipe_position";

    public static final String TAG = DetailActivity.class.getSimpleName();


    private SharedPreferences.Editor editor;
    private SharedPreferences appSharedPrefs;



    private ItemPojo mParsedItemPojo;
    private List<Ingredients> mListIngredient;
    private List<Steps> mListStep;
    private Steps mStep;

    //    private String recipeNameSelection;
    //Create a private variable to keep track of two pane layout.
    private boolean mTwoPane;
    private int mPosition;


    public String mItemName;

    //Include Idling resource for testing.
    private TestIdlingResource mIdlingResource;

//    public static final String EXTRA_NAME = "name_intent";
//    public static final String EXTRA_LIST = "list_intent";


    public static final String KEY_LIST_INGREDIENTS = "ingredient_list";
    public static final String KEY_INGREDIENT_NAME = "ingredient_name";



//    //
//    public static final String INSTANCE_PREFERENCE= "recipe_selected";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        if (findViewById(R.id.two_pane_layout) != null) {
            mTwoPane = true;

            if (savedInstanceState != null) {
                mParsedItemPojo = savedInstanceState.getParcelable(INSTANCE_RECIPES);
            }
            else {

                Bundle bundle = getIntent().getExtras();
                mParsedItemPojo = bundle.getParcelable(INSTANCE_RECIPES);
                mPosition = bundle.getInt(INSTANCE_RECIPES_POSITION);
                mItemName =  bundle.getString(INSTANCE_RECIPES_NAME);
            }


            mListIngredient = mParsedItemPojo.getIngredients();
            mListStep = mParsedItemPojo.getSteps();




            appSharedPrefs =  PreferenceManager.getDefaultSharedPreferences(this);
            editor = appSharedPrefs.edit();
            Gson gson = new Gson();
            //Convert both ingredientList into String literals,
            // and also the name of this recipe.
            String json = gson.toJson(mListIngredient);

            //Save these two data to pass on into BakingAppWidgetService.class
            editor.putString(KEY_LIST_INGREDIENTS, json);
            editor.putString(KEY_INGREDIENT_NAME, mItemName);
            editor.apply();


            RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.baking_app_widget);
            remoteViews.setTextViewText(R.id.recipe_list_name, "Recipe Name: " + mItemName );

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            ComponentName thisWidget = new ComponentName(this, BakingAppWidget.class);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_listView);
            appWidgetManager.updateAppWidget(thisWidget, remoteViews);





            /**
             * Initialized Fragment component for ingredient list.
             *
             */

            //use the fragment manager and transaction to add the fragment to the screen
            FragmentManager fragmentManager = getSupportFragmentManager();

            Fragment_ingredients ingredientFragment = new Fragment_ingredients();
            //update ingredient list
            ingredientFragment.setIngredientsList(mListIngredient);
            //fragment transaction
            fragmentManager.beginTransaction()
                    .add(R.id.ingredient_container, ingredientFragment)
                    .commit();

            Log.d(TAG, "Here is the list of ingredients" + mListIngredient.toString());

            /**
             * Initialized Fragment component for step list.
             *
             */
            Fragment_steps stepFragment = new Fragment_steps();
            stepFragment.setStepsList(mListStep);
            stepFragment.setTwoPane(mTwoPane);

            fragmentManager.beginTransaction()
                    .add(R.id.step_container, stepFragment)
                    .commit();

            Log.d(TAG, "Here is the list of steps" + mListStep.toString());


            mPosition = 0;

            mStep = mListStep.get(mPosition);


            Fragment_video videoFragment = new Fragment_video();
            //update ingredient list
            videoFragment.setVideoData(mStep);
            //use the fragment manager and transaction to add the fragment to the screen
            FragmentManager fragmentManager_video = getSupportFragmentManager();

            //fragment transaction
            fragmentManager_video.beginTransaction()
                    .replace(R.id.video_container, videoFragment)
                    .addToBackStack(null)
                    .commit();




        }

        else {

            mTwoPane = false;

            if (savedInstanceState != null) {
                mParsedItemPojo = savedInstanceState.getParcelable(INSTANCE_RECIPES);
            }
            else {

                Bundle bundle = getIntent().getExtras();

                //Getting ItemPojo object, selected position,
                // and the name of recipe that was selected
                // from MainActivity,
                mParsedItemPojo = bundle.getParcelable(INSTANCE_RECIPES);
                mPosition = bundle.getInt(INSTANCE_RECIPES_POSITION);
                mItemName =  bundle.getString(INSTANCE_RECIPES_NAME);



            }

            //Call the getIdling resourece
            getIdlingResource();


            mListIngredient = mParsedItemPojo.getIngredients();
            mListStep = mParsedItemPojo.getSteps();

            appSharedPrefs =  PreferenceManager.getDefaultSharedPreferences(this);
            editor = appSharedPrefs.edit();
            Gson gson = new Gson();
            //Convert both ingredientList into String literals,
            // and also the name of this recipe.
            String json = gson.toJson(mListIngredient);

            //Save these two data to pass on into BakingAppWidgetService.class
            editor.putString(KEY_LIST_INGREDIENTS, json);
            editor.putString(KEY_INGREDIENT_NAME, mItemName);
            editor.apply();


            RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.baking_app_widget);
            remoteViews.setTextViewText(R.id.recipe_list_name, "Recipe Name: " + mItemName );

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            ComponentName thisWidget = new ComponentName(this, BakingAppWidget.class);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_listView);
            appWidgetManager.updateAppWidget(thisWidget, remoteViews);


            /**
             * Initialized Fragment component for ingredient list.
             *
             */

            //use the fragment manager and transaction to add the fragment to the screen
            FragmentManager fragmentManager = getSupportFragmentManager();

            Fragment_ingredients ingredientFragment = new Fragment_ingredients();
            //update ingredient list
            ingredientFragment.setIngredientsList(mListIngredient);


            //fragment transaction
            fragmentManager.beginTransaction()
                    .add(R.id.ingredient_container, ingredientFragment)
                    .commit();

            Log.d(TAG, "Here is the list of ingredients" + mListIngredient.toString());

            /**
             * Initialized Fragment component for step list.
             *
             */
            Fragment_steps stepFragment = new Fragment_steps();
            stepFragment.setStepsList(mListStep);
            stepFragment.setTwoPane(mTwoPane);

            fragmentManager.beginTransaction()
                    .add(R.id.step_container, stepFragment)
                    .commit();

            if(mIdlingResource!=null){
                mIdlingResource.setIdleState(true);
            }


        }


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(INSTANCE_RECIPES, mParsedItemPojo);
        outState.putInt(INSTANCE_RECIPES_POSITION, mPosition );
        outState.putString(INSTANCE_RECIPES_NAME,  mItemName);

    }




    @VisibleForTesting
    @NonNull
    public TestIdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new TestIdlingResource();
        }
        return mIdlingResource;
    }



    @Override
    public void onStepSelected(int position) {

        mStep = mListStep.get(position);


        Fragment_video videoFragment = new Fragment_video();
        //update ingredient list
        videoFragment.setVideoData(mStep);
        //use the fragment manager and transaction to add the fragment to the screen
        FragmentManager fragmentManager_video = getSupportFragmentManager();

        //fragment transaction
        fragmentManager_video.beginTransaction()
                .replace(R.id.video_container, videoFragment)
                .commit();






    }










}


//
//
//
//
//
//    }


