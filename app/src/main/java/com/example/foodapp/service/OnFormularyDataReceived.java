package com.example.foodapp.service;

import com.example.foodapp.models.UserFormulary;

public interface OnFormularyDataReceived {
    void onDataReceived(UserFormulary userFormulary);
    void onDataError(String errorMessage);
}
