package com.example.foodapp.menu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodapp.ProfileFragment;
import com.example.foodapp.R;
import com.example.foodapp.models.UserFormulary;
import com.example.foodapp.service.FormularyService;
import com.example.foodapp.service.OnFormularyDataReceived;
import com.example.foodapp.users.LoginActivity;
import com.example.foodapp.users.formulary.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;


public class SettingsFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Button btnCerrarSesion, btnEstadisticas, btnFormulary, btn_profile;
    private SharedPreferences preferences, pref_formulary;
    private UserFormulary userFormulary;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        initComponents();
        return view;
    }

    private void initComponents() {
        //inicializar los datos del usuario
        TextView txtUsername = view.findViewById(R.id.textUsername);
        TextView txtCorreo = view.findViewById(R.id.textEmail);
        btnCerrarSesion = view.findViewById(R.id.btn_cerrarSesion);
        btnEstadisticas = view.findViewById(R.id.btn_estadisticas);
        btnFormulary = view.findViewById(R.id.btn_formulary);
        btn_profile = view.findViewById(R.id.btn_profile);

        btnCerrarSesion.setOnClickListener(this::onClick);
        btnEstadisticas.setOnClickListener(this::onClick);
        btnFormulary.setOnClickListener(this::onClick);
        btn_profile.setOnClickListener(this::onClick);


        //preferences = view.getContext().getSharedPreferences("sesion", Context.MODE_PRIVATE);
        //System.out.println(preferences.getString("username",null));
        //System.out.println(preferences.getString("email",null));
        //txtUsername.setText(preferences.getString("username",null).toString());
        //txtCorreo.setText(preferences.getString("email",null).toString());


        pref_formulary = view.getContext().getSharedPreferences("formularyUser", Context.MODE_PRIVATE);
        FormularyService formularyService = new FormularyService(getContext());
        String id_formulary = pref_formulary.getString("id_formulary",null);
        userFormulary = formularyService.getFormulary(id_formulary, new OnFormularyDataReceived(){
            @Override
            public void onDataReceived(UserFormulary userFormulary) {
                System.out.println("AQUI SE GUARDA INFOR");
                System.out.println(userFormulary.toString());
            }
            @Override
            public void onDataError(String errorMessage) {
                Toast.makeText(getContext(),  errorMessage,Toast.LENGTH_SHORT).show();
            }
        });



    }


    //Editar datos del formulario si existe el usuario
    private void editUser() {

        //agregar datos
    }

    private void viewFormulary() {
        Intent mainActivity = new Intent(this.getContext(), RegisterActivity.class );
        startActivity(mainActivity);
        getActivity().onBackPressed();
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_cerrarSesion) {
            showAlertDialog();
        } else if (id == R.id.btn_formulary) {
            viewFormulary();
        } else if (id == R.id.btn_profile) {
            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.frame_layout, new ProfileFragment()).addToBackStack(null);
            fragmentTransaction.commit();
        } else if (id == R.id.btn_estadisticas) {
            Toast.makeText(getContext(), "Estadisticas", Toast.LENGTH_SHORT).show();
        }
    }


    private void showAlertDialog() {

        //configuración de alertDialog para salir de sesión
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Cerrar sesión").setMessage("¿Desea salir de la cuenta?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                closed_session();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();

    }

    private void closed_session() {
        // Obtén una instancia de FirebaseAuth
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        //actualizar la sesión activa
        UpdatePreferencesAndNavigate();
    }

    public void UpdatePreferencesAndNavigate(){
        SharedPreferences preferences = requireContext().getSharedPreferences("sesion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("status", false);
        editor.commit();

        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        requireActivity().finish();
    }

}