package com.example.com.example.TeseFragment;

/**
 * Created by 张样 on 2016/10/5.
 */
public class TeseOneBean {
    private String path;
    private String title;
    private String time;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TeseOneBean(String path, String title, String time,String id) {
        this.path = path;
        this.title = title;
        this.time = time;
        this.id= id;
    }

    public TeseOneBean() {
        super();
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
}
