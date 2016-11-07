package com.example.fox_pipaw.Bean;

/**
 * Created by 张样 on 2016/10/27.
 */
public class NewAndHotBean {
    private String descript;
    private String count;
    private String id;
    private String logo;
    private String name;
    private String visits;
    private String size;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NewAndHotBean(String descript, String count, String id, String logo, String name, String visits, String size,String type) {
        this.descript = descript;
        this.count = count;
        this.id = id;
        this.logo = logo;
        this.name = name;
        this.visits = visits;
        this.size = size;
        this.type = type;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVisits() {
        return visits;
    }

    public void setVisits(String visits) {
        this.visits = visits;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
