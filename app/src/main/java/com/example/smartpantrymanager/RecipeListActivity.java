package com.example.smartpantrymanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecipeListActivity extends AppCompatActivity {

    private RecyclerView recipeRecyclerView;
    private RecipeAdapter recipeAdapter;
    private DatabaseHelper databaseHelper;

    private final List<Recipe> recipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        recipeRecyclerView =
                findViewById(R.id.recipeRecyclerView);

        databaseHelper = new DatabaseHelper(this);

        recipeRecyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        recipeAdapter =
                new RecipeAdapter(this, recipes);

        recipeRecyclerView.setAdapter(recipeAdapter);

        loadRecipes();
    }

    private void loadRecipes() {
        List<Recipe> allRecipes =
                databaseHelper.getAllRecipes();

        recipeAdapter.updateRecipes(allRecipes);
    }
}