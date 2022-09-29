package com.example.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.recyclerview.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private final LinkedList<String> mWordList = new LinkedList<>();
    private ArrayList<RecipeData> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareRecipeList();
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecipeListAdapter recipeListAdapter = new RecipeListAdapter(MainActivity.this, recipeList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(recipeListAdapter);
        recipeListAdapter.setOnItemClickListener(onItemClickListener);

    }

    private void prepareRecipeList() {
        recipeList = new ArrayList<>();
        RecipeData data;
        data = new RecipeData(getString(R.string.lemon_sandwiches_name), getString(R.string.lemon_sandwiches_desc), R.drawable.lemon_sandwich, getString(R.string.lemon_sandwiches_details));
        recipeList.add(data);
        data = new RecipeData(getString(R.string.mango_black_bean_salad_name), getString(R.string.mango_black_bean_salad_desc), R.drawable.mango_black_bean_salad, getString(R.string.mango_black_bean_salad_details));
        recipeList.add(data);
        data = new RecipeData(getString(R.string.sweet_potato_name), getString(R.string.sweet_potato_desc), R.drawable.sweet_potatoes, getString(R.string.sweet_potato_details));
        recipeList.add(data);
        data = new RecipeData(getString(R.string.lime_mousse_name), getString(R.string.lime_mousse_desc));
        recipeList.add(data);
        data = new RecipeData(getString(R.string.broiled_tilapia_parmesan_name), getString(R.string.broiled_tilapia_parmesan_desc));
        recipeList.add(data);
    }

    public void openDetailActivity(int imageId, String details){
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("image", imageId);
        intent.putExtra("details", details);
        startActivity(intent);
    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            RecipeData thisRecipe = recipeList.get(position);
            openDetailActivity(thisRecipe.getImage(), thisRecipe.getDetails());
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}