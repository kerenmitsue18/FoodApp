package com.example.foodapp.users.formulary;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodapp.R;
import com.example.foodapp.models.UserFormulary;
import com.example.foodapp.utils.SpeechText;
import com.example.foodapp.utils.Tokenizer;
import com.example.foodapp.utils.UtilsComponents;

import java.util.ArrayList;

public class FoodFragment extends Fragment implements UtilsComponents {

    private View view;
    private String [] no_food = {"Ninguno","Pimientos", "Huevos", "Hongos", "Cebollas", "Coliflor", "Gluten", "Lacteos", "Pasta", "Arroz", "Trigo"};
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    private FormularyViewModel formularyViewModel;
    private ArrayList<String> food_selected = new ArrayList<>();

    private ImageButton btn_microphone;
    private UserFormulary userFormulary;
    private SpeechText speechText;

    public FoodFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_food, container, false);
        initComponents();




        return view;
    }

    private void initComponents() {

        LinearLayout food_layout = view.findViewById(R.id.food_layout);
        btn_microphone = view.findViewById(R.id.btn_microphone);
        speechText = new SpeechText();

        formularyViewModel = new ViewModelProvider(requireActivity()).get(FormularyViewModel.class);
        userFormulary = formularyViewModel.getUserFormulary();


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

            btn_microphone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = speechText.createSpeechRecognizerIntent();
                    startActivityForResult(intent, speechText.getRECOGNIZER_CODE());
                }
            });

            checkBoxes.add(checkBox);
            food_layout.addView(checkBox);
        }



    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //formatear los chebox
        for (CheckBox checkbox: checkBoxes) {
            checkbox.setChecked(false);
        }
        speechText.processSpeechResults(requestCode, resultCode, data, new SpeechText.SpeechRecognitionCallback() {
            @Override
            public void onRecognitionResult(Intent data) {
                // Procesa los resultados aquí
                ArrayList<String> textList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                UpdateButtons(Tokenizer.tokenizer(textList.get(0)));
                userFormulary.setAlimentos(Tokenizer.tokenizer(textList.get(0)));
                System.out.println(userFormulary.toString());
            }
        });
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

    @Override
    public void updateRadioGroup(String valores) {

    }

    @Override
    public void UpdateButtons(ArrayList<String> valores) {
        for (String value: valores){
            for (CheckBox checkbox: checkBoxes) {
                if(checkbox.getText().equals(value) || checkbox.getText().toString().equals(SpeechText.capitalizarPrimeraLetra(value))){
                    checkbox.setChecked(true);
                }
            }
        }
    }
}