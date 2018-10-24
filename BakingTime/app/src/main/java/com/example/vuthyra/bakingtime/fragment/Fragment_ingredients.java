package com.example.vuthyra.bakingtime.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuthyra.bakingtime.DetailActivity;
import com.example.vuthyra.bakingtime.R;

import java.util.ArrayList;
import java.util.List;

import com.example.vuthyra.bakingtime.idlingresource.TestIdlingResource;
import com.example.vuthyra.bakingtime.model.Ingredients;
import com.example.vuthyra.bakingtime.adapters.IngredientAdapter;


public class Fragment_ingredients extends Fragment {

        private IngredientAdapter mIngredientAdapter;
        private RecyclerView mIngredientRecyclerView;
        private List<Ingredients> ingredientList;

    //Include Idling resource for testing.
    private TestIdlingResource mIdlingResource;


        public static final String INSTANCE_STATE_INGREDIENT = "ingredientList";

        //Empty constructor for instanciate the FragmentIngredients
        public Fragment_ingredients(){}



        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

            if(savedInstanceState != null){
                ingredientList = savedInstanceState.getParcelableArrayList(INSTANCE_STATE_INGREDIENT);
            }
            //inflate the ingredient list layout
            View rootViewIngredient = inflater.inflate(R.layout.activity_list_ingredients, container,false);

//            ingredientList = new ArrayList<>();

            //get a reference to the recyclerview
            mIngredientRecyclerView = rootViewIngredient.findViewById(R.id.recyclerView_ingredients);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                    LinearLayoutManager.VERTICAL, false);
            mIngredientRecyclerView.setHasFixedSize(true);
            mIngredientRecyclerView.setLayoutManager(layoutManager);
            mIngredientRecyclerView.setNestedScrollingEnabled(false);

            //setup ingredient adapter
            mIngredientAdapter = new IngredientAdapter (ingredientList, getContext());

            //set the adapter
            mIngredientRecyclerView.setAdapter(mIngredientAdapter);



            return rootViewIngredient;



        }






        public void setIngredientsList(List<Ingredients> ingredientsList) {
            this.ingredientList = ingredientsList;
//            mIngredientAdapter.notifyDataSetChanged();

        }

        @Override
        public void onSaveInstanceState(@NonNull Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putParcelableArrayList(INSTANCE_STATE_INGREDIENT, (ArrayList<? extends Parcelable>) ingredientList);
        }












}
