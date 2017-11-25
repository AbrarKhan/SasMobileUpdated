package com.sasmobile.announcements;

import java.io.Serializable;

/**
 * Created by Trainer on 06/09/2017.
 */

public class Announcements implements Serializable {

    private String AnnouncementsName;

    public String getAnnouncementsName() {
        return AnnouncementsName;
    }

    public void setAnnouncementsName(String announcementsName) {
        AnnouncementsName = announcementsName;
    }
}
