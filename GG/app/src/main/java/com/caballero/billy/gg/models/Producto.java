package com.caballero.billy.gg.models;

/**
 * Created by Billy on 13/02/2016.
 */
public class Producto {

    private String name;
    private String precio;

    public Producto(String name, String precio) {
        this.name = name;
        this.precio = precio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
