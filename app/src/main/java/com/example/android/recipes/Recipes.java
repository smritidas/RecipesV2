package com.example.android.recipes;


import java.util.ArrayList;

public class Recipes {
    private String label; //recipe name
    private String imageURL;
    private String url; //original recipe identifier
    private int noOfServings;
    private ArrayList<String> ingredients = new ArrayList<>();

    public Recipes(){};

    public Recipes(String label, String imageURL, String url,
                   int noOfServings, ArrayList<String> ingredients) {
        this.label = label;
        this.imageURL = imageURL;
        this.url = url;
        this.noOfServings = noOfServings;
        this.ingredients = ingredients;
    };

    public String getLabel() {
        return label;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getUrl() {
        return url;
    }

    public int getNoOfServings() {
        return noOfServings;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }
}

