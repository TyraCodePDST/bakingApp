package com.example.vuthyra.bakingtime.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredients implements Parcelable {

    private float quantity;
    private String measure;
    private String ingredient;

    public Ingredients() {
        // need for parser
    }

    public Ingredients(float quantity, String measurement, String ingredient) {
        this.setQuantity(quantity);
        this.setMeasure(measurement);
        this.setIngredient(ingredient);
    }
    //constructor used for parcel
    public Ingredients(Parcel parcel) {
        //read and set saved values from parcel
        quantity = parcel.readFloat();
        measure = parcel.readString();
        ingredient = parcel.readString();
    }





    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(quantity);
        dest.writeString(measure);
        dest.writeString(ingredient);
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }



}
