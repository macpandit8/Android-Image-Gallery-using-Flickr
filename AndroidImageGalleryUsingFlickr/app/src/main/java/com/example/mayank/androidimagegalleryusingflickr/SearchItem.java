/**
 *
 * SearchItem.java
 * Fetching top level of flickr image data
 *
 */

package com.example.mayank.androidimagegalleryusingflickr;

import com.google.gson.annotations.SerializedName;

public class SearchItem {
    @SerializedName("photos")
    private PhotoListItem photos;

    @SerializedName("stat")
    private String stat;

    public SearchItem() {
        photos = new PhotoListItem();
        stat = "";
    }

    public PhotoListItem getPhotos() {
        return photos;
    }

    public String getStat() {
        return stat;
    }
}
