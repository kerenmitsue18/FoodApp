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


public class EnfermedadesFragment extends Fragment implements UtilsComponents {

    private String [] enfermedades = {"Ninguno","Diabetes tipo 1", "Diabetes tipo 2", "Prediabetes"};
    private View view;
    private RadioGroup radioGroup;
    private FormularyViewModel formularyViewModel;
    private SpeechText speechText;

    private ImageButton btn_microphone;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_enfermedades, container, false);
        initComponents();
        return view;
    }

    private void initComponents() {

        btn_microphone = view.findViewById(R.id.btn_microphone);
        radioGroup = view.findViewById(R.id.radioGroup_sick);
        speechText = new SpeechText();

        formularyViewModel = new ViewModelProvider(requireActivity()).get(FormularyViewModel.class);


        for (int i = 0; i < enfermedades.length; i++) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(enfermedades[i]);
            radioGroup.addView(radioButton);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId != -1) {
                    RadioButton radioButton = view.findViewById(checkedId);
                    formularyViewModel.getUserFormulary().setEnfermedades(radioButton.getText().toString());
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


    public void SetViewModel(FormularyViewModel formularyViewModel) {
        this.formularyViewModel = formularyViewModel;
    }

    @Override
    public void updateRadioGroup(String valor) {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            View radioButtonView = radioGroup.getChildAt(i);
            if (radioButtonView instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) radioButtonView;
                if (radioButton.getText().toString().equals(valor) || radioButton.getText().toString().equals(SpeechText.capitalizarPrimeraLetra(valor))) {
                    radioButton.setChecked(true);
                    break;
                }
            }
        }
    }

    @Override
    public void UpdateButtons(ArrayList<String> valores) {

    }
}