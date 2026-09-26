package com.example.smartpantrymanager;

import java.util.List;
import java.util.Locale;

public class RecipeMatcher {

    public static boolean matchesRecipe(
            Recipe recipe,
            List<RecipeIngredient> recipeIngredients,
            List<PantryItem> pantryItems) {

        if (recipeIngredients == null || recipeIngredients.isEmpty()) {
            return false;
        }

        for (RecipeIngredient requiredIngredient : recipeIngredients) {

            PantryItem matchingPantryItem = null;

            String requiredName =
                    normalizeIngredientName(
                            requiredIngredient.getIngredientName()
                    );

            String requiredUnit =
                    normalizeUnit(
                            requiredIngredient.getUnit()
                    );

            for (PantryItem pantryItem : pantryItems) {

                String pantryName =
                        normalizeIngredientName(
                                pantryItem.getName()
                        );

                String pantryUnit =
                        normalizeUnit(
                                pantryItem.getUnit()
                        );

                if (requiredName.equals(pantryName)
                        && requiredUnit.equals(pantryUnit)) {

                    matchingPantryItem = pantryItem;
                    break;
                }
            }

            // Required ingredient was not found.
            if (matchingPantryItem == null) {
                return false;
            }

            // Pantry quantity is not sufficient.
            if (matchingPantryItem.getQuantity()
                    < requiredIngredient.getRequiredQuantity()) {

                return false;
            }
        }

        // Every required ingredient exists
        // in sufficient quantity.
        return true;
    }

    private static String normalizeIngredientName(String name) {

        if (name == null) {
            return "";
        }

        String normalized =
                name.trim()
                        .toLowerCase(Locale.ROOT);

        if (normalized.equals("eggs")) {
            return "egg";
        }

        if (normalized.equals("tomatoes")) {
            return "tomato";
        }

        if (normalized.equals("potatoes")) {
            return "potato";
        }

        if (normalized.equals("onions")) {
            return "onion";
        }

        if (normalized.equals("carrots")) {
            return "carrot";
        }

        if (normalized.equals("peppers")) {
            return "pepper";
        }

        if (normalized.equals("bananas")) {
            return "banana";
        }

        if (normalized.equals("apples")) {
            return "apple";
        }

        if (normalized.endsWith("ies")) {
            return normalized.substring(
                    0,
                    normalized.length() - 3
            ) + "y";
        }

        if (normalized.endsWith("s")) {
            return normalized.substring(
                    0,
                    normalized.length() - 1
            );
        }

        return normalized;
    }

    private static String normalizeUnit(String unit) {

        if (unit == null) {
            return "";
        }

        String normalized =
                unit.trim()
                        .toLowerCase(Locale.ROOT);

        switch (normalized) {

            case "g":
            case "gram":
            case "grams":
                return "g";

            case "kg":
            case "kilogram":
            case "kilograms":
                return "kg";

            case "ml":
            case "millilitre":
            case "millilitres":
            case "milliliter":
            case "milliliters":
                return "ml";

            case "l":
            case "litre":
            case "litres":
            case "liter":
            case "liters":
                return "l";

            case "unit":
            case "units":
            case "piece":
            case "pieces":
            case "pc":
            case "pcs":
                return "unit";

            case "tbsp":
            case "tablespoon":
            case "tablespoons":
                return "tbsp";

            case "tsp":
            case "teaspoon":
            case "teaspoons":
                return "tsp";

            default:
                return normalized;
        }
    }
}