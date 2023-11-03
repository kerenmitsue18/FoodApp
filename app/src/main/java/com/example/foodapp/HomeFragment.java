package com.example.foodapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.foodapp.users.LoginActivity;
import com.example.foodapp.users.formulary.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;


public class HomeFragment extends Fragment {

    private Button btn_closedSession;
    private Button btn_formulary;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //boton de cerrar sesion
        btn_closedSession = view.findViewById(R.id.btn_closedSession);
        btn_closedSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closed_session();

            }
        });

        //boton de formulario
        btn_formulary = view.findViewById(R.id.btn_formulary);
        btn_formulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFormulary();

            }
        });

        return view;
    }

    private void viewFormulary() {
        Intent mainActivity = new Intent(this.getContext(), RegisterActivity.class );
        startActivity(mainActivity);
        getActivity().onBackPressed();

    }

    private void closed_session() {

        // Obtén una instancia de FirebaseAuth
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();

        //eliminar la sesion activa
        SharedPreferences preferences = this.getContext().getSharedPreferences("sesion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("status", false);
        editor.apply();

        startActivity(new Intent(this.getContext(), LoginActivity.class));
        getActivity().onBackPressed();


    }


}
}