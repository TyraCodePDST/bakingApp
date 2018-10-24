package com.example.vuthyra.bakingtime.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuthyra.bakingtime.R;

import java.util.ArrayList;
import java.util.List;

import com.example.vuthyra.bakingtime.StepActivity;
import com.example.vuthyra.bakingtime.adapters.StepAdapter;
import com.example.vuthyra.bakingtime.model.Steps;

public class Fragment_steps extends Fragment implements StepAdapter.ItemClickListener {

    private StepAdapter mStepAdapter;
    private RecyclerView mStepRecyclerView;
    private List<Steps> mStepList;


    public static final String INSTANCE_STATE_STEP = "stepList";
    private boolean mTwoPane;

    //Define new interface that triggered a callback into DetailActivity.class
    private OnStepClickListener mCallBack;

    // OnImageClickListener interface, calls a method in the host activity named onImageSelected
    public interface OnStepClickListener {
        void onStepSelected(int position);
    }

    // Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            mCallBack = (OnStepClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnStepClickListener");
        }
    }



    public Fragment_steps() {}



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if (savedInstanceState != null) {

            mStepList = savedInstanceState.getParcelableArrayList(INSTANCE_STATE_STEP);
        }


        //inflate the step list layout
        View rootViewStep= inflater.inflate(R.layout.activity_list_steps, container,false);

        //get a reference to the recyclerview
        mStepRecyclerView = rootViewStep.findViewById(R.id.recyclerView_steps);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        mStepRecyclerView.setHasFixedSize(true);
        mStepRecyclerView.setLayoutManager(layoutManager);
        mStepRecyclerView.setNestedScrollingEnabled(false);

        mStepAdapter = new StepAdapter(mStepList , getContext(), this);
        mStepRecyclerView.setAdapter(mStepAdapter);

//        mStepRecyclerView.setOnClickListener( new StepAdapter(, ));


        return rootViewStep;


    }

    @Override
    public void onItemClick(int position) {
        if(!mTwoPane) {

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(StepActivity.INSTANCE_STEPS, (ArrayList<? extends Parcelable>) mStepList);
            bundle.putInt(StepActivity.INSTANCE_POSITION, position);
            //Using Intent to pass List of Steps data object.
            Intent intent = new Intent(getContext(), StepActivity.class);

            //Checking to make sure that this intent would result into a proper activity.
            PackageManager packageManager = getContext().getPackageManager();
            if (intent.resolveActivity(packageManager) != null) {
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }

        }else {
            mCallBack.onStepSelected(position);
        }
    }



    public void setStepsList(List<Steps> stepsList) {
        this.mStepList = stepsList;
    }

        public void setTwoPane(boolean mTwoPane)  {
            this.mTwoPane = mTwoPane;
        }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(INSTANCE_STATE_STEP, (ArrayList<? extends Parcelable>) mStepList);
    }



}
