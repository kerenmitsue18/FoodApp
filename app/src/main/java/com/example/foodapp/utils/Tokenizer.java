package com.example.foodapp.utils;

import java.util.ArrayList;

public class Tokenizer {

    public static ArrayList<String> tokenizer(String cadena){
        ArrayList<String> palabras = new ArrayList<>();
        String[] tokens = cadena.split(" ");

        // Imprimir los tokens
        for (String token : tokens) {
            palabras.add(token);
        }
        return palabras;
    }
}
