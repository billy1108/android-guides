package com.example.billy.nativedatabase.models;

/**
 * Created by Billy on 24/10/2015.
 */
public class User {
    
    private String name;
    private String lastName;
    private String dni;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
