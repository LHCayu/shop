package com.example.test.mapper;

import com.example.test.bean.UserBean;

public interface UserMapper {
    UserBean getInfo(String name, String password);

//    UserBean setInfo(String username, String password, String gender,
//                     String email, String telephone, String introduce,
//                     int state, String role, String datatime);
}
