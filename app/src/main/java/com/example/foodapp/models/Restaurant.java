package com.example.foodapp.models;

public class Restaurant {

    private String id_restaurant;
    private String name;
    private Address address;
    private String phone;
    private String email;

    public Restaurant() {
    }

    public String getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(String id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id_restaurant='" + id_restaurant + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
