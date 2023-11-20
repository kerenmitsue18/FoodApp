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

public class GenderFragment extends Fragment {

    private String [] genero = {"Hombre", "Mujer"};
    private RadioGroup radioGroup_gender;
    private View view;

    private FormularyViewModel formularyViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_gender, container, false);
        initComponents();
        return view;
    }

    private void initComponents() {
        radioGroup_gender = view.findViewById(R.id.radioGroup_gender);
        formularyViewModel = new ViewModelProvider(requireActivity()).get(FormularyViewModel.class);

        for (int i = 0; i < genero.length; i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(genero[i]);
            radioGroup_gender.addView(radioButton);
        }
        radioGroup_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId != -1){
                    RadioButton radioButton = view.findViewById(checkedId);
                    formularyViewModel.getUserFormulary().setGenero(radioButton.getText().toString());
                    System.out.println(formularyViewModel.getUserFormulary());
                }
            }
        });
    }

    public void SetViewModel(FormularyViewModel formularyViewModel) {
        this.formularyViewModel = formularyViewModel;
    }


}