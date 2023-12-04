package com.example.foodapp.menu;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.foodapp.DetailRestaurant;
import com.example.foodapp.R;
import com.example.foodapp.models.Restaurant;
import com.example.foodapp.service.RestaurantService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Keren Mitsue Ramírez Vergara
 * Descripción: Fragment que visualiza un maps con los restaurantes registrados en la aplicación
 */
public class PlaceFragment extends Fragment implements OnMapReadyCallback{

    private int MY_PERMISSIONS_REQUEST_READ_CONTACTS;
    private View view;
    private GoogleMap mMap;

    private LocationManager locationManager;
    private List<Restaurant> allRestaurants = new ArrayList<>();
    private boolean firstLocationUpdate = true;
    private LatLng myUbicacion;
    private DetailRestaurant detail_restaurant;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_place, container, false);
        initComponents();
        return view;
    }

    /**
     * Descripción: Inicializa el googleMap
     * @param googleMap
     */
    @SuppressLint("ServiceCast")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                myUbicacion = new LatLng(location.getLatitude(), location.getLongitude());

                if(mMap != null){
                    //mMap.addMarker(new MarkerOptions().position(myUbicacion).
                            //title("Mi ubicacion"));
                    //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.profile)));

                    if(firstLocationUpdate){
                        float defaultZoom = 14.0f;
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myUbicacion, defaultZoom));
                        firstLocationUpdate = false;
                    }
                }
            }
        };

        int permiso = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

        //añadir los restaurantes
        addRestaurants();
    }

    /**
     * Descripción: método que permite añadir la ubicación de los restaurantes al googleMaps
     */
    private void addRestaurants() {

        RestaurantService restaurantService = new RestaurantService(getContext());
        restaurantService.getAllRestaurants(new RestaurantService.OnDataRetrievedListener() {
            @Override
            public void onDataRetrieved(List<Restaurant> restaurantList) {
                allRestaurants = restaurantList;
                for (Restaurant restaurant: restaurantList) {
                    LatLng ubicacion = new LatLng(restaurant.getAddress().getLatitude(), restaurant.getAddress().getLongitude());
                    mMap.addMarker(new MarkerOptions().position(ubicacion)
                            .title(restaurant.getName()));
                            //.icon(BitmapDescriptorFactory.fromResource(R.mipmap.logo)).anchor(0.0f,0.0f));
                }


            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            Restaurant actualRestaurant;
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                String markerTitle = marker.getTitle();
                for (Restaurant restaurant: allRestaurants ) {
                    if (restaurant.getName().equals(markerTitle)) {
                        actualRestaurant = restaurant;
                        detail_restaurant.updateData(restaurant);
                        break;
                    }
                }

                return true;
            }
        });
    }

    /**
     * Descripción: inicializa los componentes del Fragment
     */
    private void initComponents() {
        detail_restaurant = (DetailRestaurant) getChildFragmentManager().findFragmentById(R.id.fragment_restaurant);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }




}