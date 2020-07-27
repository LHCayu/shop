package com.example.test.service;

import com.example.test.bean.UserBean;

public interface UserService {
    UserBean login(String name, String password);

//    UserBean regist(String username, String password, String gender,
//                    String email, String telephone, String introduce,
//                    int state, String role, String datatime);
}
