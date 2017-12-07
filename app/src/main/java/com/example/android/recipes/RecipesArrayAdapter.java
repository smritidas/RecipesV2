package com.example.android.recipes;


import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

public class RecipesArrayAdapter extends ArrayAdapter{
    private Context context;
    private String[] things; //this is for the ingredients
    private String[] recipes;


    public RecipesArrayAdapter(@NonNull Context context, int resource, String[] ingredients, String[] recipes) {
        super(context, resource);
        this.context = context;
        this.things = ingredients;
        this.recipes = recipes;
    }

    @Override
    public Object getItem(int position){
        String recipe = recipes[position];
        String ingredients = things[position];
        return String.format("%s \nYou can make: %s", ingredients, recipe);
    }

    @Override
    public int getCount(){
        return recipes.length;
    }


}
