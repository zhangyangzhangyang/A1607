package com.cxk.OneFragment;

/**
 * Created by Administrator on 2016/10/11.
 */
public class GiftBeanMessage {
    private String path;
    private String time;
    private String left;
    private String jieshi;
    private String gettype;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getJieshi() {
        return jieshi;
    }

    public void setJieshi(String jieshi) {
        this.jieshi = jieshi;
    }

    public String getGettype() {
        return gettype;
    }

    public void setGettype(String gettype) {
        this.gettype = gettype;
    }

    public GiftBeanMessage(String path, String time, String left, String jieshi, String gettype) {
        this.path = path;
        this.time = time;
        this.left = left;
        this.jieshi = jieshi;
        this.gettype = gettype;
    }

    public GiftBeanMessage() {
        super();
    }
}
