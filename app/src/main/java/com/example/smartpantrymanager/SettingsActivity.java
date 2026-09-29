package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Switch darkModeSwitch = findViewById(R.id.darkModeSwitch);

        int currentMode = AppCompatDelegate.getDefaultNightMode();

        if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
            darkModeSwitch.setChecked(true);
        } else {
            darkModeSwitch.setChecked(false);
        }

        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES
                );
            } else {
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                );
            }
        });
    }
}