package com.example.foodapp.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.DetailReceta;
import com.example.foodapp.R;
import com.example.foodapp.adapters.RecetaAdapter;
import com.example.foodapp.models.Receta;

import java.util.ArrayList;

/**
 *
 * @autor: Keren Mitsue Ramírez Vergara
 * Descripción: Fragment que visualiza el menú principal del usuario
 *
 */
public class HomeFragment extends Fragment {
    private View view;
    private ArrayList<Receta> recetas = new ArrayList<>();
    public RecyclerView recyclerView;

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
            addRecetas();


            RecetaAdapter adapter = new RecetaAdapter(recetas, getContext(), new RecetaAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Receta item) {
                    mostrarDetallesReceta(view,item);
                }
            });
            recyclerView = (RecyclerView) view.findViewById(R.id.list_recetas);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            recyclerView.setClickable(true);
            return view;
        }

    private void mostrarDetallesReceta(View view, Receta receta) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        DetailReceta detailFragment = new DetailReceta();

        Bundle bundle = new Bundle();
        bundle.putSerializable("receta", receta);
        detailFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, detailFragment).addToBackStack(null).commit();

    }


    private void addRecetas() {
        recetas.add(new Receta("Ensalada de Quinoa con Vegetales y Salmón", "Ensalada", "    1/2 taza de quinoa cruda,1 taza de brócoli,cortado en pequeños floretes,1 zanahoria,pelada y cortada en rodajas finas,1/2 pimiento rojo,cortado en tiras,1/4 de aguacate,cortado en cubos,1 cucharada de aceite de oliva extra virgen,Jugo de 1 limón,Sal y pimienta al gusto, 1 cucharadita de semillas de chía (opcional).",
                "Cocina la quinoa según las instrucciones del paquete. Deja enfriar. Precalienta el horno a 200°C" +
                "Coloca el brócoli, la zanahoria y el pimiento rojo en una bandeja para hornear, rocía con aceite de oliva, sal y pimienta al gusto. Asa en el horno durante 15-20 minutos o hasta que estén tiernos" +
                "En un tazón grande, mezcla la quinoa cocida, las verduras asadas y los cubos de aguacate."+
                "Prepara el aderezo mezclando el jugo de limón, aceite de oliva, sal y pimienta. Vierte sobre la ensalada y mezcla bien."+
                "Si lo deseas, agrega semillas de chía para un extra de fibra y omega-3."+
                "Sirve y disfruta.","1 porción ","400 kcal","10 gramos","45 gramos","20 gramos"));
        recetas.add(new Receta("Salmón al Horno con Espárragos", "Pescados y Mariscos", "    1/2 taza de quinoa cruda,1 taza de brócoli,cortado en pequeños floretes,1 zanahoria,pelada y cortada en rodajas finas,1/2 pimiento rojo,cortado en tiras,1/4 de aguacate,cortado en cubos,1 cucharada de aceite de oliva extra virgen,Jugo de 1 limón,Sal y pimienta al gusto, 1 cucharadita de semillas de chía (opcional).",
                "Cocina la quinoa según las instrucciones del paquete. Deja enfriar. Precalienta el horno a 200°C" +
                        "Coloca el brócoli, la zanahoria y el pimiento rojo en una bandeja para hornear, rocía con aceite de oliva, sal y pimienta al gusto. Asa en el horno durante 15-20 minutos o hasta que estén tiernos" +
                        "En un tazón grande, mezcla la quinoa cocida, las verduras asadas y los cubos de aguacate."+
                        "Prepara el aderezo mezclando el jugo de limón, aceite de oliva, sal y pimienta. Vierte sobre la ensalada y mezcla bien."+
                        "Si lo deseas, agrega semillas de chía para un extra de fibra y omega-3."+
                        "Sirve y disfruta.","1 porción ","400 kcal","10 gramos","45 gramos","20 gramos"));
        recetas.add(new Receta("Tacos de Pavo con Guacamole", "Ave", "    1/2 taza de quinoa cruda,1 taza de brócoli,cortado en pequeños floretes,1 zanahoria,pelada y cortada en rodajas finas,1/2 pimiento rojo,cortado en tiras,1/4 de aguacate,cortado en cubos,1 cucharada de aceite de oliva extra virgen,Jugo de 1 limón,Sal y pimienta al gusto, 1 cucharadita de semillas de chía (opcional).",
                "Cocina la quinoa según las instrucciones del paquete. Deja enfriar. Precalienta el horno a 200°C" +
                        "Coloca el brócoli, la zanahoria y el pimiento rojo en una bandeja para hornear, rocía con aceite de oliva, sal y pimienta al gusto. Asa en el horno durante 15-20 minutos o hasta que estén tiernos" +
                        "En un tazón grande, mezcla la quinoa cocida, las verduras asadas y los cubos de aguacate."+
                        "Prepara el aderezo mezclando el jugo de limón, aceite de oliva, sal y pimienta. Vierte sobre la ensalada y mezcla bien."+
                        "Si lo deseas, agrega semillas de chía para un extra de fibra y omega-3."+
                        "Sirve y disfruta.","1 porción ","400 kcal","10 gramos","45 gramos","20 gramos"));
        recetas.add(new Receta("Bol de Burrito Vegetariano", "Vegetariano", "    1/2 taza de quinoa cruda,1 taza de brócoli,cortado en pequeños floretes,1 zanahoria,pelada y cortada en rodajas finas,1/2 pimiento rojo,cortado en tiras,1/4 de aguacate,cortado en cubos,1 cucharada de aceite de oliva extra virgen,Jugo de 1 limón,Sal y pimienta al gusto, 1 cucharadita de semillas de chía (opcional).",
                "Cocina la quinoa según las instrucciones del paquete. Deja enfriar. Precalienta el horno a 200°C" +
                        "Coloca el brócoli, la zanahoria y el pimiento rojo en una bandeja para hornear, rocía con aceite de oliva, sal y pimienta al gusto. Asa en el horno durante 15-20 minutos o hasta que estén tiernos" +
                        "En un tazón grande, mezcla la quinoa cocida, las verduras asadas y los cubos de aguacate."+
                        "Prepara el aderezo mezclando el jugo de limón, aceite de oliva, sal y pimienta. Vierte sobre la ensalada y mezcla bien."+
                        "Si lo deseas, agrega semillas de chía para un extra de fibra y omega-3."+
                        "Sirve y disfruta.","1 porción ","400 kcal","10 gramos","45 gramos","20 gramos"));
        recetas.add(new Receta("Pollo a la Parrilla con Ensalada de Brócoli", "Aves", "    1/2 taza de quinoa cruda,1 taza de brócoli,cortado en pequeños floretes,1 zanahoria,pelada y cortada en rodajas finas,1/2 pimiento rojo,cortado en tiras,1/4 de aguacate,cortado en cubos,1 cucharada de aceite de oliva extra virgen,Jugo de 1 limón,Sal y pimienta al gusto, 1 cucharadita de semillas de chía (opcional).",
                "Cocina la quinoa según las instrucciones del paquete. Deja enfriar. Precalienta el horno a 200°C" +
                        "Coloca el brócoli, la zanahoria y el pimiento rojo en una bandeja para hornear, rocía con aceite de oliva, sal y pimienta al gusto. Asa en el horno durante 15-20 minutos o hasta que estén tiernos" +
                        "En un tazón grande, mezcla la quinoa cocida, las verduras asadas y los cubos de aguacate."+
                        "Prepara el aderezo mezclando el jugo de limón, aceite de oliva, sal y pimienta. Vierte sobre la ensalada y mezcla bien."+
                        "Si lo deseas, agrega semillas de chía para un extra de fibra y omega-3."+
                        "Sirve y disfruta.","1 porción ","458 kcal","20 gramos","48 gramos","20 gramos"));
        recetas.add(new Receta("Sopa de Lentejas con Tomate y Espinacas", "Sopas y Guisos", "    1/2 taza de quinoa cruda,1 taza de brócoli,cortado en pequeños floretes,1 zanahoria,pelada y cortada en rodajas finas,1/2 pimiento rojo,cortado en tiras,1/4 de aguacate,cortado en cubos,1 cucharada de aceite de oliva extra virgen,Jugo de 1 limón,Sal y pimienta al gusto, 1 cucharadita de semillas de chía (opcional).",
                "Cocina la quinoa según las instrucciones del paquete. Deja enfriar. Precalienta el horno a 200°C" +
                        "Coloca el brócoli, la zanahoria y el pimiento rojo en una bandeja para hornear, rocía con aceite de oliva, sal y pimienta al gusto. Asa en el horno durante 15-20 minutos o hasta que estén tiernos" +
                        "En un tazón grande, mezcla la quinoa cocida, las verduras asadas y los cubos de aguacate."+
                        "Prepara el aderezo mezclando el jugo de limón, aceite de oliva, sal y pimienta. Vierte sobre la ensalada y mezcla bien."+
                        "Si lo deseas, agrega semillas de chía para un extra de fibra y omega-3."+
                        "Sirve y disfruta.","1 porción ","586 kcal","89 gramos","75 gramos","20 gramos"));
        recetas.add(new Receta("Tazón de Batata Asada con Garbanzos y Espinacas", "Vegetariano", "    1/2 taza de quinoa cruda,1 taza de brócoli,cortado en pequeños floretes,1 zanahoria,pelada y cortada en rodajas finas,1/2 pimiento rojo,cortado en tiras,1/4 de aguacate,cortado en cubos,1 cucharada de aceite de oliva extra virgen,Jugo de 1 limón,Sal y pimienta al gusto, 1 cucharadita de semillas de chía (opcional).",
                "Cocina la quinoa según las instrucciones del paquete. Deja enfriar. Precalienta el horno a 200°C" +
                        "Coloca el brócoli, la zanahoria y el pimiento rojo en una bandeja para hornear, rocía con aceite de oliva, sal y pimienta al gusto. Asa en el horno durante 15-20 minutos o hasta que estén tiernos" +
                        "En un tazón grande, mezcla la quinoa cocida, las verduras asadas y los cubos de aguacate."+
                        "Prepara el aderezo mezclando el jugo de limón, aceite de oliva, sal y pimienta. Vierte sobre la ensalada y mezcla bien."+
                        "Si lo deseas, agrega semillas de chía para un extra de fibra y omega-3."+
                        "Sirve y disfruta.","1 porción ","250 kcal","80 gramos","158 gramos","20 gramos"));

    }

}
