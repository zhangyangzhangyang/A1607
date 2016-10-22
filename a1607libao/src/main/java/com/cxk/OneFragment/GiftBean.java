package com.cxk.OneFragment;

/**
 * Created by Administrator on 2016/10/8.
 */
public class GiftBean {
    private String id;
    private String path;
    private String gamename;
    private String giftname;
    private String left;
    private String time;

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

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }

    public String getGiftname() {
        return giftname;
    }

    public void setGiftname(String giftname) {
        this.giftname = giftname;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public GiftBean(String id,String path, String gamename, String giftname,
                    String left, String time) {
        super();
        this.id=id;
        this.path = path;
        this.gamename = gamename;
        this.giftname = giftname;
        this.left = left;
        this.time = time;
    }

    public GiftBean() {
        super();
        // TODO Auto-generated constructor stub
    }
}
