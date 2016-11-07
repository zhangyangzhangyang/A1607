package com.example.fox_pipaw.Bean;

/**
 * Created by 张样 on 2016/10/24.
 */
public class BookBean {
    private String id;
    private String name;
    private String imgPath;

    public BookBean(String id, String imgPath, String name) {
        this.id = id;
        this.imgPath = imgPath;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
