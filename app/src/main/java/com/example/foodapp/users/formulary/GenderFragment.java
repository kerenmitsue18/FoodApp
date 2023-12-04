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
import com.example.foodapp.models.UserFormulary;
import com.example.foodapp.utils.SpeechText;
import com.example.foodapp.utils.UtilsComponents;

import java.util.ArrayList;

public class GenderFragment extends Fragment implements UtilsComponents {

    private String [] genero = {"Hombre", "Mujer"};
    private RadioGroup radioGroup_gender;
    private View view;

    private FormularyViewModel formularyViewModel;
    private ImageButton btn_microphone;
    private SpeechText speechText;
    private UserFormulary userFormulary;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_gender, container, false);
        initComponents();


        //if(!userFormulary.getGenero().isEmpty()){
        //    updateRadioGroup(userFormulary.getGenero());
        //}


        return view;
    }

    private void initComponents() {

        radioGroup_gender = view.findViewById(R.id.radioGroup_gender);
        btn_microphone = view.findViewById(R.id.btn_microphone);
        speechText = new SpeechText();

        //accede al vieModel compartido
        formularyViewModel = new ViewModelProvider(requireActivity()).get(FormularyViewModel.class);
        userFormulary = formularyViewModel.getUserFormulary();

        //inicialiazr ragioGroup
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

        btn_microphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = speechText.createSpeechRecognizerIntent();
                startActivityForResult(intent, speechText.getRECOGNIZER_CODE());
            }
        });

    }

    public void SetViewModel(FormularyViewModel formularyViewModel) {
        this.formularyViewModel = formularyViewModel;
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

    public static String capitalizarPrimeraLetra(String palabra) {
        return Character.toUpperCase(palabra.charAt(0)) + palabra.substring(1);
    }

    @Override
    public void updateRadioGroup(String valores) {
        for (int i = 0; i < radioGroup_gender.getChildCount(); i++) {
            View radioButtonView = radioGroup_gender.getChildAt(i);
            if (radioButtonView instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) radioButtonView;
                if (radioButton.getText().toString().equals(valores) || radioButton.getText().toString().equals(SpeechText.capitalizarPrimeraLetra(valores))) {
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