package com.example.foodapp;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodapp.databinding.ActivityHomeBinding;
import com.example.foodapp.menu.HomeFragment;
import com.example.foodapp.menu.PlaceFragment;
import com.example.foodapp.menu.SettingsFragment;
import com.example.foodapp.menu.StatisticsFragment;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initComponents();
    }

    private void initComponents(){
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
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
    }


    private void changeFragment(Fragment selectedFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, selectedFragment);
        fragmentTransaction.commit();
    }


}