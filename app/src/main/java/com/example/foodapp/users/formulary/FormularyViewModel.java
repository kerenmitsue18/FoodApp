package com.example.foodapp.users.formulary;

import androidx.lifecycle.ViewModel;

import com.example.foodapp.models.UserFormulary;

public class FormularyViewModel extends ViewModel {
    private static UserFormulary userFormulary;

    public UserFormulary getUserFormulary() {
        return userFormulary;
    }

    public void setUserFormulary(UserFormulary userFormulary) {
        this.userFormulary = userFormulary;
    }
}
