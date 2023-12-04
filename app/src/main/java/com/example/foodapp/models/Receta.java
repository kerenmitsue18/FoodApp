package com.example.foodapp.models;

import java.io.Serializable;

public class Receta implements Serializable {
    private String name;
    private String category;
    private String ingredients;
    private String instructions;
    private String portion;
    private String calories;
    private String proteins;
    private String carbohydrates;
    private String fats;

    public Receta() {
    }
    public Receta(String name, String category, String ingredients, String instructions, String portion, String calories, String proteins, String carbohydrates, String fats) {
        this.name = name;
        this.category = category;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.portion = portion;
        this.calories = calories;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getProteins() {
        return proteins;
    }

    public void setProteins(String proteins) {
        this.proteins = proteins;
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(String carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String getFats() {
        return fats;
    }

    public void setFats(String fats) {
        this.fats = fats;
    }

    @Override
    public String toString() {
        return "Receta{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", instructions='" + instructions + '\'' +
                ", portion='" + portion + '\'' +
                ", calories='" + calories + '\'' +
                ", proteins='" + proteins + '\'' +
                ", carbohydrates='" + carbohydrates + '\'' +
                ", fats='" + fats + '\'' +
                '}';
    }
}
