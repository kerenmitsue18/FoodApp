package com.example.foodapp.users;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;
import com.example.foodapp.models.User;
import com.example.foodapp.service.UserService;
import com.example.foodapp.users.formulary.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth auth;
    private EditText txt_email, txt_password, txtusername;
    private Button signup_button;
    private TextView loginRedictText;
    private String id_user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initComponents();
    }

    private void saveSesion() {
        SharedPreferences preferences = getSharedPreferences("sesion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("id_user", id_user);
        editor.putString("username", String.valueOf(txtusername));
        editor.putString("email", String.valueOf(txt_email));
        editor.putString("password", String.valueOf(txt_password));
        editor.putBoolean("status", true);
        editor.apply();
    }



    public void Autentication(){
        String username = txtusername.getText().toString().trim();
        String user = txt_email.getText().toString().trim();
        String pass = txt_password.getText().toString().trim();

        if(username.isEmpty()){
            txt_email.setError("El username no puede estar vacio");
        }
        if(user.isEmpty()){
            txt_email.setError("El email no puede ser vacio");
        }
        if(pass.isEmpty()){
            txt_password.setError("La contraseña no puede ser vacio");
        }else{
            User user_ob = new User(username, user, pass);
            auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignUpActivity.this, "A ingresado correctamente", Toast.LENGTH_SHORT).show();

                        //almacenar en firebase el usuario
                        UserService service_user = new UserService(SignUpActivity.this);
                        id_user = service_user.save(user_ob);

                        //Salvar la sesión en android
                        saveSesion();
                        startActivity(new Intent(SignUpActivity.this, RegisterActivity.class));

                    }else{
                        Toast.makeText(SignUpActivity.this, "Fallo el registro \n" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.signup_button){
            Autentication();
        }
        else if(id == R.id.loginRedictText){
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
        }
    }

    public void initComponents(){
        auth = FirebaseAuth.getInstance();
        txt_email = findViewById(R.id.email);
        txt_password = findViewById(R.id.password);
        signup_button = findViewById(R.id.signup_button);
        loginRedictText = findViewById(R.id.loginRedictText);
        txtusername = findViewById(R.id.username);
        signup_button.setOnClickListener(this);
        loginRedictText.setOnClickListener(this);
    }
}
