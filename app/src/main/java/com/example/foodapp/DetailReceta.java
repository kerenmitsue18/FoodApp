package com.example.foodapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.foodapp.models.Receta;


public class DetailReceta extends Fragment implements View.OnClickListener {

    private View view;
    private TextView txtName, txt_cal, txt_proteina, txt_HC, txt_AG, txt_ingredientes, txt_preparacion, porcion;
    private Button btn_cocinar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_receta, container, false);
        initComponents();


        if (getArguments() != null) {
            Receta receta = (Receta) getArguments().getSerializable("receta");
            System.out.println(receta);
            bindData(receta);
        }


        return view;
    }

    private void bindData(Receta receta) {
        txtName.setText(receta.getName());
        txt_cal.setText("Cal: " + receta.getCalories());
        txt_proteina.setText("P: "+ receta.getProteins());
        txt_HC.setText("HC: " +receta.getCarbohydrates());
        txt_AG.setText("AG: " +receta.getFats());
        txt_ingredientes.setText(receta.getIngredients());
        txt_preparacion.setText(receta.getInstructions());
        porcion.setText(receta.getPortion());
    }

    private void initComponents() {
        txtName = view.findViewById(R.id.txtName);
        txt_cal = view.findViewById(R.id.txt_cal);
        txt_proteina = view.findViewById(R.id.txt_proteina);
        txt_HC = view.findViewById(R.id.txt_HC);
        txt_AG = view.findViewById(R.id.txt_AG);
        txt_ingredientes = view.findViewById(R.id.txt_ingredientes);
        txt_preparacion = view.findViewById(R.id.txt_preparacion);
        porcion = view.findViewById(R.id.porcion);
        btn_cocinar = view.findViewById(R.id.btn_cocinar);
        btn_cocinar.setOnClickListener(this::onClick);

    }

    @Override
    public void onClick(View view) {

    }
}