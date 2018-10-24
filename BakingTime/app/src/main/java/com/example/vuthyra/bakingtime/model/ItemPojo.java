package com.example.vuthyra.bakingtime.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ItemPojo implements Parcelable {

    private float id;
    private String name;
    private List<Ingredients> ingredients;
    private List<Steps> steps;
    private int servings;
    private String image;


    //Empty constructor for Item object.
    public ItemPojo(){}

    public ItemPojo(String name, List<Ingredients> ingredients, List<Steps> steps, int servings, String image) {

        this.setName(name);
        this.setIngredients(ingredients);
        this.setSteps(steps);
        this.setServings(servings);
        this.setImage(image);
    }

    public ItemPojo(float id, String name, List<Ingredients> ingredients, List<Steps> steps, int servings, String image) {

        this.setId(id);
        this.setName(name);
        this.setIngredients(ingredients);
        this.setSteps(steps);
        this.setServings(servings);
        this.setImage(image);
    }





    public static final Creator<ItemPojo> CREATOR = new Creator<ItemPojo>() {
        @Override
        public ItemPojo createFromParcel(Parcel in) {
            return new ItemPojo(in);
        }

        @Override
        public ItemPojo[] newArray(int size) {
            return new ItemPojo[size];
        }
    };

    //A method to get CREATOR object of ItemPojo
    public static Creator<ItemPojo> getCREATOR() {
        return CREATOR;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    //Constructor to read in Parcel object
    public ItemPojo(Parcel input) {

        id = input.readFloat();
        name = input.readString();
        image = input.readString();
        servings = input.readInt();
        steps = input.createTypedArrayList(Steps.CREATOR);
        ingredients = input.createTypedArrayList(Ingredients.CREATOR);
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeFloat(id);
        dest.writeString(image);
        dest.writeString(name);
        dest.writeInt(servings);
        dest.writeTypedList(steps);
        dest.writeTypedList(ingredients);


    }





    //All the setter and getter method.
    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public void setSteps(List<Steps> steps) {
        this.steps = steps;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
