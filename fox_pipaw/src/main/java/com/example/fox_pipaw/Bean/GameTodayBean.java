package com.example.fox_pipaw.Bean;

/**
 * Created by 张样 on 2016/10/25.
 */
public class GameTodayBean {

    private String logo;
    private String game_name;
    private String type_name;
    private String size;
    private String game_visits;
    private String descl;
    private String real_down_url;
    private String game_id;

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public GameTodayBean(String logo, String game_name, String type_name
            , String size, String game_visits, String descl, String real_down_url,String game_id) {
        this.logo = logo;
        this.game_name = game_name;
        this.type_name = type_name;
        this.size = size;
        this.game_visits = game_visits;
        this.descl = descl;
        this.real_down_url = real_down_url;
        this.game_id = game_id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getGame_visits() {
        return game_visits;
    }

    public void setGame_visits(String game_visits) {
        this.game_visits = game_visits;
    }

    public String getDescl() {
        return descl;
    }

    public void setDescl(String descl) {
        this.descl = descl;
    }

    public String getReal_down_url() {
        return real_down_url;
    }

    public void setReal_down_url(String real_down_url) {
        this.real_down_url = real_down_url;
    }
}
