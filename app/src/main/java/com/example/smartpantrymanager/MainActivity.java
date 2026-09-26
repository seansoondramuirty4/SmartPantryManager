package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView pantryRecyclerView;
    private PantryAdapter pantryAdapter;
    private DatabaseHelper databaseHelper;
    private TextView emptyPantryTextView;
    private TextView pantryCountTextView;

    private final List<PantryItem> pantryItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pantryRecyclerView =
                findViewById(R.id.pantryRecyclerView);

        emptyPantryTextView =
                findViewById(R.id.emptyPantryTextView);

        pantryCountTextView =
                findViewById(R.id.pantryCountTextView);

        Button addIngredientButton =
                findViewById(R.id.addIngredientButton);

        Button viewRecipesButton =
                findViewById(R.id.viewRecipesButton);

        Button suggestedRecipesButton =
                findViewById(R.id.suggestedRecipesButton);

        Button settingsButton =
                findViewById(R.id.settingsButton);

        databaseHelper = new DatabaseHelper(this);

        pantryRecyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        pantryAdapter =
                new PantryAdapter(this, pantryItems);

        pantryRecyclerView.setAdapter(pantryAdapter);

        addIngredientButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    AddEditIngredientActivity.class
            );

            startActivity(intent);
        });

        viewRecipesButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    RecipeListActivity.class
            );

            startActivity(intent);
        });

        suggestedRecipesButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    SuggestedRecipesActivity.class
            );

            startActivity(intent);
        });

        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    SettingsActivity.class
            );

            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadPantryItems();
    }

    private void loadPantryItems() {
        List<PantryItem> items =
                databaseHelper.getAllPantryItems();

        pantryAdapter.updateItems(items);

        if (items.isEmpty()) {

            pantryCountTextView.setVisibility(View.GONE);
            emptyPantryTextView.setVisibility(View.VISIBLE);

        } else {

            emptyPantryTextView.setVisibility(View.GONE);
            pantryCountTextView.setVisibility(View.VISIBLE);

            String ingredientWord =
                    items.size() == 1
                            ? "ingredient"
                            : "ingredients";

            pantryCountTextView.setText(
                    items.size()
                            + " "
                            + ingredientWord
                            + " in your pantry"
            );
        }
    }
}