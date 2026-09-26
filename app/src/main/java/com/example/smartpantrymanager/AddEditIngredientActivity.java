package com.example.smartpantrymanager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddEditIngredientActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText quantityEditText;
    private EditText unitEditText;
    private EditText expiryDateEditText;

    private DatabaseHelper databaseHelper;

    private int ingredientId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient);

        nameEditText = findViewById(R.id.nameEditText);
        quantityEditText = findViewById(R.id.quantityEditText);
        unitEditText = findViewById(R.id.unitEditText);
        expiryDateEditText = findViewById(R.id.expiryDateEditText);

        Button saveIngredientButton =
                findViewById(R.id.saveIngredientButton);

        Button cancelButton =
                findViewById(R.id.cancelButton);

        TextView titleTextView =
                findViewById(R.id.titleTextView);

        databaseHelper = new DatabaseHelper(this);

        ingredientId = getIntent().getIntExtra("ingredient_id", -1);

        if (ingredientId != -1) {
            titleTextView.setText("Edit Ingredient");
            saveIngredientButton.setText("Update Ingredient");

            loadIngredient();
        }

        expiryDateEditText.setOnClickListener(v -> showDatePicker());

        saveIngredientButton.setOnClickListener(v -> saveIngredient());

        cancelButton.setOnClickListener(v -> finish());
    }

    private void loadIngredient() {
        for (PantryItem item : databaseHelper.getAllPantryItems()) {

            if (item.getId() == ingredientId) {
                nameEditText.setText(item.getName());
                quantityEditText.setText(
                        String.valueOf(item.getQuantity())
                );
                unitEditText.setText(item.getUnit());

                if (item.getExpiryDate() != null) {
                    expiryDateEditText.setText(
                            item.getExpiryDate()
                    );
                }

                break;
            }
        }
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog =
                new DatePickerDialog(
                        this,
                        (view, year, month, dayOfMonth) -> {

                            String selectedDate =
                                    String.format(
                                            "%04d-%02d-%02d",
                                            year,
                                            month + 1,
                                            dayOfMonth
                                    );

                            expiryDateEditText.setText(selectedDate);
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );

        datePickerDialog.show();
    }

    private void saveIngredient() {

        String name = nameEditText.getText()
                .toString()
                .trim();

        String quantityText = quantityEditText.getText()
                .toString()
                .trim();

        String unit = unitEditText.getText()
                .toString()
                .trim();

        String expiryDate = expiryDateEditText.getText()
                .toString()
                .trim();

        if (name.isEmpty()) {
            nameEditText.setError("Enter an ingredient name");
            nameEditText.requestFocus();
            return;
        }

        if (quantityText.isEmpty()) {
            quantityEditText.setError("Enter a quantity");
            quantityEditText.requestFocus();
            return;
        }

        if (unit.isEmpty()) {
            unitEditText.setError("Enter a unit");
            unitEditText.requestFocus();
            return;
        }

        double quantity;

        try {
            quantity = Double.parseDouble(quantityText);
        } catch (NumberFormatException e) {
            quantityEditText.setError("Enter a valid number");
            quantityEditText.requestFocus();
            return;
        }

        if (quantity <= 0) {
            quantityEditText.setError(
                    "Quantity must be greater than zero"
            );
            quantityEditText.requestFocus();
            return;
        }

        PantryItem item = new PantryItem(
                ingredientId == -1 ? 0 : ingredientId,
                name,
                quantity,
                unit,
                expiryDate
        );

        if (ingredientId == -1) {

            long result = databaseHelper.addPantryItem(item);

            if (result != -1) {
                Toast.makeText(
                        this,
                        "Ingredient added successfully",
                        Toast.LENGTH_SHORT
                ).show();

                finish();
            } else {
                Toast.makeText(
                        this,
                        "Unable to add ingredient",
                        Toast.LENGTH_SHORT
                ).show();
            }

        } else {

            int result =
                    databaseHelper.updatePantryItem(item);

            if (result > 0) {
                Toast.makeText(
                        this,
                        "Ingredient updated successfully",
                        Toast.LENGTH_SHORT
                ).show();

                finish();
            } else {
                Toast.makeText(
                        this,
                        "Unable to update ingredient",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }
}