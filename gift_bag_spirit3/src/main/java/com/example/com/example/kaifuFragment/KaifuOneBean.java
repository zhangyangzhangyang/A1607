package com.example.com.example.kaifuFragment;

/**
 * Created by 张样 on 2016/10/6.
 */
public class KaifuOneBean {
    private String id;
    private String path;
    private String title;
    private String time;
    private String addtime;
    private String area;
    private String operate;

    public KaifuOneBean(String id, String operate, String area, String time, String title, String path,String addtime) {
        this.id = id;
        this.operate = operate;
        this.area = area;
        this.time = time;
        this.title = title;
        this.path = path;
        this.addtime = addtime;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public KaifuOneBean() {
        super();
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
