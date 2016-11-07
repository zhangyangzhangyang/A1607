package com.example.fox_pipaw.Bean;

import java.util.List;

/**
 * Created by 张样 on 2016/10/26.
 */
public class GiftGuessBean {

    /**
     * data : [{"game_id":"1005551","game_logo":"http://img.pipaw.net/pipaw/logo/2016/10/12/173a89324067c1b7eef4fb16dde6d0aa.jpg","game_name":"不良人2","game_visits":"22073","num":1},{"game_id":"1005765","game_logo":"http://img.pipaw.net/pipaw/logo/2016/10/14/cca2d62d27da3e33ca3bbe1b19906511.jpg","game_name":"怪物X联盟2","game_visits":"30617","num":1},{"game_id":"1004942","game_logo":"http://img.pipaw.net/pipaw/logo/2016/08/04/8ba44179abbb3e44ec1d3658b6a395c6.jpg","game_name":"大秦帝国ol","game_visits":"9680","num":4},{"game_id":"1930","game_logo":"http://img.pipaw.net/wy/logo/2016/10/25/9a83b579aa69fa888ceb073040975e01.png","game_name":"率土之滨","game_visits":"3900","num":8},{"game_id":"1004837","game_logo":"http://img.pipaw.net/pipaw/logo/2016/05/17/ca521312110feb5a13ac65864b44bf7d.jpg","game_name":"蜀山战纪之剑侠传奇","game_visits":"4218","num":1}]
     * errorCode : 0
     * errorMessage :
     */

    private int errorCode;
    private String errorMessage;
    /**
     * game_id : 1005551
     * game_logo : http://img.pipaw.net/pipaw/logo/2016/10/12/173a89324067c1b7eef4fb16dde6d0aa.jpg
     * game_name : 不良人2
     * game_visits : 22073
     * num : 1
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
        private String game_id;
        private String game_logo;
        private String game_name;
        private String game_visits;
        private int num;

        public String getGame_id() {
            return game_id;
        }

        public void setGame_id(String game_id) {
            this.game_id = game_id;
        }

        public String getGame_logo() {
            return game_logo;
        }

        public void setGame_logo(String game_logo) {
            this.game_logo = game_logo;
        }

        public String getGame_name() {
            return game_name;
        }

        public void setGame_name(String game_name) {
            this.game_name = game_name;
        }

        public String getGame_visits() {
            return game_visits;
        }

        public void setGame_visits(String game_visits) {
            this.game_visits = game_visits;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
