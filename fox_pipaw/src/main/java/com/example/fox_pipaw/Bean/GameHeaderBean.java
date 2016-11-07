package com.example.fox_pipaw.Bean;

/**
 * Created by 张样 on 2016/10/25.
 */
public class GameHeaderBean {
    private String id;
    private String path;

    public GameHeaderBean(String id, String path) {
        this.id = id;
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
}
