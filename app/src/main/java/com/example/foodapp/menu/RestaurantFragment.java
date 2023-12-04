package com.example.foodapp.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.foodapp.R;
import com.example.foodapp.models.Address;
import com.example.foodapp.models.Restaurant;
import com.example.foodapp.service.RestaurantService;

/**
 * @Author: Keren Mitsue Ramírez Vergara
 * Descripción: Fragment que visualiza las
 */
public class RestaurantFragment extends Fragment implements View.OnClickListener {

    private View view;
    private EditText txt_restaurante, txt_telefono, txt_correo, txt_latitud, txt_longitud, txt_calle, txt_exterior, txt_interior, txt_colonia,txt_municipio , txt_estado, txt_postal;
    private Button btn_guardar;
    public RestaurantFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_restaurant, container, false);
        initComponents();
        return view;
    }

    private void initComponents() {

        txt_restaurante = view.findViewById(R.id.txt_restaurante);
        txt_telefono = view.findViewById(R.id.txt_phone);
        txt_correo = view.findViewById(R.id.txt_email);
        txt_latitud = view.findViewById(R.id.txt_latitud);
        txt_longitud = view.findViewById(R.id.txt_longitud);
        txt_calle = view.findViewById(R.id.txt_street);
        txt_exterior = view.findViewById(R.id.txt_exterior);
        txt_interior = view.findViewById(R.id.txt_interior);
        txt_colonia = view.findViewById(R.id.txt_colonia);
        txt_estado = view.findViewById(R.id.txt_estado);
        txt_municipio = view.findViewById(R.id.txt_municipio);
        txt_postal = view.findViewById(R.id.txt_postal);

        btn_guardar = view.findViewById(R.id.btn_guardar);
        btn_guardar.setOnClickListener(this::onClick);

    }
    @Override
    public void onClick(View view) {

        Address address = new Address();
        address.setLatitude(Double.parseDouble(txt_latitud.getText().toString()));
        address.setLongitude(Double.parseDouble(txt_longitud.getText().toString()));
        address.setCalle(txt_calle.getText().toString());
        address.setInterior(Integer.parseInt(txt_interior.getText().toString()));
        address.setExterior(Integer.parseInt(txt_exterior.getText().toString()));
        address.setColonia(txt_colonia.getText().toString());
        address.setMunicipio(txt_municipio.getText().toString());
        address.setEstado(txt_estado.getText().toString());
        address.setCodigoPostal(txt_postal.getText().toString());

        Restaurant restaurant = new Restaurant();
        restaurant.setName(txt_restaurante.getText().toString());
        restaurant.setAddress(address);
        restaurant.setPhone(txt_telefono.getText().toString());
        restaurant.setEmail(txt_correo.getText().toString());


        addRestaurant(restaurant);
    }

    private void addRestaurant(Restaurant restaurant) {
        RestaurantService restaurantService = new RestaurantService(getContext());
        restaurantService.save(restaurant);

    }


}