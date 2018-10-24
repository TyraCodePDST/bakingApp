package com.example.vuthyra.bakingtime.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vuthyra.bakingtime.R;
import com.example.vuthyra.bakingtime.model.Ingredients;

import java.util.List;

import com.example.vuthyra.bakingtime.model.*;

public class IngredientAdapter  extends RecyclerView.Adapter<IngredientAdapter.IngredientsViewHolder> {

        private static final String TAG = IngredientAdapter.class.getSimpleName();

        private final List<Ingredients> mIngredentList;
        Context mContext;

        public IngredientAdapter (List<Ingredients> ingredients, Context context) {
            this.mIngredentList = ingredients;
            this.mContext = context;

        }


        @NonNull
        @Override
        public IngredientAdapter.IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View trailerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.container_ingredients, parent, false );
            IngredientsViewHolder ingredientHolder = new IngredientsViewHolder(trailerView);
            return ingredientHolder;

        }


    @Override
        public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {

            Ingredients populatedIngredients = mIngredentList.get(position);


            holder.mUnit.setText(String.valueOf(populatedIngredients.getQuantity()));
            holder.mMeasurement.setText(populatedIngredients.getMeasure());
            holder.mInfomation.setText(String.valueOf(populatedIngredients.getIngredient()));

            Log.d(TAG, "There are number of items " + getItemCount());

        }

        @Override
        public int getItemCount() {
            return mIngredentList.size();
        }

        /**
         * Indepentdent class of TrailerHolder that acts as a ViewHolder for our
         * RecyclerViewAdapter.
         */


        public class IngredientsViewHolder extends RecyclerView.ViewHolder{

            private final TextView mUnit;
            private final TextView mMeasurement;
            private final TextView mInfomation;


            IngredientsViewHolder(View itemView) {

                super(itemView);
                mUnit = (TextView) itemView.findViewById(R.id.unit);
                mMeasurement = (TextView) itemView.findViewById(R.id.measurement);
                mInfomation = (TextView) (TextView) itemView.findViewById(R.id.ingredients);




            }

//
//            public  String getNameRecipe(){
//
//                Ingredients populatedIngredients = mIngredentList.get(getAdapterPosition());
//                String name = populatedIngredients.getIngredient();
//                return name;
//            }





        }




    }
