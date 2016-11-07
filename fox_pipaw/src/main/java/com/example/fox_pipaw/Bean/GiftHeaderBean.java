package com.example.fox_pipaw.Bean;

import java.util.List;

/**
 * Created by 张样 on 2016/10/26.
 */
public class GiftHeaderBean {

    /**
     * data : [{"img":"http://img.pipaw.net/big/ad/4d903e184c1ecfee782a73bd312306b9.jpg","title":"不良人2","type":3,"url":"1005551"},{"img":"http://img.pipaw.net/big/ad/2b2d2a737ddf9f2daac07fdfa9f7fbf1.jpg","title":"我叫MT3","type":3,"url":"1005371"},{"img":"http://img.pipaw.net/big/ad/e375a5fe9a55d9f15905dc3e9f1be16b.jpg","title":"异次元战姬","type":3,"url":"1004897"}]
     * errorCode : 0
     * errorMessage :
     */

    private int errorCode;
    private String errorMessage;
    /**
     * img : http://img.pipaw.net/big/ad/4d903e184c1ecfee782a73bd312306b9.jpg
     * title : 不良人2
     * type : 3
     * url : 1005551
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
        private String img;
        private String title;
        private int type;
        private String url;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
