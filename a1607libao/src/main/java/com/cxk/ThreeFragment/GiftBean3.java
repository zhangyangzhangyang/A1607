package com.cxk.ThreeFragment;

/**
 * Created by Administrator on 2016/10/9.
 */
public class GiftBean3 {
    private String path;
    private String name;
    private String typename;
    private String size;
    private String  clicks;

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

    public String getClicks() {
        return clicks;
    }

    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    public GiftBean3() {
        super();

    }

    public GiftBean3(String path, String name, String typename, String size, String clicks) {
        this.path = path;
        this.name = name;
        this.typename = typename;
        this.size = size;
        this.clicks = clicks;
    }
}
