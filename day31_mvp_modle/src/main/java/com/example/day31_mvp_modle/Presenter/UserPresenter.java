package com.example.day31_mvp_modle.Presenter;

import com.example.day31_mvp_modle.model.UserModel;

/**
 * Created by 张样 on 2016/10/21.
 */
public class UserPresenter {
    private UserModel userModel = new UserModel();
    public String login(String username,String password){
        if ("".equals(username)||"".equals(password)){
            return  "用户名密码不能为空";
        }
        String result = userModel.login(username, password);
        return result;
    }
}
