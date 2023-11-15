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
import com.google.firebase.database.Query;
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

    private User getUser(String id_user){
        User user = new User();
        initFirebase();
        Query query = databaseReference.orderByChild("id_user").equalTo(id_user);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Obtiene el usuario encontrado
                        User usuario = snapshot.getValue(User.class);
                        String correo = usuario.getCorreo();
                        Toast.makeText(context, "Correo: " + correo, Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Error en la consulta", Toast.LENGTH_SHORT).show();
            }
        });
        return user;
    }


}
