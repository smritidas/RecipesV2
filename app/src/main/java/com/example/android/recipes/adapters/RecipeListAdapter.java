package com.example.android.recipes.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.recipes.R;
import com.example.android.recipes.models.Recipes;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>  {
    private ArrayList<Recipes> mRecipe = new ArrayList<>();
    private Context mContext;

    public RecipeListAdapter(Context context, ArrayList<Recipes> recipes){
        mContext = context;
        mRecipe = recipes;
    }

    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item,
                parent,false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeListAdapter.RecipeViewHolder holder, int position) {
        holder.bindRecipe(mRecipe.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipe.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.recipeImageView) ImageView mRecipeImageView;
        @BindView(R.id.recipeNameTextView) TextView mRecipeNameTextView;
        @BindView(R.id.noOfServings) TextView mNoOfServings;

        private Context mContext;

        public RecipeViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindRecipe(Recipes recipe){
            Picasso.with(mContext).load(recipe.getImageURL()).into(mRecipeImageView);
            mRecipeNameTextView.setText(recipe.getName());
            mNoOfServings.setText(recipe.getNoOfServings());
        }
    }
}
