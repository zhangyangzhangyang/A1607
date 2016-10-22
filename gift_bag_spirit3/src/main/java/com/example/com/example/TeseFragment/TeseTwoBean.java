package com.example.com.example.TeseFragment;

/**
 * Created by 张样 on 2016/10/5.
 */
public class TeseTwoBean {
    private String id;
    private String path;
    private String name;

    public TeseTwoBean() {
        super();
    }

    public TeseTwoBean(String id, String path, String name) {
        this.id = id;
        this.path = path;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
