package com.cxk.TwoFragment;

/**
 * Created by Administrator on 2016/10/10.
 */
public class GiftBean_2 {
    private String path;
    private String gname;
    private String operators;
    private String starttime;

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

    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public GiftBean_2(String path, String gname, String operators, String starttime) {
        this.path = path;
        this.gname = gname;
        this.operators = operators;
        this.starttime = starttime;
    }

    public GiftBean_2() {
        super();
    }
}
