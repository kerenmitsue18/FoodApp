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

public class AgeFragment extends Fragment implements UtilsComponents {

    private View view;
    private final String [] edad = {"menos de 20 años","20 a 25 años", "25 a 30 años", "30 a 35 años", "35 a 40 años", "más de 40 años"};
    private RadioGroup radioGroup_age;
    private UserFormulary userFormulary;
    private FormularyViewModel formularyViewModel;
    private ImageButton btn_microphone;

    private SpeechText speechText;

    public AgeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_age, container, false);
        initComponents();

        //if(!userFormulary.getEdad().isEmpty()){
        //updateRadioGroup(userFormulary.getEdad());
        //}


        return view;
    }

    private void initComponents() {

        // Accede al ViewModel compartido
        formularyViewModel= new ViewModelProvider(requireActivity()).get(FormularyViewModel.class);
        userFormulary = formularyViewModel.getUserFormulary();

        //inicializa elementos
        btn_microphone = view.findViewById(R.id.btn_microphone);
        radioGroup_age = view.findViewById(R.id.radioGroup_age);
        speechText = new SpeechText();


        //Agregar su función
        for (String value : edad) {
            radioGroup_age.addView(newButton(value));
        }
        radioGroup_age.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1) {
                    RadioButton selectedRadioButton = view.findViewById(checkedId);
                    userFormulary.setEdad(selectedRadioButton.getText().toString());
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

    public RadioButton newButton(String name){
        RadioButton radioButton = new RadioButton(getContext());
        radioButton.setText(name);
        return radioButton;
    }

    public void SetViewModel(FormularyViewModel viewModel) {
        this.formularyViewModel = viewModel;
    }

    @Override
    public void updateRadioGroup(String valor) {
        for (int i = 0; i < radioGroup_age.getChildCount(); i++) {
            View radioButtonView = radioGroup_age.getChildAt(i);
            if (radioButtonView instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) radioButtonView;
                if (radioButton.getText().toString().equals(valor)) {
                    radioButton.setChecked(true);
                    //userFormulary.setEdad(radioButton.getText().toString()); //actualizar
                    break;
                }
            }
        }
    }

    @Override
    public void UpdateButtons(ArrayList<String> valores) {
    }
}