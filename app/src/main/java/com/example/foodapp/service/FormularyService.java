package com.example.foodapp.service;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodapp.models.UserFormulary;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;


public class FormularyService {

    private final Context context;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private UserFormulary userFormulary;

    public FormularyService(Context context){
        this.context = context;
    }

    public String save(UserFormulary userFormulary) {
        try {
            initFirebase();
            userFormulary.setId_formulario(UUID.randomUUID().toString());
            databaseReference.child("Formulario").child(userFormulary.getId_formulario()).setValue(userFormulary);
            Toast.makeText(context, "Información guardada", Toast.LENGTH_LONG).show();
            return userFormulary.getId_formulario();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error al guardar la información", Toast.LENGTH_LONG).show();
            return null; // Otra opción sería lanzar una excepción personalizada
        }
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(context);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void getFormulary(String id_formulario, OnFormularyDataReceived callback) {
        initFirebase();
        DatabaseReference formularioRef = databaseReference.child("Formulario");

        formularioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()  ) {
                    if(snapshot.getKey().equals(id_formulario)){
                        UserFormulary userFormulary = snapshot.getValue(UserFormulary.class);
                        callback.onDataReceived(userFormulary);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Error al recuperar datos: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
