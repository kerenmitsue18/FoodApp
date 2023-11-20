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

public class FoodFragment extends Fragment {

    private View view;
    private String [] no_food = {"Ninguno","Pimientos", "Huevos", "Hongos", "cebollas", "Coliflor", "Trigo y gluten", "Lacteos", "Brócoli", "Pasta", "Arroz", "Fruta"};
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    private FormularyViewModel formularyViewModel;
    private ArrayList<String> food_selected = new ArrayList<>();
    public FoodFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_food, container, false);
        LinearLayout food_layout = view.findViewById(R.id.food_layout);
        formularyViewModel = new ViewModelProvider(requireActivity()).get(FormularyViewModel.class);

        for (int i = 0; i < no_food.length; i++) {
            CheckBox checkBox = new CheckBox(getContext());
            checkBox.setText(no_food[i]);
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
                    formularyViewModel.getUserFormulary().setAlimentos(food_selected);
                }
            });
            checkBoxes.add(checkBox);
            food_layout.addView(checkBox);
        }


        return view;
    }



    private void removeChecked(CompoundButton compoundButton) {
        food_selected.remove(compoundButton.getText().toString());
    }

    private void addChecked(CompoundButton compoundButton) {
        food_selected.add(compoundButton.getText().toString());
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
                food_selected.clear();
                formularyViewModel.getUserFormulary().setAlergias(food_selected);
            }
        }
    }

    public void SetViewModel(FormularyViewModel formularyViewModel) {
        this.formularyViewModel = formularyViewModel;
    }
}