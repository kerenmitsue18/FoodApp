package com.example.foodapp.service;

import android.content.Context;
import android.widget.Toast;

import com.example.foodapp.models.UserFormulary;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;


public class FormularyService {

    private final Context context;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private final UserFormulary userFormulary;

    public FormularyService(Context context, UserFormulary userFormulary){
        this.userFormulary = userFormulary;
        this.context = context;
    }

    public void save() {
        initFirebase();

    }

    private void initFirebase() {
        FirebaseApp.initializeApp(context);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        userFormulary.setId(UUID.randomUUID().toString());
        databaseReference.child("Formulario").child(userFormulary.getId()).setValue(userFormulary);
        Toast.makeText(context, "Información guardada", Toast.LENGTH_LONG).show();
    }

}
