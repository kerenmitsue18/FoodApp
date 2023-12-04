package com.example.foodapp.service;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodapp.models.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class UserService {

    private final Context context;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public UserService(Context context){
        this.context = context;
    }

    public String save(User user) {
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

    public void getUser(String id_user, OnUserDataReceived callback){
        initFirebase();
        DatabaseReference userbase = databaseReference.child("Usuarios");

        userbase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()  ) {
                    if(snapshot.getKey().equals(id_user)){
                        User user = snapshot.getValue(User.class);
                        callback.onDataReceived(user);
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
