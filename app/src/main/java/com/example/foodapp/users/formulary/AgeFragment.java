package com.example.foodapp.users.formulary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodapp.R;
import com.example.foodapp.models.UserFormulary;

public class AgeFragment extends Fragment{

    private View view;
    private String [] edad = {"menos de 20 años","20 a 25 años", "25 a 30 años", "30 a 35 años", "35 a 40 años", "más de 40 años"};
    private RadioGroup radioGroup_age;
    private UserFormulary userFormulary;
    private FormularyViewModel formularyViewModel;
    private int position;

    public AgeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Accede al ViewModel compartido
        formularyViewModel= new ViewModelProvider(requireActivity()).get(FormularyViewModel.class);
        userFormulary = formularyViewModel.getUserFormulary();

        view = inflater.inflate(R.layout.fragment_age, container, false);
        radioGroup_age = view.findViewById(R.id.radioGroup_age);
        //init Components
        for (int i = 0; i < edad.length; i++){
            radioGroup_age.addView(newButton(edad[i]));
        }
        radioGroup_age.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1) {
                    RadioButton selectedRadioButton = view.findViewById(checkedId);
                    String textoSeleccionado = selectedRadioButton.getText().toString();
                    userFormulary.setEdad(textoSeleccionado);
                    System.out.println(userFormulary.toString());
                }
            }
        });



        return view;
    }

    public RadioButton newButton(String name){
        RadioButton radioButton = new RadioButton(getContext());
        radioButton.setText(name);
        return radioButton;
    }

    public void SetViewModel(FormularyViewModel viewModel) {
        this.formularyViewModel = viewModel;
    }


}