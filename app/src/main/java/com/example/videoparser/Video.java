package com.example.videoparser;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Video {
    private String title;
    private String description;
    private Date duration;

    public Video(String title, String description, Date duration) {
        this.title = title;
        this.description = description;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss", Locale.ENGLISH);
        return dateFormat.format(duration);

    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }
}
