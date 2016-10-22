package com.example.hot;

/**
 * Created by 张样 on 2016/10/5.
 */
public class HotBean {
    /**
     * listview的id
     */
    private String id;
    /**
     * 图片的网址
     */
    private String path;
    /**
     * 游戏名
     */
    private String gname;
    /**
     *游戏类型
     */
    private String typename;
    /**
     * 软件大小
     */
    private String size;
    /**
     * 玩的人数
     */
    private String people;

    public HotBean(String id, String path, String gname, String typename, String size, String people) {
        this.id = id;
        this.path = path;
        this.gname = gname;
        this.typename = typename;
        this.size = size;
        this.people = people;
    }

    public HotBean() {
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

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }
}
