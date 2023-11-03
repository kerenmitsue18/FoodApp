package com.example.foodapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.users.LoginActivity;
import com.example.foodapp.users.formulary.RegisterActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

     BottomNavigationView bottomNavigationView;
     HomeFragment homeFragment = new HomeFragment();
     PlaceFragment placesFragment = new PlaceFragment();
     SettingsFragment settingsFragment = new SettingsFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();
                        return true;
                    case R.id.lugares:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, placesFragment).commit();
                        return true;
                    case R.id.configuracion:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, settingsFragment).commit();
                        return true;
                    default:
                        throw new IllegalStateException("Unexpected value: " + item.getItemId());
                }
                return false;
            }
        });

        //validacion para tener la sesion activa
        SharedPreferences preferences = getSharedPreferences("sesion", Context.MODE_PRIVATE);
        if (preferences.getBoolean("status_user", false) == false) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent loginView = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(loginView);
                    finish();
                }
            }, 40000);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent loginView = new Intent(getApplicationContext(), RegisterActivity.class);
                    startActivity(loginView);
                    finish();
                }
            }, 40000);
        }


    }
}
