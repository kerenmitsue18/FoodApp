package com.example.foodapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.models.Receta;

import java.util.ArrayList;

public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.ViewHolderReceta>  {

    private Context context;

    private ArrayList<Receta> recetas;
    final RecetaAdapter.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(Receta receta);
    }

    public RecetaAdapter(ArrayList<Receta> recetas, Context context, RecetaAdapter.OnItemClickListener listener) {
        this.recetas = recetas;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolderReceta onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_receta, parent, false);
        return new ViewHolderReceta(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderReceta holder, int position) {
        // Actualizar datos
        System.out.println("Aqui se actualizan datos" + recetas.get(position).toString());
        holder.bindData(recetas.get(position));

    }

    @Override
    public int getItemCount() {
        return recetas.size();
    }



    public class ViewHolderReceta extends RecyclerView.ViewHolder {

        public TextView txt_cal, txt_proteina, txt_HC, txt_AG, txtNombre;

        public ViewHolderReceta(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txt_cal = itemView.findViewById(R.id.txt_cal);
            txt_proteina = itemView.findViewById(R.id.txt_proteina);
            txt_HC = itemView.findViewById(R.id.txt_HC);
            txt_AG = itemView.findViewById(R.id.txt_AG);
        }

        void bindData(final Receta receta){
            txtNombre.setText(receta.getName());
            txt_cal.setText(receta.getCalories());
            txt_proteina.setText(receta.getProteins());
            txt_HC.setText(receta.getCarbohydrates());
            txt_AG.setText(receta.getFats());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(receta);

                }
            });
        }

    }

}
