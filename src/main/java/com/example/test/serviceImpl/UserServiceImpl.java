package com.example.test.serviceImpl;

import com.example.test.bean.UserBean;
import com.example.test.mapper.UserMapper;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserBean login(String name, String password) {
        return userMapper.getInfo(name, password);
    }

//    public UserBean regist(String username, String password, String gender,
//                           String email, String telephone, String introduce,
//                           int state, String role, String datatime) {
//        return userMapper.setInfo(username, password, gender,
//                email, telephone, introduce,
//                state, role, datatime);
//    }


}
