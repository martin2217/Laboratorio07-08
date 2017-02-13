package com.example.martin.laboratorio07_08;


import java.io.Serializable;

public class Reclamo implements Serializable{
    private Double latitud;
    private Double longitud;
    private String titulo;
    private String telefono;
    private String email;
    private String imagenPath;
    private String idMarcador;

    public void setIdMarcador(String idMarcador) {
        this.idMarcador = idMarcador;
    }

    public String getIdMarcador() {

        return idMarcador;
    }

    public Reclamo() {

    }
    public Reclamo(String titulo, String telefono, String email) {
        this.titulo = titulo;
        this.telefono = telefono;
        this.email = email;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImagenPath(String imagenPath) {
        this.imagenPath = imagenPath;
    }

    public Double getLatitud() {
        return latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getImagenPath() {
        return imagenPath;
    }
}
