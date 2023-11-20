package com.example.foodapp.users.formulary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodapp.R;

import java.util.ArrayList;


public class AlergiasFragment extends Fragment{

    String [] alergias = {"Ninguno", "Lactosa", "Huevos", "Frutos secos", "Gluten", "Pescado", "Mariscos", "Soja", "Citricos"};
    private View view;
    private ArrayList<String> alergias_selected = new ArrayList<>();
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();;
    private FormularyViewModel formularyViewModel;

    public AlergiasFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_alergias, container, false);
        initComponents();
        return view;
    }

    private void initComponents() {

        LinearLayout alergias_layout = view.findViewById(R.id.alergias_layout);
        formularyViewModel = new ViewModelProvider(requireActivity()).get(FormularyViewModel.class);

        for (int i = 0; i < alergias.length; i++) {
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(alergias[i]);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    // Si se selecciona este CheckBox, desactiva los demás
                    if (isChecked) {
                        desactivarOtrosCheckBox(compoundButton);
                        addChecked(compoundButton);
                    } else{
                        activarCheckBoxes();
                        removeChecked(compoundButton);
                    }
                    formularyViewModel.getUserFormulary().setAlergias(alergias_selected);
                    System.out.println("****************** Las alergias seleccionadas son: \n"+ formularyViewModel.getUserFormulary().getAlergias());
                }
            });

            checkBoxes.add(checkBox);
            alergias_layout.addView(checkBox);
        }

    }

    private void removeChecked(CompoundButton compoundButton) {
        alergias_selected.remove(compoundButton.getText().toString());
    }

    private void addChecked(CompoundButton compoundButton) {
        alergias_selected.add(compoundButton.getText().toString());
    }

    private void activarCheckBoxes() {
        for (CheckBox checkBox : checkBoxes) {
            checkBox.setEnabled(true); // Activa el CheckBox
            checkBox.setChecked(false);
        }
    }

    private void desactivarOtrosCheckBox(CompoundButton checkBoxActual) {
        // Itera sobre los CheckBox y desactiva los que no sean el Ninguno
        for (CheckBox checkBox : checkBoxes) {
            if ("Ninguno" == checkBoxActual.getText()) {
                checkBox.setChecked(false);
                checkBoxActual.setEnabled(true);
                checkBoxActual.setChecked(true);
                checkBox.setEnabled(false);
                alergias_selected.clear();
            }
        }
    }

    public void SetViewModel(FormularyViewModel formularyViewModel) {
        this.formularyViewModel = formularyViewModel;
    }
}