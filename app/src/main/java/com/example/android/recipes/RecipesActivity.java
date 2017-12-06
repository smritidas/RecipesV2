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

public class RecipesActivity extends AppCompatActivity {
    private TextView recipeTextView;
    private ListView listView;
    private String[] recipes = new String[] {"Mashed Potato", "Apple Pie", "Roast Chicken", "Oatmeal Cookies",
            "Pad Thai", "Sushi", "Ramen noodles", "Tacos", "Burrito's",
            "Fish cakes", "Cheeseburger", "Fries", "Chicken soup", "Pasta", "Stew", "Poutine" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        listView = (ListView) findViewById(R.id.listView);
        recipeTextView = (TextView) findViewById(R.id.recipeTextView);

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

    }
}
