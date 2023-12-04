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
import com.example.foodapp.models.User;
import com.example.foodapp.models.UserFormulary;
import com.example.foodapp.service.FormularyService;
import com.example.foodapp.service.OnFormularyDataReceived;
import com.example.foodapp.service.OnUserDataReceived;
import com.example.foodapp.service.UserService;
import com.example.foodapp.users.LoginActivity;
import com.example.foodapp.users.formulary.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;


public class SettingsFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Button btnCerrarSesion, btnEstadisticas, btnFormulary, btn_profile;
    private TextView txtUsername, txtCorreo;
    private SharedPreferences preferences, pref_formulary;
    private UserFormulary userFormulary;
    private User ActualUser;

    public SettingsFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        initComponents();

        getUser();

        return view;
    }

    private void initComponents() {
        //inicializar componentes
        txtUsername = view.findViewById(R.id.textUsername);
        txtCorreo = view.findViewById(R.id.textEmail);
        btnCerrarSesion = view.findViewById(R.id.btn_cerrarSesion);
        btnEstadisticas = view.findViewById(R.id.btn_estadisticas);
        btnFormulary = view.findViewById(R.id.btn_formulary);
        btn_profile = view.findViewById(R.id.btn_profile);

        btnCerrarSesion.setOnClickListener(this::onClick);
        btnEstadisticas.setOnClickListener(this::onClick);
        btnFormulary.setOnClickListener(this::onClick);
        btn_profile.setOnClickListener(this::onClick);

    }

    private void getUser() {
        User user = new User();
        //Obtener identificador del id_user
        preferences = view.getContext().getSharedPreferences("sesion", Context.MODE_PRIVATE);
        String id_user = preferences.getString("id_user", null);
        System.out.println("El id_user es: "+ id_user);

        //obtener usuario de base de datos
        UserService userService = new UserService(getContext());
        userService.getUser(id_user, new OnUserDataReceived() {
            @Override
            public void onDataReceived(User usuario) {
                ActualUser = usuario;
                System.out.println("El usuario es: " + ActualUser.toString());
                editUser();
            }
            @Override
            public void onDataError(String errorMessage) {

            }
        });

    }

    private void getFormulary() {

        pref_formulary = view.getContext().getSharedPreferences("formularyUser", Context.MODE_PRIVATE);
        FormularyService formularyService = new FormularyService(getContext());

        String id_formulary = pref_formulary.getString("id_formulary",null);
        System.out.println("El identificador es: "+ id_formulary);
        formularyService.getFormulary(id_formulary, new OnFormularyDataReceived(){
            @Override
            public void onDataReceived(UserFormulary userf) {
                System.out.println(userf.toString());
                userFormulary = userf;
            }
            @Override
            public void onDataError(String errorMessage) {
                Toast.makeText(getContext(),  errorMessage,Toast.LENGTH_SHORT).show();
            }
        });

    }


    //Editar datos si existe el usuario
    private void editUser() {
        System.out.println("Usuario es: " +ActualUser.getUsername());
        System.out.println("Correo es: " +ActualUser.getUsername());
        txtUsername.setText(ActualUser.getUsername());
        txtCorreo.setText(ActualUser.getCorreo());
    }

    private void viewFormulary() {
        Intent formularyActivity = new Intent(this.getContext(), RegisterActivity.class);
        formularyActivity.putExtra("User_formulary", userFormulary); // Mover esta línea antes de startActivity
        startActivity(formularyActivity);
        getActivity().onBackPressed();
    }

    private void viewProfile(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        ProfileFragment formulario_fragment = new ProfileFragment();

        Bundle args = new Bundle();
        args.putSerializable("User", ActualUser);
        formulario_fragment.setArguments(args);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, formulario_fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_cerrarSesion) {
            showAlertDialog();
        } else if (id == R.id.btn_formulary) {
            viewFormulary();
        } else if (id == R.id.btn_profile) {
           viewProfile();
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