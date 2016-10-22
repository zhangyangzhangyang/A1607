package com.example.gift_bag_spirit;

/**
 * ListView的Bean
 * Created by 张样 on 2016/10/3.
 */
public class GiftBean {
    /**
     * listview的id
     */
    private Long id;
    /**
     * 图片的网址
     */
    private String path;
    /**
     * 游戏名
     */
    private String gname;
    /**
     *礼包名
     */
    private String giftName;
    /**
     * 剩余数量
     */
    private String residue;
    /**
     * 时间
     */
    private String time;

      public GiftBean(){
          super();
      }

    public GiftBean(Long id, String path, String gname, String giftName, String residue, String time) {
        this.id = id;
        this.path = path;
        this.gname = gname;
        this.giftName = giftName;
        this.residue = residue;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}