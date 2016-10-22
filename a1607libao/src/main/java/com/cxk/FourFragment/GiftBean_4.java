package com.cxk.FourFragment;

/**
 * Created by Administrator on 2016/10/9.
 */
public class GiftBean_4 {
    private String path;
    private String name;
    private String authorimg;

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

    public String getAuthorimg() {
        return authorimg;
    }

    public void setAuthorimg(String authorimg) {
        this.authorimg = authorimg;
    }

    public GiftBean_4(String path, String name, String authorimg) {
        this.path = path;
        this.name = name;
        this.authorimg = authorimg;
    }

    public GiftBean_4() {
        super();
    }
}
