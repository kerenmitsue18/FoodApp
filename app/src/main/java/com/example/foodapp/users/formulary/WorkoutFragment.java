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

public class WorkoutFragment extends Fragment {
    private FormularyViewModel formularyViewModel;
    private  View view;
    private RadioGroup radioGroup;
    private String [] nivel_actividad = {"Poca", "Normal", "Regular"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_workout, container, false);
        initComponents();
        return view;
    }

    private void initComponents() {
        radioGroup = view.findViewById(R.id.radioGroup_workout);
        formularyViewModel = new ViewModelProvider(requireActivity()).get(FormularyViewModel.class);

        for (int i = 0; i < nivel_actividad.length; i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(nivel_actividad[i]);
            radioGroup.addView(radioButton);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId != -1){
                    RadioButton radioButton = view.findViewById(checkedId);
                    formularyViewModel.getUserFormulary().setActividadFisica(radioButton.getText().toString());
                    System.out.println(formularyViewModel.getUserFormulary());
                }
            }
        });
    }

    public void SetViewModel(FormularyViewModel formularyViewModel) {
        this.formularyViewModel = formularyViewModel;
    }
}