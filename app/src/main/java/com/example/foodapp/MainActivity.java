package com.example.foodapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodapp.databinding.ActivityMainBinding;
import com.example.foodapp.menu.HomeFragment;
import com.example.foodapp.menu.PlaceFragment;
import com.example.foodapp.menu.SettingsFragment;
import com.example.foodapp.menu.StatisticsFragment;
import com.example.foodapp.users.LoginActivity;
import com.example.foodapp.users.formulary.RegisterActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        changeFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.menu_home:
                    changeFragment(new HomeFragment());
                    break;

                case R.id.menu_buscar:
                    changeFragment(new PlaceFragment());
                    break;
                case R.id.menu_estadisticas:
                    changeFragment(new StatisticsFragment());
                    break;
                case R.id.menu_perfil:
                    changeFragment(new SettingsFragment());
                    break;
            }

            return true;

        });


        //validacion para tener la sesion activa
        SharedPreferences preferences = getSharedPreferences("sesion", Context.MODE_PRIVATE);
        if (preferences.getString("id_user", "") == null) {
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

    private void changeFragment(Fragment selectedFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, selectedFragment);
        fragmentTransaction.commit();
    }




}

