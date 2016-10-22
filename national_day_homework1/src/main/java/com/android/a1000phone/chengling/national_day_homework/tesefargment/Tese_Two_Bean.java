package com.android.a1000phone.chengling.national_day_homework.tesefargment;

/**
 * Created by chengling on 2016/10/8.
 */
public class Tese_Two_Bean {
    private String path;
    private String iconurl;
    private String name;
    private String id;

    public Tese_Two_Bean(String path, String iconurl, String name, String id) {
        this.path = path;
        this.iconurl = iconurl;
        this.name = name;
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Tese_Two_Bean(String iconurl, String name) {
        super();
    }
}
