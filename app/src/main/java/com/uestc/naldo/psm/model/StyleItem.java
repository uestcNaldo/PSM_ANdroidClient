package com.uestc.naldo.psm.model;


public class StyleItem {

    private String name;

    private int imageId;

    public StyleItem(){}

    public StyleItem(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
