package com.example.foodapp.service;

import com.example.foodapp.models.User;

public interface OnUserDataReceived {

    void onDataReceived(User user);
    void onDataError(String errorMessage);

}
