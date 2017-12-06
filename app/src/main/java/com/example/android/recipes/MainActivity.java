package com.example.android.recipes;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Button findRecipesButton;
    private EditText ingredientEditText;
    private TextView appNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ingredientEditText = (EditText) findViewById(R.id.ingredientEditText);
        findRecipesButton = (Button) findViewById(R.id.findRecipeButton);
        appNameTextView = (TextView) findViewById(R.id.appNameTextView);
        Typeface openSans = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf");
        appNameTextView.setTypeface(openSans);
        findRecipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ingredient = ingredientEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, RecipesActivity.class);
                intent.putExtra("ingredient", ingredient);
                startActivity(intent);
            }
        });


    }
}
