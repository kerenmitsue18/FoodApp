package com.example.foodapp.models;

import java.util.List;

public class ScreenItem {
    String Title,Description;
    List<String> Opciones;

    boolean IsRadioGroup;
    public ScreenItem(String title, String description, List<String> opciones, boolean isRadioGroup) {
        Title = title;
        Description = description;
        Opciones = opciones;
        IsRadioGroup = isRadioGroup;
    }


    public String getTitle() {
        return Title;
    }
    public String getDescription() {
        return Description;
    }

    public List<String> getOpciones() {
        return Opciones;
    }

    public boolean isRadioGroup() {
        return IsRadioGroup;
    }
}
