package com.example.android.recipes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


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

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, recipes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String restaurant = ((TextView)view).getText().toString();
                Toast.makeText(RecipesActivity.this, restaurant, Toast.LENGTH_LONG).show();
            }
        });

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
            public void onResponse(Call call, Response response) throws IOException {
                    try{
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        recipes = recipeService.processResults(response);
                    } catch(IOException e){
                        e.printStackTrace();
                }
            }
        });
    }
}
