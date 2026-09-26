package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SuggestedRecipesActivity extends AppCompatActivity {

    private RecyclerView suggestedRecipesRecyclerView;
    private TextView noMatchesTextView;

    private RecipeAdapter recipeAdapter;
    private DatabaseHelper databaseHelper;

    private final List<Recipe> suggestedRecipes =
            new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        suggestedRecipesRecyclerView =
                findViewById(R.id.suggestedRecipesRecyclerView);

        noMatchesTextView =
                findViewById(R.id.noMatchesTextView);

        databaseHelper =
                new DatabaseHelper(this);

        suggestedRecipesRecyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        recipeAdapter =
                new RecipeAdapter(
                        this,
                        suggestedRecipes
                );

        suggestedRecipesRecyclerView.setAdapter(
                recipeAdapter
        );

        loadSuggestedRecipes();
    }

    private void loadSuggestedRecipes() {

        List<Recipe> allRecipes =
                databaseHelper.getAllRecipes();

        List<PantryItem> pantryItems =
                databaseHelper.getAllPantryItems();

        List<Recipe> matchingRecipes =
                new ArrayList<>();

        for (Recipe recipe : allRecipes) {

            List<RecipeIngredient> ingredients =
                    databaseHelper.getRecipeIngredients(
                            recipe.getId()
                    );

            boolean matches =
                    RecipeMatcher.matchesRecipe(
                            recipe,
                            ingredients,
                            pantryItems
                    );

            if (matches) {
                matchingRecipes.add(recipe);
            }
        }

        recipeAdapter.updateRecipes(matchingRecipes);

        if (matchingRecipes.isEmpty()) {

            noMatchesTextView.setVisibility(
                    TextView.VISIBLE
            );

            noMatchesTextView.setText(
                    "No recipes match your pantry right now.\n\n"
                            + "Add the missing ingredients or "
                            + "increase your pantry quantities "
                            + "to see recipe suggestions."
            );

        } else {

            noMatchesTextView.setVisibility(
                    TextView.GONE
            );
        }
    }
}