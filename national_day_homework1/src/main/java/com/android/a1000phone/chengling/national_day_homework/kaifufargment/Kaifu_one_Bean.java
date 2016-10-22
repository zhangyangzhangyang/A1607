package com.android.a1000phone.chengling.national_day_homework.kaifufargment;

/**
 * Created by chengling on 2016/10/7.
 */
public class Kaifu_one_Bean {
    private String id;
    private String path;
    private String title;
    private String time;
    private String addtime;
    private String area;
    private String operate;

    public Kaifu_one_Bean(String id, String operate, String area, String time, String title, String path,String addtime) {
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

    public Kaifu_one_Bean() {
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
