package com.example.foodapp.models;

public class Address {

    private String id_address;
    private Double latitude;
    private Double longitude;
    private String calle;
    private int interior;
    private int exterior;
    private String colonia;
    private String municipio;
    private String estado;
    private String codigoPostal;

    public Address() {
    }

    public String getId_address() {
        return id_address;
    }

    public void setId_address(String id_address) {
        this.id_address = id_address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getInterior() {
        return interior;
    }

    public void setInterior(int interior) {
        this.interior = interior;
    }

    public int getExterior() {
        return exterior;
    }

    public void setExterior(int exterior) {
        this.exterior = exterior;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String toString() {
        return
                calle +" "+ "#" + interior + "#" + exterior + " " +
                colonia + ' ' + municipio + ' ' +
                estado + ' ' +
                codigoPostal;
    }
}
