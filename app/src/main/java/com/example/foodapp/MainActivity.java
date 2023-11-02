package com.example.foodapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.users.LoginActivity;
import com.example.foodapp.users.formulary.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button btn_closedSession;
    private Button btn_formulary;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

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

        //boton de cerrar sesion
        btn_closedSession = findViewById(R.id.btn_closedSession);
        btn_closedSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closed_session();

            }
        });

        //boton de formulario
        btn_formulary = findViewById(R.id.btn_formulary);
        btn_formulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFormulary();

            }
        });
    }

    private void viewFormulary() {
        Intent mainActivity = new Intent(getApplicationContext(),RegisterActivity.class );
        startActivity(mainActivity);
        finish();
    }

    private void closed_session() {

        // Obtén una instancia de FirebaseAuth
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();

        //eliminar la sesion activa
        SharedPreferences preferences = getSharedPreferences("sesion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("status", false);
        editor.apply();

        startActivity(new Intent(this, LoginActivity.class));
        finish();

    }




}
