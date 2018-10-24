package com.example.vuthyra.bakingtime.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vuthyra.bakingtime.R;
import com.example.vuthyra.bakingtime.StepActivity;
import com.example.vuthyra.bakingtime.model.Steps;

import java.util.ArrayList;
import java.util.List;


public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

        private static final String TAG = StepAdapter.class.getSimpleName();

        private final List<Steps> mStepList;
        private Context mContext;

    private final ItemClickListener mClickListener;




    //    //click listener for recipe
//    public interface ItemClickListener {
//        void onItemClick(int position);
//
//    }
        public StepAdapter(List<Steps> step, Context context, ItemClickListener listener) {
            this.mStepList = step;
            this.mClickListener = listener;
        }






    @NonNull
        @Override
        public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View stepView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.container_steps, parent, false );
            StepViewHolder stepViewHolder = new StepViewHolder(stepView);
            return stepViewHolder;

        }


        @Override
        public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {

            Steps populatedSteps = mStepList.get(position);

            holder.mStepId.setText(String.valueOf(populatedSteps.getId()));
            holder.mStepDescription.setText(populatedSteps.getShortDescription());


            Log.d(TAG, "There are number of items " + getItemCount());

        }


    /**
     * method to pass List<Steps> to the next activity.
     */
    public List<Steps> returnStep() {
        return mStepList;
    }


        @Override
        public int getItemCount() {
            return mStepList.size();
        }



    /**
         * Indepentdent class of TrailerHolder that acts as a ViewHolder for our
         * RecyclerViewAdapter.
         */


        public class StepViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {


        TextView mStepId;
        TextView mStepDescription;


        StepViewHolder(View itemView) {

            super(itemView);
            mStepId = (TextView) itemView.findViewById(R.id.step_id);
            mStepDescription = (TextView) itemView.findViewById(R.id.step_shortDescription);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            mClickListener.onItemClick(getAdapterPosition());
        }



    }

    //click listener for recipe
    public interface ItemClickListener {
        void onItemClick(int position);

    }



    }

