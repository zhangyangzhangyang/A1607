package com.cxk.TwoFragment;

/**
 * Created by Administrator on 2016/10/10.
 */
public class GiftBean2 {
    private String path;
    private String gname;
    private String linkurl;
    private String operators;
    private String area;

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

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public GiftBean2(String path, String gname, String linkurl, String operators, String area) {
        this.path = path;
        this.gname = gname;
        this.linkurl = linkurl;
        this.operators = operators;
        this.area = area;
    }

    public GiftBean2() {
        super();
    }
}
