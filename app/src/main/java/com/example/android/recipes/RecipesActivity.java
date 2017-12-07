package com.example.android.recipes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesActivity extends AppCompatActivity {
    @BindView(R.id.recipeTextView) TextView recipeTextView;
    @BindView(R.id.listView) ListView listView;
    private String[] recipes = new String[] {"Mashed Potato", "Apple Pie", "Roast Chicken", "Oatmeal Cookies",
            "Pad Thai", "Sushi", "Ramen noodles", "Tacos", "Burrito's",
            "Fish cakes", "Cheeseburger", "Fries", "Chicken soup", "Pasta", "Stew", "Poutine" };

    private String[] ingredients = new String[]{"Potato", "Cheese", "Apple", "Flour", "Chicken", "Oatmeal",
            "Shrimp", "Noodles", "Fish", "Taco", "Ground meat", "Chicken", "Pasta", "Banana",
            "Peas", "Onions"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);


        ArrayAdapter adapter = new RecipesArrayAdapter(this, android.R.layout.simple_list_item_1, ingredients, recipes);
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

    }
}
