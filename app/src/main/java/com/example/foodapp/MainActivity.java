package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.users.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //validacion para tener la sesion activa
        verifyAutentication();

    }

    private void verifyAutentication() {
        // Verificar si el usuario está autenticado
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String uid = currentUser.getUid();
            String email = currentUser.getEmail();
            Intent loginView = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginView);
            finish();
        } else {
            Intent loginView = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(loginView);
            finish();
        }

    }

}

