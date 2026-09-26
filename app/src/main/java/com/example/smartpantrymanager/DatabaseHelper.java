package com.example.smartpantrymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "smart_pantry.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_PANTRY = "pantry_items";
    private static final String TABLE_RECIPES = "recipes";
    private static final String TABLE_RECIPE_INGREDIENTS = "recipe_ingredients";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_UNIT = "unit";
    private static final String COLUMN_EXPIRY_DATE = "expiry_date";

    private static final String COLUMN_RECIPE_ID = "recipe_id";
    private static final String COLUMN_METHOD = "method";
    private static final String COLUMN_INGREDIENT_NAME = "ingredient_name";
    private static final String COLUMN_REQUIRED_QUANTITY = "required_quantity";

    private static final String CREATE_PANTRY_TABLE =
            "CREATE TABLE " + TABLE_PANTRY + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_QUANTITY + " REAL NOT NULL, " +
                    COLUMN_UNIT + " TEXT NOT NULL, " +
                    COLUMN_EXPIRY_DATE + " TEXT" +
                    ")";

    private static final String CREATE_RECIPES_TABLE =
            "CREATE TABLE " + TABLE_RECIPES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_METHOD + " TEXT NOT NULL" +
                    ")";

    private static final String CREATE_RECIPE_INGREDIENTS_TABLE =
            "CREATE TABLE " + TABLE_RECIPE_INGREDIENTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_RECIPE_ID + " INTEGER NOT NULL, " +
                    COLUMN_INGREDIENT_NAME + " TEXT NOT NULL, " +
                    COLUMN_REQUIRED_QUANTITY + " REAL NOT NULL, " +
                    COLUMN_UNIT + " TEXT NOT NULL, " +
                    "FOREIGN KEY (" + COLUMN_RECIPE_ID + ") REFERENCES " +
                    TABLE_RECIPES + "(" + COLUMN_ID + ")" +
                    ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PANTRY_TABLE);
        db.execSQL(CREATE_RECIPES_TABLE);
        db.execSQL(CREATE_RECIPE_INGREDIENTS_TABLE);

        RecipeData.seedRecipes(db);
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE_INGREDIENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PANTRY);

        onCreate(db);
    }

    // -----------------------------
    // PANTRY METHODS

    public long addPantryItem(PantryItem item) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_QUANTITY, item.getQuantity());
        values.put(COLUMN_UNIT, item.getUnit());
        values.put(COLUMN_EXPIRY_DATE, item.getExpiryDate());

        long id = db.insert(TABLE_PANTRY, null, values);

        db.close();

        return id;
    }

    public List<PantryItem> getAllPantryItems() {
        List<PantryItem> pantryItems = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_PANTRY,
                null,
                null,
                null,
                null,
                null,
                COLUMN_NAME + " ASC"
        );

        try {
            int idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
            int nameIndex = cursor.getColumnIndexOrThrow(COLUMN_NAME);
            int quantityIndex = cursor.getColumnIndexOrThrow(COLUMN_QUANTITY);
            int unitIndex = cursor.getColumnIndexOrThrow(COLUMN_UNIT);
            int expiryIndex = cursor.getColumnIndexOrThrow(COLUMN_EXPIRY_DATE);

            while (cursor.moveToNext()) {
                PantryItem item = new PantryItem(
                        cursor.getInt(idIndex),
                        cursor.getString(nameIndex),
                        cursor.getDouble(quantityIndex),
                        cursor.getString(unitIndex),
                        cursor.getString(expiryIndex)
                );

                pantryItems.add(item);
            }
        } finally {
            cursor.close();
            db.close();
        }

        return pantryItems;
    }

    public int updatePantryItem(PantryItem item) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, item.getName());
        values.put(COLUMN_QUANTITY, item.getQuantity());
        values.put(COLUMN_UNIT, item.getUnit());
        values.put(COLUMN_EXPIRY_DATE, item.getExpiryDate());

        int rowsUpdated = db.update(
                TABLE_PANTRY,
                values,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(item.getId())}
        );

        db.close();

        return rowsUpdated;
    }

    public int deletePantryItem(int id) {
        SQLiteDatabase db = getWritableDatabase();

        int rowsDeleted = db.delete(
                TABLE_PANTRY,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}
        );

        db.close();

        return rowsDeleted;
    }

    // -----------------------------
    // RECIPE METHODS

    public long addRecipe(Recipe recipe) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, recipe.getName());
        values.put(COLUMN_METHOD, recipe.getMethod());

        long id = db.insert(TABLE_RECIPES, null, values);

        db.close();

        return id;
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_RECIPES,
                null,
                null,
                null,
                null,
                null,
                COLUMN_NAME + " ASC"
        );

        try {
            int idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
            int nameIndex = cursor.getColumnIndexOrThrow(COLUMN_NAME);
            int methodIndex = cursor.getColumnIndexOrThrow(COLUMN_METHOD);

            while (cursor.moveToNext()) {
                Recipe recipe = new Recipe(
                        cursor.getInt(idIndex),
                        cursor.getString(nameIndex),
                        cursor.getString(methodIndex)
                );

                recipes.add(recipe);
            }
        } finally {
            cursor.close();
            db.close();
        }

        return recipes;
    }

    // -----------------------------
    // RECIPE INGREDIENT METHODS

    public long addRecipeIngredient(RecipeIngredient ingredient) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_RECIPE_ID, ingredient.getRecipeId());
        values.put(COLUMN_INGREDIENT_NAME, ingredient.getIngredientName());
        values.put(COLUMN_REQUIRED_QUANTITY, ingredient.getRequiredQuantity());
        values.put(COLUMN_UNIT, ingredient.getUnit());

        long id = db.insert(
                TABLE_RECIPE_INGREDIENTS,
                null,
                values
        );

        db.close();

        return id;
    }

    public List<RecipeIngredient> getRecipeIngredients(int recipeId) {
        List<RecipeIngredient> ingredients = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_RECIPE_INGREDIENTS,
                null,
                COLUMN_RECIPE_ID + " = ?",
                new String[]{String.valueOf(recipeId)},
                null,
                null,
                COLUMN_INGREDIENT_NAME + " ASC"
        );

        try {
            int idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
            int recipeIdIndex = cursor.getColumnIndexOrThrow(COLUMN_RECIPE_ID);
            int nameIndex = cursor.getColumnIndexOrThrow(COLUMN_INGREDIENT_NAME);
            int quantityIndex = cursor.getColumnIndexOrThrow(COLUMN_REQUIRED_QUANTITY);
            int unitIndex = cursor.getColumnIndexOrThrow(COLUMN_UNIT);

            while (cursor.moveToNext()) {
                RecipeIngredient ingredient =
                        new RecipeIngredient(
                                cursor.getInt(idIndex),
                                cursor.getInt(recipeIdIndex),
                                cursor.getString(nameIndex),
                                cursor.getDouble(quantityIndex),
                                cursor.getString(unitIndex)
                        );

                ingredients.add(ingredient);
            }
        } finally {
            cursor.close();
            db.close();
        }

        return ingredients;
    }
}