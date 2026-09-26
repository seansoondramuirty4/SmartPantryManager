package com.example.smartpantrymanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder> {

    private final List<PantryItem> pantryItems;
    private final DatabaseHelper databaseHelper;

    public PantryAdapter(Context context, List<PantryItem> pantryItems) {
        this.pantryItems = pantryItems;
        this.databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public PantryViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pantry, parent, false);

        return new PantryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull PantryViewHolder holder,
            int position) {

        PantryItem item = pantryItems.get(position);

        holder.ingredientNameTextView.setText(item.getName());

        String details = "Quantity: "
                + item.getQuantity()
                + " | Unit: "
                + item.getUnit();

        holder.ingredientDetailsTextView.setText(details);

        String expiryDate = item.getExpiryDate();

        if (expiryDate == null || expiryDate.trim().isEmpty()) {
            holder.expiryDateTextView.setText("Expiry: Not specified");
        } else {
            holder.expiryDateTextView.setText(
                    "Expiry: " + expiryDate
            );
        }

        holder.editButton.setOnClickListener(v -> {

            Intent intent = new Intent(
                    v.getContext(),
                    AddEditIngredientActivity.class
            );

            intent.putExtra("ingredient_id", item.getId());

            v.getContext().startActivity(intent);
        });

        holder.deleteButton.setOnClickListener(v -> {

            new AlertDialog.Builder(v.getContext())
                    .setTitle("Delete Ingredient")
                    .setMessage(
                            "Are you sure you want to delete "
                                    + item.getName()
                                    + "?"
                    )
                    .setPositiveButton("Delete", (dialog, which) -> {

                        int result =
                                databaseHelper.deletePantryItem(
                                        item.getId()
                                );

                        if (result > 0) {
                            pantryItems.remove(holder.getAdapterPosition());
                            notifyItemRemoved(holder.getAdapterPosition());

                            Toast.makeText(
                                    v.getContext(),
                                    "Ingredient deleted",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return pantryItems.size();
    }

    public void updateItems(List<PantryItem> newItems) {
        pantryItems.clear();
        pantryItems.addAll(newItems);
        notifyDataSetChanged();
    }

    public static class PantryViewHolder extends RecyclerView.ViewHolder {

        TextView ingredientNameTextView;
        TextView ingredientDetailsTextView;
        TextView expiryDateTextView;

        Button editButton;
        Button deleteButton;

        public PantryViewHolder(@NonNull View itemView) {
            super(itemView);

            ingredientNameTextView =
                    itemView.findViewById(
                            R.id.ingredientNameTextView
                    );

            ingredientDetailsTextView =
                    itemView.findViewById(
                            R.id.ingredientDetailsTextView
                    );

            expiryDateTextView =
                    itemView.findViewById(
                            R.id.expiryDateTextView
                    );

            editButton =
                    itemView.findViewById(
                            R.id.editButton
                    );

            deleteButton =
                    itemView.findViewById(
                            R.id.deleteButton
                    );
        }
    }
}