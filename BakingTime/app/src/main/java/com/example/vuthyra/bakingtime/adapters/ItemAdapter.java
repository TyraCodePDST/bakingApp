package com.example.vuthyra.bakingtime.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vuthyra.bakingtime.DetailActivity;
import com.example.vuthyra.bakingtime.R;
import com.example.vuthyra.bakingtime.model.ItemPojo;

import java.util.ArrayList;
import java.util.List;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemsHolder>{


        private static final String TAG = ItemAdapter.class.getSimpleName();
        private List<ItemPojo> itemList;
        private ItemPojo mParsedItem;
        private Context mContext;

    private final ItemClickListener mClickListener;

        public ItemAdapter(Context context, List<ItemPojo> items, ItemClickListener listener) {
            mContext = context;
            itemList = items;
            this.mClickListener = listener;
        }

        @NonNull
        @Override
        public ItemAdapter.ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View ItemsView = LayoutInflater.from(mContext)
                    .inflate(R.layout.container_items,
                            parent,
                            false );

            ItemsHolder itemsHolder = new ItemsHolder(ItemsView);
            Log.d(TAG, "created ItemsHolder completed");

            return itemsHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ItemAdapter.ItemsHolder holder, int position) {

            //Create new Movie object with the given value from List<Movie> movieList;
            mParsedItem = itemList.get(position);
            holder.itemName.setText(mParsedItem.getName());


        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }

        /**
         * method to pass List<Item> to the next activity.
         */
        public List<ItemPojo> returnItems() {
            return itemList;
        }


        public void updateAdapter(List<ItemPojo> itemList){
            this.itemList = itemList;
            notifyDataSetChanged();
        }







        public class ItemsHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener

        {

            private TextView itemName;

            ItemsHolder(View itemView) {
                super(itemView);
                //Get a reference resource ID of ImageView of the movie poster,
                // then setOnClickListener to get response of what movie is selected.
                itemName = (TextView) itemView.findViewById(R.id.single_recipe);
            itemName.setOnClickListener(this);
            }

        @Override
        public void onClick(View view) {




            mClickListener.onItemClick(getAdapterPosition());


        }





        }


    //click listener for recipe
    public interface ItemClickListener {
        void onItemClick(int position);

    }





}
