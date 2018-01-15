package com.example.android.recipes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecipeService {

    public static void findRecipes(String ingredient, Callback callback){
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.RECIPES_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.QUERY_PARAMETER, ingredient);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Recipes> processResults(Response response){
        ArrayList<Recipes> recipes = new ArrayList<>();
        try{
            String jsonData = response.body().string();
            if(response.isSuccessful()){
                JSONObject returnJSON = new JSONObject(jsonData);
                JSONArray stuffJSON = returnJSON.getJSONArray("stuff");
                for (int i = 0; i < stuffJSON.length(); i++){
                    JSONObject foodJSON = stuffJSON.getJSONObject(i);
                    String name = foodJSON.getString("label");
                    String imageURL = foodJSON.getString("imageURL");
                    String URL = foodJSON.getString("URL");
                    int noOfServings = foodJSON.getInt("servings");

                    ArrayList<String> recipe = new ArrayList<>();
                    JSONArray recipeJSON = foodJSON.getJSONObject("recipes")
                            .getJSONArray("display_ingredients");
                    for (int y = 0; y < recipeJSON.length(); y++) {
                        recipe.add(recipeJSON.get(y).toString());
                    }

                    Recipes food = new Recipes(name, imageURL, URL, noOfServings, recipe);
                    recipes.add(food);

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recipes;
    }

}
