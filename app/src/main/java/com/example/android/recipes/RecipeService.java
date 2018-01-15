package com.example.android.recipes;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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

}
