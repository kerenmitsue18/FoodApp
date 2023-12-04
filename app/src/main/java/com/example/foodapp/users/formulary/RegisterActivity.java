package com.example.foodapp.users.formulary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.foodapp.HomeActivity;
import com.example.foodapp.R;
import com.example.foodapp.models.UserFormulary;
import com.example.foodapp.service.FormularyService;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager screenPager;
    RegisterAdapter registerAdapter;
    TabLayout tabIndicator;
    Button btnNext,btnGetStarted;
    int position = 0;
    Animation btnAnim;
    private FormularyViewModel sharedViewModel;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponents();

        mAuth = FirebaseAuth.getInstance();
        if(verifyFormulary()){
            System.out.println("Ya existe el usuario");
            updateFormularyUser();
        }

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

    private void updateFormularyUser() {
        //FormularyService formularyService = new FormularyService(getBaseContext());
        //SharedPreferences preferences = getBaseContext().getSharedPreferences("sesion", Context.MODE_PRIVATE);
        //UserFormulary userFormulary = formularyService.getFormulary(preferences.getString("id_user",""));
       // System.out.println(userFormulary.toString());
    }

    private boolean verifyFormulary() {
        SharedPreferences pref_formulary = getBaseContext().getSharedPreferences("formularyUser", Context.MODE_PRIVATE);
        if (pref_formulary.getString("id_formulary", "").isEmpty()) { //no existe formulario -> guardar
            return true;
        }
        return false;
    }

    private void loaddLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        btnGetStarted.setAnimation(btnAnim);
    }

    private void saveUserFormulary() {
        UserFormulary userFormulary = sharedViewModel.getUserFormulary();
        SharedPreferences preferences = getSharedPreferences("sesion", Context.MODE_PRIVATE);
        String id_user = preferences.getString("id_user", "");
        if (id_user != null) {
            userFormulary.setId_user(id_user);
            FormularyService formularySave = new FormularyService(this);
            String id_formulary = formularySave.save(userFormulary);
            SharedPreferences pref_formulary = this.getSharedPreferences("formularyUser", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref_formulary.edit();
            editor.putString("id_user", id_user);
            editor.putString("id_formulary", id_formulary);
            Boolean ex = editor.commit();
            Log.d("Formulario", "exitoso: ----------" +ex);

        }else{
            Toast.makeText(RegisterActivity.this, "Fallo el registro de formulario \n", Toast.LENGTH_SHORT).show();
        }
    }


    private void loadNext(){
        position = screenPager.getCurrentItem();
        if (position < registerAdapter.getCount()) {
            position++;
            screenPager.setCurrentItem(position);
        }
        if (position == registerAdapter.getCount() - 1) {
            loaddLastScreen();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btn_next){
          loadNext();
        }
        else if (id == R.id.btn_get_started){
            //Verificar si id de UserFormulary existe
            if (verifyFormulary()){
                // el usuario existe y actualizará los datos
                System.out.println("Usuario ya existente");
                updateFormularyUser();
            }else{
                // el usuario ingresa por primera vez
                System.out.println("Usuario por primer vez");
                saveUserFormulary();
            }
            Intent mainActivity = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(mainActivity);
            finish();
        }
    }


    public void initComponents(){

        sharedViewModel = new ViewModelProvider(this).get(FormularyViewModel.class);
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);
        screenPager = findViewById(R.id.screen_viewpager);
        btnNext.setOnClickListener(this::onClick);
        btnGetStarted.setOnClickListener(this::onClick);


        //si existe un userFormulario, traer los datos y mostrarlos
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("User_formulary")) {
            UserFormulary userFormulary = (UserFormulary) intent.getSerializableExtra("User_formulary");
            sharedViewModel.setUserFormulary(userFormulary); //le pasamos a todas las vistar el userFormulary
            editInitComponents();
        } else {
            System.out.println("No llego");
            sharedViewModel.setUserFormulary(new UserFormulary()); // le pasamos un userVacio
        }



        initPagerAdapter();
        initTabIndicator();
    }

    private void editInitComponents() {


    }

    private void initPagerAdapter() {
        tabIndicator.setupWithViewPager(screenPager);
        registerAdapter = new RegisterAdapter(getSupportFragmentManager(), sharedViewModel);
        registerAdapter.addFragment(new AgeFragment());
        registerAdapter.addFragment(new GenderFragment());
        registerAdapter.addFragment(new DietFragment());
        registerAdapter.addFragment(new WorkoutFragment());
        registerAdapter.addFragment(new GoalFragment());
        registerAdapter.addFragment(new LevelCook());
        registerAdapter.addFragment(new AlergiasFragment());
        registerAdapter.addFragment(new FoodFragment());
        registerAdapter.addFragment(new EnfermedadesFragment());
        screenPager.setAdapter(registerAdapter);
    }

    private void initTabIndicator() {
        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == registerAdapter.getCount()-1) {
                    loaddLastScreen();
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

    }

}