package com.example.com.example.kaifuFragment;

/**
 * Created by 张样 on 2016/10/6.
 */
public class KaifuTwoBean {
    private String id;
    private String path;
    private String title;
    private String oprate;
    private String time;

    public KaifuTwoBean() {
        super();
    }

    public KaifuTwoBean(String id, String time, String oprate, String title, String path) {
        this.id = id;
        this.time = time;
        this.oprate = oprate;
        this.title = title;
        this.path = path;
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
}
