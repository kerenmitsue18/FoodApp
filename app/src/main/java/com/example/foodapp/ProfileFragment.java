package com.example.foodapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.foodapp.models.User;


public class ProfileFragment extends Fragment {
    private View view;
    public ProfileFragment() {}
    private Button btn_actualizar;
    private ImageView image_profile;
    private TextView textUsername, textEmail, txtProfile_username, txtProfile_password;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        initComponents();
        return view;
    }

    private void initComponents() {
            textUsername = view.findViewById(R.id.textUsername);
            textEmail = view.findViewById(R.id.textEmail);
            txtProfile_password = view.findViewById(R.id.password);
            txtProfile_username = view.findViewById(R.id.txtProfile_username);
            btn_actualizar = view.findViewById(R.id.btn_actualizar);

            btn_actualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "aqui", Toast.LENGTH_SHORT).show();
                }
            });

    }

    public void updateData(User user){

    }


}