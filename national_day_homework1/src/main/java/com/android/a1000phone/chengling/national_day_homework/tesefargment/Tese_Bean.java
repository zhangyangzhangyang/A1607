package com.android.a1000phone.chengling.national_day_homework.tesefargment;

/**
 * Created by chengling on 2016/10/7.
 */
public class Tese_Bean {
    private String path;
    private String title;
    private String time;
    private String id;

    public Tese_Bean(String path, String title, String time, String id) {
        this.path = path;
        this.title = title;
        this.time = time;
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Tese_Bean() {
        super();
    }
}
