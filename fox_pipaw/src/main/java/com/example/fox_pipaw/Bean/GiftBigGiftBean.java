package com.example.fox_pipaw.Bean;

import java.util.List;

/**
 * Created by 张样 on 2016/10/26.
 */
public class GiftBigGiftBean {

    /**
     * data : [{"description":"礼包内容：伙伴修炼册*10、亲密丹*3、凝魂丹*1、1级宝石*1","ft_id":"15342","game_id":"1946","game_name":"大话西游","number":59,"remain":82.7,"soft_url":"1","title":"《大话西游手游》10月菊月秋霜礼包","type":"1"},{"description":"礼包内容：风神水1天，进阶丹绿3个，锻造石5500，一级精铁矿5，银子50000，御风神水3","ft_id":"15364","game_id":"1005006","game_name":"九阴真经3D","number":1,"remain":93.3,"soft_url":"1","title":"《九阴真经3D》秋冬温情盛宴礼包","type":"1"},{"description":"礼包内容：元宝68银币30000进阶礼包内容：元宝68银币30000进阶丹20丹20","ft_id":"15357","game_id":"1005634","game_name":"放开那三国2","number":226,"remain":1.3,"soft_url":"1","title":"《放开那三国2》新手礼包","type":"1"},{"description":"礼包内容：打开获得银锭*2、九转金丹*10，增加北海龙女、北海龙子伙伴7天使用时间","ft_id":"15298","game_id":"1690","game_name":"梦幻西游","number":152,"remain":87.3,"soft_url":"1","title":"《梦幻西游》手游十月情谊礼包","type":"1"},{"description":"礼包内容：200万金币x1、1.5倍经验药水x1、传送卷轴x10","ft_id":"15333","game_id":"1005226","game_name":"永恒纪元：戒","number":301,"remain":47.3,"soft_url":"1","title":"《永恒纪元：戒》独家金币礼包","type":"1"},{"description":"礼包内容：元宝×300、魂玉×100、刷新令×5","ft_id":"15344","game_id":"1003290","game_name":"道友请留步","number":12,"remain":83.6,"soft_url":"","title":"《道友请留步》秋冬暖心礼包","type":"1"},{"description":"礼包内容：100结晶*1，1000声望卷轴*3，1000成就卷轴*5，200000金币*5，坐骑进阶石*1","ft_id":"15365","game_id":"1005229","game_name":"天堂2：血盟","number":4,"remain":86.7,"soft_url":"1","title":"《天堂2血盟》秋冬出游安卓礼包","type":"1"},{"description":"五丝洋粉*20五香酱牛肉*20大力丸","ft_id":"15301","game_id":"2556","game_name":"倩女幽魂手游","number":61,"remain":55.1,"soft_url":"1","title":"《倩女幽魂》追爱三界礼包","type":"1"}]
     * errorCode : 0
     * errorMessage :
     */

    private int errorCode;
    private String errorMessage;
    /**
     * description : 礼包内容：伙伴修炼册*10、亲密丹*3、凝魂丹*1、1级宝石*1
     * ft_id : 15342
     * game_id : 1946
     * game_name : 大话西游
     * number : 59
     * remain : 82.7
     * soft_url : 1
     * title : 《大话西游手游》10月菊月秋霜礼包
     * type : 1
     */

    private List<DataBean> data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String description;
        private String ft_id;
        private String game_id;
        private String game_name;
        private int number;
        private double remain;
        private String soft_url;
        private String title;
        private String type;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getFt_id() {
            return ft_id;
        }

        public void setFt_id(String ft_id) {
            this.ft_id = ft_id;
        }

        public String getGame_id() {
            return game_id;
        }

        public void setGame_id(String game_id) {
            this.game_id = game_id;
        }

        public String getGame_name() {
            return game_name;
        }

        public void setGame_name(String game_name) {
            this.game_name = game_name;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public double getRemain() {
            return remain;
        }

        public void setRemain(double remain) {
            this.remain = remain;
        }

        public String getSoft_url() {
            return soft_url;
        }

        public void setSoft_url(String soft_url) {
            this.soft_url = soft_url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
