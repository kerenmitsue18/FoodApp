package com.example.foodapp.service;

import android.content.Context;
import android.widget.Toast;

import com.example.foodapp.models.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class UserService {

    private final Context context;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private final User user;

    public UserService(Context context, User user){
        this.user = user;
        this.context = context;
    }

    public String save() {
        initFirebase();
        user.setId(UUID.randomUUID().toString());
        databaseReference.child("Usuarios").child(user.getId()).setValue(user);
        Toast.makeText(context, "Usuario guardado", Toast.LENGTH_LONG).show();
        return user.getId();
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(context);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}
