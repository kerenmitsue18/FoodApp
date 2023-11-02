package com.example.foodapp.users.formulary;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.foodapp.R;
import com.example.foodapp.models.ScreenItem;
import com.example.foodapp.models.UserFormulary;

import java.util.ArrayList;
import java.util.List;

public class IntroViewPagerAdapter extends PagerAdapter {
    private Context mContext ;
    private List<ScreenItem> mListScreen;

    private String textoSeleccionado = "";

    private UserFormulary userFormulary = new UserFormulary();

    public IntroViewPagerAdapter(Context mContext, List<ScreenItem> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }


    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ArrayList<String> options = new ArrayList<>();

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_screen, null);

        TextView title = layoutScreen.findViewById(R.id.intro_title);
        TextView description = layoutScreen.findViewById(R.id.intro_description);

        LinearLayout groupContainer = layoutScreen.findViewById(R.id.radioGroupContainer);

        title.setText(mListScreen.get(position).getTitle());
        description.setText(mListScreen.get(position).getDescription());

        List<String> opciones = mListScreen.get(position).getOpciones();

        // Verificamos si se requiere solo una opción
        if(mListScreen.get(position).isRadioGroup()){

            RadioGroup radioGroup = new RadioGroup(mContext);
            for (int i = 0; i < opciones.size(); i++) {
                radioGroup.addView(newButton(opciones.get(i)));
            }
            groupContainer.addView(radioGroup);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId != -1) {
                        RadioButton selectedRadioButton = layoutScreen.findViewById(checkedId);
                        textoSeleccionado = selectedRadioButton.getText().toString();
                    }
                }
            });

        } else{

            //agregar los botones al linearLayout
            for (int i = 0; i < opciones.size(); i++) {
                final RadioButton button = newButton(opciones.get(i));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (button.isChecked()) {
                            textoSeleccionado = button.getText().toString();
                            options.add(textoSeleccionado);
                        } else {
                            // Puedes realizar acciones adicionales si lo deseas
                        }
                    }
                });
                groupContainer.addView(button);
            }

        }
        container.addView(layoutScreen);

        if (position <= 7){
            System.out.println(position);
            SaveData(position);
        } else{
            SaveMultipleChoice(position, options);
            options.clear();
        }
        System.out.println(userFormulary.toString());
        return layoutScreen;

    }


    private RadioButton newButton(String opcion) {
        RadioButton radioButton = new RadioButton(mContext);
        radioButton.setText(opcion);
        radioButton.setBackgroundResource(R.drawable.radio_selector);
        radioButton.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        //modificar margenes
        int marginStartInDp = 16; // Tamaño del margen en dp
        int margin = (int) (marginStartInDp * mContext.getResources().getDisplayMetrics().density + 0.5f);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, margin, 0, 0);
        radioButton.setLayoutParams(params);
        return radioButton;
    }


    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    public void SaveData(int position){
        switch (position-1){
            case 1:
                userFormulary.setEdad(textoSeleccionado);
                break;
            case 2:
                userFormulary.setGenero(textoSeleccionado);
                break;
            case 3:
                userFormulary.setDieta(textoSeleccionado);
                break;
            case 4:
                userFormulary.setActividadFisica(textoSeleccionado);
                break;
            case 5:
                userFormulary.setObjetivo(textoSeleccionado);
                break;
            case 6:
                userFormulary.setNivel_cocina(textoSeleccionado);
                break;
        }
    }

    private void SaveMultipleChoice(int position, ArrayList<String> options) {
        switch (position-1){
            case 7:
                userFormulary.setAlergias(options);
            case 8:
                userFormulary.setAlimentos(options);
            case 9:
                userFormulary.setEnfermedades(options);
        }
    }

    public UserFormulary getUserFormulary() {
        return userFormulary;
    }
}
