package com.example.vuthyra.bakingtime;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import com.example.vuthyra.bakingtime.*;

import com.example.vuthyra.bakingtime.adapters.ItemAdapter;
import com.example.vuthyra.bakingtime.api.*;

import com.example.vuthyra.bakingtime.adapters.*;
import com.example.vuthyra.bakingtime.model.ItemPojo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ItemAdapter.ItemClickListener {

    public static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    public static final String TAG = MainActivity.class.getSimpleName();


    private RecyclerView mRecyclerView;
    private ItemAdapter mItemAdapter;
    private List<ItemPojo> mItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);

        mRecyclerView = findViewById(R.id.recyclerView_items);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));

        mItemList = new ArrayList<>();
        mItemAdapter = new ItemAdapter(this, mItemList, this);


        loadItemsList();

    }


    public void loadItemsList () {

        // Create a retrofit object.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //Create our BakingApi and pass our BakingApi.class

        BakingApi bakingApi = retrofit.create(BakingApi.class);


        // Fetch a list of the recipe.
        Call<List<ItemPojo>> call = bakingApi.getRecicpes();

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<List<ItemPojo>>() {
            @Override
            public void onResponse(Call<List<ItemPojo>> call, Response<List<ItemPojo>> response) {
                // The network call was a success and we got a re sponse
                // TODO: use the repository list and display it

                mItemList = response.body();
                mItemAdapter.updateAdapter(mItemList);
                mRecyclerView.setAdapter(mItemAdapter);
                Log.d(TAG,  "Server response success:  " + response.toString());
            }

            @Override
            public void onFailure(Call<List<ItemPojo>> call, Throwable t) {
                // the network call was a failure
                // TODO: handle error

                Log.d(TAG,  "Server response ERROR:  " + t.toString());
            }});

    }


    @Override
    public void onItemClick(int position) {

        launchDetailsActivity(position);

    }

    private void launchDetailsActivity(int position) {

        ItemPojo recipeToSend;
        recipeToSend = this.mItemList.get(position);

        String ingredientName  = recipeToSend.getName() ;

        //Using Intent to pass Movie object by a key name Movie.
        Intent intent = new Intent(this, DetailActivity.class);


        Bundle bundle = new Bundle();
        bundle.putParcelable(DetailActivity.INSTANCE_RECIPES, recipeToSend);
        bundle.putString(DetailActivity.INSTANCE_RECIPES_NAME, ingredientName );
        bundle.putString("name", ingredientName );
//        bundle.putInt(DetailActivity.INSTANCE_RECIPES_POSITION, position);

        //Checking to make sure that this intent would result into a proper activity.
        PackageManager packageManager = this.getPackageManager();
        if (intent.resolveActivity(packageManager) != null) {
            intent.putExtras(bundle);
            this.startActivity(intent);
        }


    }



}
