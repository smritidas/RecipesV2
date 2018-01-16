package com.example.android.recipes.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.example.android.recipes.R;
import com.example.android.recipes.models.Recipes;
import com.example.android.recipes.services.RecipeService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecipesActivity extends AppCompatActivity {
    public static final String TAG = RecipesActivity.class.getSimpleName();

    @BindView(R.id.recipeTextView) TextView recipeTextView;
    @BindView(R.id.listView) ListView listView;

    public ArrayList<Recipes> recipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String ingredient = intent.getStringExtra("ingredient");

        recipeTextView.setText("Here are all the restaurants near: " + ingredient);
        getRecipes(ingredient);
    }

    private void getRecipes(String ingredient){
        final RecipeService recipeService = new RecipeService();
        recipeService.findRecipes(ingredient, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                    recipes = recipeService.processResults(response);

                    RecipesActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String[] recipeNames =  new String[recipes.size()];
                            for (int i = 0; i < recipeNames.length; i++ ){
                                recipeNames[i] = recipes.get(i).getName();
                            }
                            ArrayAdapter adapter = new ArrayAdapter(RecipesActivity.this,
                                    android.R.layout.simple_list_item_1, recipeNames);
                                    listView.setAdapter(adapter);

                                    for (Recipes recipe : recipes){
                                        Log.d(TAG, "Name: " + recipe.getName());
                                        Log.d(TAG, "Image: " + recipe.getImageURL());
                                        Log.d(TAG, "URL: " + recipe.getUrl());
                                        Log.d(TAG, "Servings: " + Integer.toString(recipe.getNoOfServings()));
                                        Log.d(TAG, "Ingredients: " + android.text.TextUtils.join(", ", recipe.getIngredients()));
                                    }
                        }
                    });

                }

        });
    }

}
