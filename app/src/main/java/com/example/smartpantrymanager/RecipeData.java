package com.example.smartpantrymanager;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class RecipeData {

    public static void seedRecipes(SQLiteDatabase db) {

        addRecipe(
                db,
                "Pancakes",
                "Mix flour, milk, egg and sugar. Cook portions in a lightly greased pan until golden on both sides.",
                new String[][]{
                        {"flour", "200", "g"},
                        {"milk", "250", "ml"},
                        {"egg", "1", "unit"},
                        {"sugar", "30", "g"}
                }
        );

        addRecipe(
                db,
                "Scrambled Eggs",
                "Beat the eggs with milk and salt. Cook gently in a pan while stirring until set.",
                new String[][]{
                        {"egg", "2", "unit"},
                        {"milk", "30", "ml"},
                        {"salt", "2", "g"}
                }
        );

        addRecipe(
                db,
                "Chicken Pasta",
                "Cook the pasta. Fry the chicken and garlic, then combine with cooked pasta and tomato sauce.",
                new String[][]{
                        {"pasta", "200", "g"},
                        {"chicken", "250", "g"},
                        {"tomato sauce", "150", "ml"},
                        {"garlic", "10", "g"}
                }
        );

        addRecipe(
                db,
                "Tomato Pasta",
                "Cook pasta until tender. Heat tomato sauce with garlic and combine with the pasta.",
                new String[][]{
                        {"pasta", "200", "g"},
                        {"tomato sauce", "200", "ml"},
                        {"garlic", "10", "g"}
                }
        );

        addRecipe(
                db,
                "Chicken Sandwich",
                "Cook the chicken and place it between slices of bread with lettuce and tomato.",
                new String[][]{
                        {"bread", "2", "slice"},
                        {"chicken", "150", "g"},
                        {"lettuce", "30", "g"},
                        {"tomato", "50", "g"}
                }
        );

        addRecipe(
                db,
                "Cheese Sandwich",
                "Place cheese between slices of bread and toast until the cheese melts.",
                new String[][]{
                        {"bread", "2", "slice"},
                        {"cheese", "60", "g"},
                        {"butter", "10", "g"}
                }
        );

        addRecipe(
                db,
                "French Toast",
                "Dip bread in beaten egg and milk, then fry in a lightly buttered pan until golden.",
                new String[][]{
                        {"bread", "2", "slice"},
                        {"egg", "1", "unit"},
                        {"milk", "50", "ml"},
                        {"butter", "10", "g"}
                }
        );

        addRecipe(
                db,
                "Chicken Rice",
                "Cook the rice. Fry the chicken with onion and combine with the cooked rice.",
                new String[][]{
                        {"rice", "200", "g"},
                        {"chicken", "250", "g"},
                        {"onion", "50", "g"},
                        {"oil", "15", "ml"}
                }
        );

        addRecipe(
                db,
                "Vegetable Rice",
                "Cook the rice and stir-fry the vegetables with oil before combining everything.",
                new String[][]{
                        {"rice", "200", "g"},
                        {"carrot", "50", "g"},
                        {"peas", "50", "g"},
                        {"oil", "15", "ml"}
                }
        );

        addRecipe(
                db,
                "Omelette",
                "Beat the eggs and cook them in a pan with onion, tomato and cheese.",
                new String[][]{
                        {"egg", "2", "unit"},
                        {"onion", "30", "g"},
                        {"tomato", "50", "g"},
                        {"cheese", "40", "g"}
                }
        );

        addRecipe(
                db,
                "Beef Stir Fry",
                "Cook the beef quickly over high heat with vegetables, soy sauce and oil.",
                new String[][]{
                        {"beef", "250", "g"},
                        {"carrot", "50", "g"},
                        {"onion", "50", "g"},
                        {"soy sauce", "30", "ml"},
                        {"oil", "15", "ml"}
                }
        );

        addRecipe(
                db,
                "Vegetable Soup",
                "Cook the vegetables in stock until soft, then season and serve warm.",
                new String[][]{
                        {"carrot", "100", "g"},
                        {"potato", "150", "g"},
                        {"onion", "50", "g"},
                        {"vegetable stock", "500", "ml"}
                }
        );

        addRecipe(
                db,
                "Chicken Soup",
                "Cook chicken and vegetables in stock until the chicken is fully cooked and the vegetables are tender.",
                new String[][]{
                        {"chicken", "200", "g"},
                        {"carrot", "100", "g"},
                        {"onion", "50", "g"},
                        {"chicken stock", "500", "ml"}
                }
        );

        addRecipe(
                db,
                "Beef Burger",
                "Shape the beef into a patty and cook thoroughly. Serve in a bun with lettuce and tomato.",
                new String[][]{
                        {"beef", "200", "g"},
                        {"burger bun", "1", "unit"},
                        {"lettuce", "30", "g"},
                        {"tomato", "50", "g"}
                }
        );

        addRecipe(
                db,
                "Tuna Sandwich",
                "Mix tuna with mayonnaise and place it between slices of bread with lettuce.",
                new String[][]{
                        {"tuna", "100", "g"},
                        {"bread", "2", "slice"},
                        {"mayonnaise", "30", "g"},
                        {"lettuce", "30", "g"}
                }
        );

        addRecipe(
                db,
                "Mac and Cheese",
                "Cook the macaroni. Prepare a simple cheese sauce using milk, butter and cheese, then combine.",
                new String[][]{
                        {"macaroni", "200", "g"},
                        {"cheese", "100", "g"},
                        {"milk", "200", "ml"},
                        {"butter", "20", "g"}
                }
        );

        addRecipe(
                db,
                "Garlic Bread",
                "Mix butter with garlic and spread over bread. Bake until crisp and golden.",
                new String[][]{
                        {"bread", "4", "slice"},
                        {"butter", "40", "g"},
                        {"garlic", "15", "g"}
                }
        );

        addRecipe(
                db,
                "Mashed Potatoes",
                "Boil potatoes until soft, then mash with butter and milk until smooth.",
                new String[][]{
                        {"potato", "300", "g"},
                        {"butter", "30", "g"},
                        {"milk", "100", "ml"}
                }
        );

        addRecipe(
                db,
                "Chicken Wrap",
                "Cook the chicken and place it in a wrap with lettuce, tomato and mayonnaise.",
                new String[][]{
                        {"chicken", "150", "g"},
                        {"wrap", "1", "unit"},
                        {"lettuce", "30", "g"},
                        {"tomato", "50", "g"},
                        {"mayonnaise", "20", "g"}
                }
        );

        addRecipe(
                db,
                "Fruit Salad",
                "Cut the fruits into small pieces and mix together. Serve chilled.",
                new String[][]{
                        {"apple", "1", "unit"},
                        {"banana", "1", "unit"},
                        {"orange", "1", "unit"}
                }
        );
    }

    private static void addRecipe(
            SQLiteDatabase db,
            String name,
            String method,
            String[][] ingredients) {

        ContentValues recipeValues = new ContentValues();
        recipeValues.put("name", name);
        recipeValues.put("method", method);

        long recipeId = db.insert("recipes", null, recipeValues);

        if (recipeId == -1) {
            return;
        }

        for (String[] ingredient : ingredients) {

            ContentValues ingredientValues = new ContentValues();

            ingredientValues.put("recipe_id", recipeId);
            ingredientValues.put("ingredient_name", ingredient[0]);
            ingredientValues.put("required_quantity", Double.parseDouble(ingredient[1]));
            ingredientValues.put("unit", ingredient[2]);

            db.insert(
                    "recipe_ingredients",
                    null,
                    ingredientValues
            );
        }
    }
}