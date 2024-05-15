package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment;
    ActivityFragment activityFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        activityFragment = new ActivityFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, homeFragment).commit();


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Seçilen öğeye göre ilgili fragmenti yükle
                if (item.getItemId() == R.id.menu_home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, homeFragment).commit();
                    return true;
                } else if (item.getItemId() == R.id.menu_activities) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, activityFragment).commit();
                    return true;
                } else {
                    return false;
                }
            }
        });

    }

}