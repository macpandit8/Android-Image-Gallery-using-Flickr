/**
 *
 * PhotoListItem.java
 * Getting photos items in the flickr image data
 *
 */

package com.example.mayank.androidimagegalleryusingflickr;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PhotoListItem {

    @SerializedName("page")
    private int page;

    @SerializedName("pages")
    private int pages;

    @SerializedName("perpage")
    private int perpage;

    @SerializedName("total")
    private String total;

    @SerializedName("photo")
    private ArrayList<PhotoItem> photo;

    public int getPage() {
        return page;
    }

    public int getPages() {
        return pages;
    }

    public int getPerpage() {
        return perpage;
    }

    public String getTotal() {
        return total;
    }

    public ArrayList<PhotoItem> getPhotoList() {
        return photo;
    }

}
