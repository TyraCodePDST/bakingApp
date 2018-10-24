package com.example.vuthyra.bakingtime;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vuthyra.bakingtime.fragment.Fragment_video;
import com.example.vuthyra.bakingtime.model.Steps;
import com.google.android.exoplayer2.SimpleExoPlayer;

import java.util.ArrayList;
import java.util.List;
import com.example.vuthyra.bakingtime.fragment.*;
import com.example.vuthyra.bakingtime.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class StepActivity extends AppCompatActivity implements View.OnClickListener {


    public static final String INSTANCE_STEPS = "steps";
    public static final String INSTANCE_POSITION = "position";


    //key values for onSavedInstanceState.
    public static final String KEY_STEPS = "steps";
    public static final String KEY_STEPS_LIST = "steps_list";
    public static final String KEY_STEPS_POSITION = "steps_position";


    public static final String TAG = StepActivity.class.getSimpleName();


    private Button mBack, mNext;
    private Steps mStep;
    private ArrayList<Steps> mListStep;
    private SimpleExoPlayer player;

    private int stepPosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_video);
        if (savedInstanceState != null) {

            mListStep = savedInstanceState.getParcelableArrayList(KEY_STEPS_LIST);
            mStep = savedInstanceState.getParcelable(KEY_STEPS);
            stepPosition = savedInstanceState.getInt(KEY_STEPS_POSITION);


        } else {


            //Get the bundle from StepActivity
            Bundle bundle = getIntent().getExtras();
            //Get the step list
            mListStep = bundle.getParcelableArrayList(INSTANCE_STEPS);
            //Get the position clicked on in StepDetailActivity
            stepPosition = bundle.getInt(INSTANCE_POSITION);
            //Get Steps object to call later on into Fragment_video class.
            mStep = mListStep.get(stepPosition);

            Log.d(TAG, "SUCCESS");

            Fragment_video videoFragment = new Fragment_video();
            //update ingredient list
            videoFragment.setVideoData(mStep);
            //use the fragment manager and transaction to add the fragment to the screen
            FragmentManager fragmentManager_video = getSupportFragmentManager();

            //fragment transaction
            fragmentManager_video.beginTransaction()
                    .add(R.id.video_container, videoFragment)
                    .commit();





        }





        mBack = findViewById(R.id.id_back);
        mNext = findViewById(R.id.id_next);

        mBack.setOnClickListener(this);
        mNext.setOnClickListener(this);


//        SharedPreferences appSharedPrefs =  getSharedPreferences(DetailActivity.INSTANCE_PREFERENCE, MODE_PRIVATE );
//        String responseList = appSharedPrefs.getString(DetailActivity.KEY_LIST_INGREDIENTS, "");
//        String responseName = appSharedPrefs.getString(DetailActivity.KEY_INGREDIENT_NAME, "" );
//
//        Gson gson = new Gson();
//
//
//        List<Ingredients> retrieveIngredients = gson.fromJson(responseList,new TypeToken<List<Ingredients>>(){}.getType());
//
//        Log.d(TAG,   "This is LIST inside the list      :" + retrieveIngredients.toString());
//
//        Log.d(TAG,   "This is NAME inside the list      :" + responseName);
//
//

        /**
         * Initialized Fragment component for ingredient list.
         *
         */



    }





    @Override
    public void onClick(View v) {


        int lastPosition = mListStep.size() - 1;
        switch (v.getId()) {


            case R.id.id_back: {
                if (mStep.getId() > 0) {

                    //dont think i need this here i beleve its don in the fragment
                    if (player != null) {
                        player.stop();
//                        player.release();
                    }


                    //update
                    //create a new instance of the ingredient fragment

                    Fragment_video videoFragment = new Fragment_video();
                    //update ingredient list
                    mStep = mListStep.get(stepPosition - 1);


                    videoFragment.setVideoData(mStep);
                    //update ingredient list
                    //use the fragment manager and transaction to add the fragment to the screen
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    //fragment transaction
                    fragmentManager.beginTransaction()
                            .replace(R.id.video_container, videoFragment)
                            .commit();
                    stepPosition -= 1;

                } else {
                    Toast.makeText(this, "Already at the First Step", Toast.LENGTH_SHORT).show();
                    return;
                }

                break;
            }

            case R.id.id_next:
                //next button was pressed
                //load next step
                if (mStep.getId() < mListStep.get(lastPosition).getId()) {
                    if (player != null) {
                        player.stop();
//                        player.release();
                    }
                    //update
                    //create a new instance of the ingredient fragment

                    Fragment_video videoFragment = new Fragment_video();

                    mStep = mListStep.get(stepPosition + 1);
                    stepPosition += 1;
                    //update ingredient

                    videoFragment.setVideoData(mStep);
                    //update ingredient list
                    //use the fragment manager and transaction to add the fragment to the screen
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    //fragment transaction
                    fragmentManager.beginTransaction()
                            .replace(R.id.video_container, videoFragment)
                            .commit();

//                 stepPosition += 1;

                } else {
                    Toast.makeText(this, "Already at the Second Step", Toast.LENGTH_SHORT).show();
                    return;
                }

                break;


        }


    }// End of onClick method.










    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(KEY_STEPS_LIST, mListStep);
        outState.putParcelable(KEY_STEPS, mStep );
        outState.putInt(KEY_STEPS_POSITION, stepPosition );

    }

}









