package com.example.thabo.bhgjhg;

/**
 * Created by Thabo on 2016/05/03.
 */
public class NewsItem {
    private String heading;
    private String description;
    private String date;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "[ headline=" + heading + ", reporter Name=" + description + " , date=" + date + "]";
    }
}
