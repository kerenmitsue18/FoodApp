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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;


public class FormularyService {

    private final Context context;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public FormularyService(Context context){
        this.context = context;
    }

    public String save(UserFormulary userFormulary) {
        initFirebase();
        userFormulary.setId(UUID.randomUUID().toString());
        databaseReference.child("Formulario").child(userFormulary.getId()).setValue(userFormulary);
        Toast.makeText(context, "Información guardada", Toast.LENGTH_LONG).show();
        return userFormulary.getId();
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(context);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public UserFormulary getFormulary(String id_formulario){
        UserFormulary userFormulary = new UserFormulary();
        initFirebase();
        Query query = databaseReference.orderByChild("id_formulario").equalTo(id_formulario);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        UserFormulary userFormulary = snapshot.getValue(UserFormulary.class);
                        Toast.makeText(context, "id_formulario: " + userFormulary.getId(), Toast.LENGTH_SHORT ).show();
                    }
                } else{
                    Toast.makeText(context, "Formulario no encontrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Error en la consulta", Toast.LENGTH_SHORT).show();
            }
        });

        return userFormulary;
    }

}
