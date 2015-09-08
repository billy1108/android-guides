package com.billycaballero.butterknife.model;

/**
 * Created by billycaballero on 9/8/15.
 */
public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }
}
