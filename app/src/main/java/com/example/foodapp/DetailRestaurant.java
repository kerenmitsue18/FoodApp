package com.example.foodapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.Manifest;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.foodapp.models.Restaurant;

public class DetailRestaurant extends Fragment implements View.OnClickListener {

    private View view;
    private Restaurant actualRestaurant;
    private TextView txt_Name, txt_street, txt_telefono, txt_correo;
    private Button btn_contact;
    private static final int REQUEST_CALL = 1;

    public DetailRestaurant(Restaurant actualRestaurant) {
        this.actualRestaurant = actualRestaurant;
    }

    public DetailRestaurant() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_restaurant, container, false);

        initComponents();
        return view;
    }

    private void initComponents() {
        txt_Name = view.findViewById(R.id.txt_Name);
        txt_street = view.findViewById(R.id.txt_street);
        txt_telefono = view.findViewById(R.id.txt_phone);
        txt_correo = view.findViewById(R.id.txt_email);
        btn_contact = view.findViewById(R.id.btn_contact);
        btn_contact.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btn_contact){
            contact();
        }
    }

    private void contact() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CALL_PHONE},  REQUEST_CALL );
            }else{
                startActivity(new Intent( Intent.ACTION_CALL, Uri.parse("tel:" + actualRestaurant.getPhone()) ));
            }
        }
    }

    public void updateData(Restaurant restaurant) {
        actualRestaurant = restaurant;
        txt_Name.setText(restaurant.getName());
        txt_street.setText(restaurant.getAddress().toString());
        txt_telefono.setText("Télefono: " + restaurant.getPhone());
        txt_correo.setText("Correo: "+ restaurant.getEmail());
    }
}