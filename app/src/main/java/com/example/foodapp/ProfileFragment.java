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

    private Button btn_actualizar;
    private ImageView image_profile;
    private User ActualUser;
    private TextView textUsername, textEmail, txtProfile_username, txtProfile_password;

    public ProfileFragment() {}

    public ProfileFragment(User us){
        ActualUser = us;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        initComponents();


        if(getArguments() != null){
            ActualUser = (User) getArguments().getSerializable("User");
            System.out.println(ActualUser.toString());
            updateUser();
        }

        return view;
    }

    private void initComponents() {
            textUsername = view.findViewById(R.id.textUsername);
            textEmail = view.findViewById(R.id.textEmail);
            txtProfile_password = view.findViewById(R.id.txtProfile_password);
            txtProfile_username = view.findViewById(R.id.txtProfile_username);
            btn_actualizar = view.findViewById(R.id.btn_actualizar);

            btn_actualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "aqui", Toast.LENGTH_SHORT).show();
                }
            });

    }

    public void updateUser(){
        textUsername.setText(ActualUser.getUsername());
        txtProfile_username.setText(ActualUser.getUsername());
        textEmail.setText(ActualUser.getCorreo());
        txtProfile_password.setText(ActualUser.getPassword());

    }


}