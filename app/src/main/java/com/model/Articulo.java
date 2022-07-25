package com.model;

public class Articulo {

    String Nombre, Descripcion, Precio;

    public Articulo() {}

    public Articulo(String nombre, String descripcion, String precio) {
        Nombre = nombre;
        Descripcion = descripcion;
        Precio = precio;
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
}