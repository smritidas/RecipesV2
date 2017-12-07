package com.example.android.recipes;


import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

public class RecipesArrayAdapter extends ArrayAdapter{
    private Context context;
    private String[] recipes;
    private String[] things; //this is for the ingredients


    public RecipesArrayAdapter(@NonNull Context context, int resource, String[] recipes, String[] ingredients) {
        super(context, resource);
        this.context = context;
        this.recipes = recipes;
        this.things = ingredients;
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
