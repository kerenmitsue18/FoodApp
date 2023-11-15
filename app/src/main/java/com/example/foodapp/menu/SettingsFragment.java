package com.example.foodapp.menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.foodapp.R;
import com.example.foodapp.service.FormularyService;
import com.example.foodapp.users.LoginActivity;
import com.example.foodapp.users.formulary.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;


public class SettingsFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Button btnCerrarSesion, btnEstadisticas, btnFormulary, btn_profile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_settings, container, false);

        initComponents();
        return view;
    }

    private void initComponents() {
        //inicializar los datos del usuario
        SharedPreferences preferences = getContext().getSharedPreferences("sesion", Context.MODE_PRIVATE);
        if (preferences.getString("id_user", "") == null) {
            editUser();
        }
    }

    //Editar datos del formulario si existe el usuario
    private void editUser() {
        FormularyService formularyService = new FormularyService(getContext());
        formularyService.getFormulary("id_user");
        //agregar datos

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

    private void viewFormulary() {
        Intent mainActivity = new Intent(this.getContext(), RegisterActivity.class );
        startActivity(mainActivity);
        getActivity().onBackPressed();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_cerrarSesion){
            closed_session();
        }
        if (view.getId() ==  R.id.btn_formulary){
            viewFormulary();
        }
    }
}