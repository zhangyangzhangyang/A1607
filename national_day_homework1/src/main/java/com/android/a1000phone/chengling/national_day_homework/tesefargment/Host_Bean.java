package com.android.a1000phone.chengling.national_day_homework.tesefargment;

/**
 * Created by chengling on 2016/10/9.
 */
public class Host_Bean {
    private String id;
    private String logo;
    private String size;
    private String clicks;
    private String typename;
    private String name;

    public Host_Bean(String id, String logo, String size, String clicks, String typename, String name) {
        this.id = id;
        this.logo = logo;
        this.size = size;
        this.clicks = clicks;
        this.typename = typename;
        this.name = name;
    }

    public Host_Bean() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
