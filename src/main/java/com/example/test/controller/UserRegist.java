package com.example.test.controller;

import com.example.test.bean.UserBean;
import com.example.test.mapper.UserDAO;
import com.example.test.service.UserService;
import javafx.scene.chart.PieChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserRegist {
    @Autowired
    UserDAO userDAO;

    @RequestMapping("/register")
    public String show(){
        return "register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String test(String username, String password, String gender, String email, String telephone, String introduce) {
//        String username = "啊啊";
//        String password = "aaa";
//        String gender = "男";
//        String email = "cbai@qq.cn";
//        String telephone = "123456";
//        String introduce = "dhaihoa";
//        System.out.println(username + " " + password + " " + sexRadios);
        int state = 0;
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String registerTime = format.format(date);
//        System.out.println(introduce);
        userDAO.regist(username, password, gender, email, telephone, introduce, state, registerTime);

//        List<UserBean> userBeans=userDAO.sel();
//        for (UserBean userBean : userBeans) {
//            System.out.println(userBean.getId()+" "+userBean.getName()+" "+userBean.getPassword()+" "+
//                    userBean.getGender()+" "+userBean.getEmail()+" "+userBean.getTelephone()+" "+
//                    userBean.getIntroduce()+" "+userBean.getState()+" "+userBean.getRegisterTime()+" ");
//        }
        return "redirect:login";
    }

}
