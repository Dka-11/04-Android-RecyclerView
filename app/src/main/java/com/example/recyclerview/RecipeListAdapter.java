package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {
    private ArrayList<RecipeData> recipeList;
    private LayoutInflater mInflater;
    private View.OnClickListener mOnItemClickListener;

    public RecipeListAdapter(Context context, ArrayList<RecipeData> recipeList){
        mInflater = LayoutInflater.from(context);
        this.recipeList = recipeList;
    }

    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.activity_recipe_list, parent, false);
        return new RecipeViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder( RecipeListAdapter.RecipeViewHolder recipeViewHolder, int position) {
        recipeViewHolder.name.setText((recipeList.get(position)).getName());
        recipeViewHolder.description.setText((recipeList.get(position)).getDescription());
        Glide.with(recipeViewHolder.itemView)
                .load(recipeList.get(position).getImage())
                .override(100, 150)
                .into(recipeViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView name, description;
        ImageView image;

        public RecipeViewHolder(View itemView, RecipeListAdapter recipeListAdapter){
            super(itemView);
            name = itemView.findViewById(R.id.recipe_name);
            description = itemView.findViewById(R.id.recipe_description);
            image = itemView.findViewById(R.id.recipe_image);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}
