package com.cxk.FourFragment;

/**
 * Created by Administrator on 2016/10/9.
 */
public class GiftBean4 {
    private String path;
    private String addtime;
    private String name;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GiftBean4(String path, String addtime, String name) {
        this.path = path;
        this.addtime = addtime;
        this.name = name;
    }

    public GiftBean4() {
        super();
    }
}
