package com.example.foodapp.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.foodapp.R;


public class HomeFragment extends Fragment {
    private View view;

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
            initComponents();
            return view;
        }

    private void initComponents() {
    }

}
