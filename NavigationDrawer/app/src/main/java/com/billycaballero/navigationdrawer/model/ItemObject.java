package com.billycaballero.navigationdrawer.model;

/**
 * Created by billycaballero on 9/10/15.
 */
public class ItemObject {
    private String name;
    private int imageId;

    public ItemObject(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {

        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
