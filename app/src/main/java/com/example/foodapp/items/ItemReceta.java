package com.example.foodapp.items;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.foodapp.R;
import com.example.foodapp.models.Receta;

import java.io.Serializable;


public class ItemReceta extends Fragment implements Serializable {
    private View view;
    TextView txtNombre, txt_cal;
    public ItemReceta() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_item_receta, container, false);;
        initComponents();

        Bundle bundle = getArguments();
        if(bundle != null){
            Receta receta = (Receta) bundle.getSerializable("receta");
            updateItem(receta);
        }


        return view;
    }

    private void initComponents() {
            txtNombre = view.findViewById(R.id.txtNombre);
            txt_cal = view.findViewById(R.id.txt_cal);
    }

    private void updateItem(Receta receta) {
        txtNombre.setText(receta.getName());
        txt_cal.setText(receta.getCalories());
    }
}