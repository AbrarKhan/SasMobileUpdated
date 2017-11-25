package com.sasmobile.galleryupdated;

import java.io.Serializable;

/**
 * Created by abrarkhan on 10/24/17.
 */

public class Gallery implements Serializable {
    private int imageURL;
    private int videoURL;

    public int getImageURL() {
        return imageURL;
    }

    public void setImageURL(int imageURL) {
        this.imageURL = imageURL;
    }

    public int getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(int videoURL) {
        this.videoURL = videoURL;
    }
}
