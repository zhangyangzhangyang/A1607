package com.android.a1000phone.chengling.national_day_homework.kaifufargment;

/**
 * Created by chengling on 2016/10/7.
 */
public class Kaice_one_Bean {
    private String id;
    private String path;
    private String title;
    private String oprate;
    private String time;

    public Kaice_one_Bean(String id, String path, String title, String oprate, String time) {
        this.id = id;
        this.path = path;
        this.title = title;
        this.oprate = oprate;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getOprate() {
        return oprate;
    }

    public void setOprate(String oprate) {
        this.oprate = oprate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Kaice_one_Bean() {
        super();
    }

}
