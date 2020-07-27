package com.example.test.controller;

import com.example.test.bean.UserBean;
import com.example.test.mapper.UserDAO;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserDAO userDAO;

    @RequestMapping("/login")
    public String show() {
        return "login";
    }

    @RequestMapping(value = "/loginIn", method = RequestMethod.POST)
    public String login(String username, String password, HttpServletRequest request) {
        UserBean userBean = userDAO.login(username);
//        System.out.println(userBean.getName() + " " + userBean.getPassword());
        if (userBean.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("userName",username);
            session.setAttribute("userId",userBean.getId());
            return "redirect:shop/index";
        } else {
            return "error";
        }
    }

}
