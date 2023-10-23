package com.example.foodapp.users.formulary;

public class ScreenItem {
    String Title,Description;
    public ScreenItem(String title, String description) {
        Title = title;
        Description = description;
    }

    public String getTitle() {
        return Title;
    }
    public String getDescription() {
        return Description;
    }

}
