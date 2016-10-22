package com.android.a1000phone.chengling.national_day_homework;

/**
 * ListView的Bean
 * Created by chengling on 2016/10/5.
 */
public class GiftBean {
    //ListView的ID
    private long id;
    //图片的网址
    private String path;
    //游戏名
    private String gName;
    //礼包名
    private String giftName;
    //剩余数量
    private String residue;
    //时间
    private String time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getResidue() {
        return residue;
    }

    public void setResidue(String residue) {
        this.residue = residue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public GiftBean(long id, String path, String gName, String giftName, String residue, String time) {
        this.id = id;
        this.path = path;
        this.gName = gName;
        this.giftName = giftName;
        this.residue = residue;
        this.time = time;
    }

    public GiftBean() {
        super();
    }
}
