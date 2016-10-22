package com.example.day31_mvp_modle.model;

/**
 * 请求后台服务，验证用户名和密码是否正确
 * Created by 张样 on 2016/10/21.
 */
public class UserModel {
    public String login(String username,String password){
        if (username.equals("zhangsan")&&password.equals("123")){
            return "success";
        }
        return "failure";
    }
}
