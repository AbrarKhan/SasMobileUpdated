package com.sasmobile.appszone;

import java.io.Serializable;

/**
 * Created by Trainer on 06/09/2017.
 */

public class Event implements Serializable {

    private String eventName;
    private int eventPrice;
    private String eventDesc;
    private String eventVideoURL;
    private int eventImage;

    public int getEventImage() {
        return eventImage;
    }

    public void setEventImage(int eventImage) {
        this.eventImage = eventImage;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventPrice() {
        return eventPrice;
    }

    public void setEventPrice(int eventPrice) {
        this.eventPrice = eventPrice;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getEventVideoURL() {
        return eventVideoURL;
    }

    public void setEventVideoURL(String eventVideoURL) {
        this.eventVideoURL = eventVideoURL;
    }


}
