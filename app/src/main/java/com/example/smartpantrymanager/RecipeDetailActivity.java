package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    private TextView recipeNameTextView;
    private TextView ingredientsTextView;
    private TextView methodTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        recipeNameTextView =
                findViewById(R.id.recipeNameTextView);

        ingredientsTextView =
                findViewById(R.id.ingredientsTextView);

        methodTextView =
                findViewById(R.id.methodTextView);

        databaseHelper = new DatabaseHelper(this);

        int recipeId =
                getIntent().getIntExtra("recipe_id", -1);

        if (recipeId == -1) {
            finish();
            return;
        }

        loadRecipe(recipeId);
    }

    private void loadRecipe(int recipeId) {

        List<Recipe> recipes =
                databaseHelper.getAllRecipes();

        Recipe selectedRecipe = null;

        for (Recipe recipe : recipes) {
            if (recipe.getId() == recipeId) {
                selectedRecipe = recipe;
                break;
            }
        }

        if (selectedRecipe == null) {
            finish();
            return;
        }

        recipeNameTextView.setText(
                selectedRecipe.getName()
        );

        methodTextView.setText(
                selectedRecipe.getMethod()
        );

        List<RecipeIngredient> ingredients =
                databaseHelper.getRecipeIngredients(recipeId);

        StringBuilder ingredientText =
                new StringBuilder();

        for (RecipeIngredient ingredient : ingredients) {

            ingredientText
                    .append("• ")
                    .append(ingredient.getIngredientName())
                    .append(" - ")
                    .append(ingredient.getRequiredQuantity())
                    .append(" ")
                    .append(ingredient.getUnit())
                    .append("\n");
        }

        if (ingredients.isEmpty()) {
            ingredientsTextView.setText(
                    "No ingredients listed for this recipe."
            );
        } else {
            ingredientsTextView.setText(
                    ingredientText.toString()
            );
        }
    }
}