package com.example.foodapp.service;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodapp.models.Restaurant;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RestaurantService {

    private final Context context;
    FirebaseDatabase database;
    DatabaseReference restaurantesRef;

    public RestaurantService(Context context) {
        this.context = context;
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(context);
        database = FirebaseDatabase.getInstance();
        restaurantesRef = database.getReference();

    }

    public String save(Restaurant restaurant){
        try {
            initFirebase();
            restaurant.setId_restaurant(UUID.randomUUID().toString());
            restaurantesRef.child("Restaurante").child(restaurant.getId_restaurant()).setValue(restaurant);
            Toast.makeText(context, "Información guardada", Toast.LENGTH_LONG).show();
            return restaurant.getId_restaurant();
        }catch (Exception e){
            System.out.println(e.getMessage());
            Toast.makeText(context, "Error al guardar la información", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public void getAllRestaurants(final OnDataRetrievedListener listener){
        initFirebase();
        restaurantesRef.child("Restaurante").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Restaurant> restaurantList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Restaurant restaurant = snapshot.getValue(Restaurant.class);
                    restaurantList.add(restaurant);
                }

                if (listener != null) {
                    listener.onDataRetrieved(restaurantList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Error al recuperar datos: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public interface OnDataRetrievedListener {
        void onDataRetrieved(List<Restaurant> restaurantList);
    }
}


