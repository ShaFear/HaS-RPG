package com.example.jereczem.hasrpg.view.activities;

/**
 * Created by jereczem on 02.08.15.
 */
public class ItemData {


    private String title;
    private int imageUrl;

    public ItemData(String title,int imageUrl){

        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public int getImageUrl() {
        return imageUrl;
    }
    // getters & setters
}

