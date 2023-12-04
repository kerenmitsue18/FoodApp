package com.example.foodapp.users.formulary;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodapp.R;
import com.example.foodapp.utils.SpeechText;
import com.example.foodapp.utils.UtilsComponents;

import java.util.ArrayList;

public class LevelCook extends Fragment implements UtilsComponents {
    private View view;
    private String [] nivel_cocina = {"Bajo", "Normal", "Avanzado"};
    private RadioGroup radioGroup;
    private FormularyViewModel formularyViewModel;
    private ImageButton btn_microphone;
    private SpeechText speechText;
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
        btn_microphone = view.findViewById(R.id.btn_microphone);
        formularyViewModel = new ViewModelProvider(requireActivity()).get(FormularyViewModel.class);
        speechText = new SpeechText();

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

        btn_microphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = speechText.createSpeechRecognizerIntent();
                startActivityForResult(intent, speechText.getRECOGNIZER_CODE());
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        speechText.processSpeechResults(requestCode, resultCode, data, new SpeechText.SpeechRecognitionCallback() {
            @Override
            public void onRecognitionResult(Intent data) {
                // Procesa los resultados aquí
                ArrayList<String> textList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                updateRadioGroup(textList.get(0));
            }
        });
    }


    @Override
    public void updateRadioGroup(String valores) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            View radioButtonView = radioGroup .getChildAt(i);
            if (radioButtonView instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) radioButtonView;
                if (radioButton.getText().toString().equals(valores)   || radioButton.getText().toString().equals(SpeechText.capitalizarPrimeraLetra(valores) )) {
                    radioButton.setChecked(true);
                    break;
                }
            }
        }
    }

    @Override
    public void UpdateButtons(ArrayList<String> valores) {
    }



    public void SetViewModel(FormularyViewModel formularyViewModel) {
        this.formularyViewModel = formularyViewModel;
    }
}