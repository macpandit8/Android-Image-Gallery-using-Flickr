/**
 *
 * PhotoItem.java
 * Fetches bottom level of flickr image data
 * Each elements from flickr image data
 * which is used to construct URL of static image
 *
 */

package com.example.mayank.androidimagegalleryusingflickr;

import com.google.gson.annotations.SerializedName;

public class PhotoItem {

    @SerializedName("id")
    private String id;

    @SerializedName("owner")
    String owner;

    @SerializedName("secret")
    String secret;

    @SerializedName("server")
    String server;

    @SerializedName("farm")
    int farm;

    @SerializedName("title")
    String title;

    @SerializedName("ispublic")
    int isPublic;

    @SerializedName("isfriend")
    int isFriend;

    @SerializedName("isfamily")
    int isFamily;

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getSecret() {
        return secret;
    }

    public String getServer() {
        return server;
    }

    public int getFarm() {
        return farm;
    }

    public String getTitle() {
        return title;
    }

    public boolean getIsPublic() {

        boolean result = false;
        if(isPublic == 1) {
            result = true;
        }

        return result;
    }

    public boolean getIsFriend() {
        boolean result = false;
        if(isFriend == 1) {
            result = true;
        }

        return result;
    }

    public boolean getIsFamily() {
        boolean result = false;
        if(isFamily == 1) {
            result = true;
        }

        return result;
    }

}
