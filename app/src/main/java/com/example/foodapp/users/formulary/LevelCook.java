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

public class LevelCook extends Fragment {
    private View view;
    private String [] nivel_cocina = {"Bajo", "Normal", "Avanzado"};
    private RadioGroup radioGroup;
    private FormularyViewModel formularyViewModel;
    public LevelCook() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_level_cook, container, false);
        initComponents();
        return view;
    }

    private void initComponents() {
        radioGroup = view.findViewById(R.id.radioGroup_cook);
        formularyViewModel = new ViewModelProvider(requireActivity()).get(FormularyViewModel.class);

        for (int i = 0; i < nivel_cocina.length; i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(nivel_cocina[i]);
            radioGroup.addView(radioButton);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId != -1){
                    RadioButton radioButton = view.findViewById(checkedId);
                    formularyViewModel.getUserFormulary().setNivel_cocina(radioButton.getText().toString());
                    System.out.println(formularyViewModel.getUserFormulary());
                }
            }
        });
    }

    public void SetViewModel(FormularyViewModel formularyViewModel) {
        this.formularyViewModel = formularyViewModel;
    }
}