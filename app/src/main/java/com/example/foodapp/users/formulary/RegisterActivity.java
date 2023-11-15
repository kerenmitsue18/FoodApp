package com.example.foodapp.users.formulary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.foodapp.MainActivity;
import com.example.foodapp.R;
import com.example.foodapp.models.ScreenItem;
import com.example.foodapp.models.UserFormulary;
import com.example.foodapp.service.FormularyService;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0;
    Button btnGetStarted;
    Animation btnAnim;
    private List<String[]> opciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //ArrayList de opciones
        initOpcions();

        // init views
        initViews();

        //verificar si hay un usuario asignado
        SharedPreferences preferences = this.getSharedPreferences("sesion", Context.MODE_PRIVATE);
        String id_user = preferences.getString("id_user", null);

        if(id_user != null){
            Toast.makeText(RegisterActivity.this, id_user, Toast.LENGTH_SHORT).show();
        }



        // fill list screen
        final List<ScreenItem> mList = new ArrayList<>();
        //mList.add(new ScreenItem("Cuestionario", "Por favor contesta las siguientes preguntas que nos ayudarán a darte una mejor experiencia", null));
        mList.add(new ScreenItem("Edad", "Selecciona el rango de edad correspondiente: ", Arrays.asList(opciones.get(0)), true));
        mList.add(new ScreenItem("Genero", "Selecciona tu genero: ", Arrays.asList(opciones.get(1)), true));
        mList.add(new ScreenItem("Tipo de dieta", "¿Deseas realizar alguna dieta en especifico? ", Arrays.asList(opciones.get(5)), true));
        mList.add(new ScreenItem("Actividad física", "Selecciona la actividad física que realices: ", Arrays.asList(opciones.get(6)), true));
        mList.add(new ScreenItem("Objetivo", "Selecciona el objetivo que deseas obtener con ayuda de la aplicación: ", Arrays.asList(opciones.get(7)), true));
        mList.add(new ScreenItem("Nivel de cocina", "Selecciona el nivel de preparación tienes en las comidas: ", Arrays.asList(opciones.get(8)), true));
        mList.add(new ScreenItem("Alergias", "Selecciona alguna alergía que tengas a estos alimentos: ", Arrays.asList(opciones.get(2)), false));
        mList.add(new ScreenItem("Alimentos", "Selecciona algunos alimentos que no puedas comer: ", Arrays.asList(opciones.get(3)), false));
        mList.add(new ScreenItem("Enfermedades", "Si cuentas con una enfermedad, selecciona la correspondiente: ", Arrays.asList(opciones.get(4)), false));
        // setup viewpager
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);



        // setup tablayout with viewpager
        tabIndicator.setupWithViewPager(screenPager);

        // hide the action bar
        getSupportActionBar().hide();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = screenPager.getCurrentItem();
                if (position < mList.size()) {
                    position++;
                    screenPager.setCurrentItem(position);
                }
                if (position == mList.size() - 1) {
                    loaddLastScreen();
                }
            }
        });

        // tablayout add change listener
        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size()-1) {
                    loaddLastScreen();
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        // Get Started button click listener
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserFormulary userFormulary = introViewPagerAdapter.getUserFormulary();
                saveUserFormulary(userFormulary);
                Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(mainActivity);
                savePrefsData();
                finish();
            }
        });

        // make the activity on full screen
        /*
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent mainActivity = new Intent(getApplicationContext(), RegisterActivity.class );
        startActivity(mainActivity);

    }


    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend",false);
        return  isIntroActivityOpnendBefore;
    }

    */

    }

    private void saveUserFormulary(UserFormulary userFormulary) {
        //consultar el id_usuario
        SharedPreferences preferences = getSharedPreferences("sesion", Context.MODE_PRIVATE);
        String id_user = preferences.getString("id_user", "");
        if (id_user != null) {
            userFormulary.setId_user(id_user);
            FormularyService formularySave = new FormularyService(this);
            formularySave.save(userFormulary);
        }else{
            Toast.makeText(RegisterActivity.this, "Fallo el registro de formulario \n", Toast.LENGTH_SHORT).show();
        }
    }

    private void initOpcions() {
        opciones = new ArrayList<>();
        String[] edad = {"menos de 20 años","20 a 25 años", "25 a 30 años", "30 a 35 años", "35 a 40 años", "más de 40 años"};
        //Altura
        //Peso Actual
        String [] alergias = {"Ninguno", "Lactosa", "Huevos", "Frutos secos", "Gluten", "Pescado", "Mariscos", "Soja", "Citricos"};
        String [] no_alimentos = {"Ninguno","Pimientos", "Huevos", "Hongos", "cebollas", "Coliflor", "Trigo y gluten", "Lacteos", "Brócoli", "Pasta", "Arroz", "Fruta"};
        String [] genero = {"Hombre", "Mujer"};
        String [] enfermedades = {"Ninguno","Diabetes Tipo I", "Diabetes Tipo II", "Prediabetes"};
        String [] tipo_dieta = {"Comer de todo", "Vegetariana", "Vegana"};
        String [] nivel_actividad = {"Poca", "Normal", "Regular"};
        String [] objetivo = {"Peso objetivo", "Mantenerse en forma", "Dormir mejor", "Reducir el estres y la ansiedad"};
        String [] nivel_cocina = {"Bajo", "Normal", "Avanzado"};

        opciones.add(edad);
        opciones.add(genero);
        opciones.add(alergias);
        opciones.add(no_alimentos);
        opciones.add(enfermedades);
        opciones.add(tipo_dieta);
        opciones.add(nivel_actividad);
        opciones.add(objetivo);
        opciones.add(nivel_cocina);
    }

    private void loaddLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        btnGetStarted.setAnimation(btnAnim);
    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.commit();
    }

    public void initViews(){
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);
        screenPager = findViewById(R.id.screen_viewpager);
    }

}