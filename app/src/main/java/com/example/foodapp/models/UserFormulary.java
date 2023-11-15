package com.example.foodapp.models;

import java.util.List;

public class UserFormulary {

    private String id_formulario;
    private String id_user;
    private String edad;
    private String genero;

    private String dieta;
    private String actividadFisica;
    private String objetivo;
    private String nivel_cocina;
    private List<String> alergias;
    private List<String> alimentos;
    private List<String> enfermedades;

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId() {
        return id_formulario;
    }

    public void setId(String id) {
        this.id_formulario = id;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDieta() {
        return dieta;
    }

    public void setDieta(String dieta) {
        this.dieta = dieta;
    }

    public String getActividadFisica() {
        return actividadFisica;
    }

    public void setActividadFisica(String actividadFisica) {
        this.actividadFisica = actividadFisica;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getNivel_cocina() {
        return nivel_cocina;
    }

    public void setNivel_cocina(String nivel_cocina) {
        this.nivel_cocina = nivel_cocina;
    }

    public List<String> getAlergias() {
        return alergias;
    }

    public void setAlergias(List<String> alergias) {
        this.alergias = alergias;
    }

    public List<String> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(List<String> alimentos) {
        this.alimentos = alimentos;
    }

    public List<String> getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(List<String> enfermedades) {
        this.enfermedades = enfermedades;
    }

    @Override
    public String toString() {
        return "UserFormulary{" +
                "id='" + id_formulario + '\'' +
                ", id_user='" + id_user + '\'' +
                ", edad='" + edad + '\'' +
                ", genero='" + genero + '\'' +
                ", dieta='" + dieta + '\'' +
                ", actividadFisica='" + actividadFisica + '\'' +
                ", objetivo='" + objetivo + '\'' +
                ", nivel_cocina='" + nivel_cocina + '\'' +
                ", alergias=" + alergias +
                ", alimentos=" + alimentos +
                ", enfermedades=" + enfermedades +
                '}';
    }
}
