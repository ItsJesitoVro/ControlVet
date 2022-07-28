package com.model;

public class Articulo {

    String Nombre, Descripcion, Precio,photo;

    public Articulo() {}

    public Articulo(String nombre, String descripcion, String precio, String photo) {
        Nombre = nombre;
        Descripcion = descripcion;
        Precio = precio;
        photo = photo;

    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}