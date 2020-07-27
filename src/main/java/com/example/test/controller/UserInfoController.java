package com.example.test.controller;

import com.example.test.bean.ReceiverBean;
import com.example.test.bean.UserBean;
import com.example.test.mapper.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserInfoController {
    @Autowired
    UserDAO userDAO;

    @RequestMapping("shop/info")
    public String info(HttpServletRequest request, Model model) {
        try {
            HttpSession session = request.getSession();
//        System.out.println(session.getAttribute("userId"));
//        System.out.println(session.getAttribute("userName"));
            int id = Integer.parseInt(session.getAttribute("userId").toString());
            UserBean userBean = userDAO.sel(id);
            List<ReceiverBean> receiverBeans = userDAO.showAddress(id);
            model.addAttribute("userAddress", receiverBeans);
            model.addAttribute("userBean", userBean);
            return "shop/userInfo";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/login";
    }

    @RequestMapping("/addAddress")
    public String addAddress(String editUser,String editPhone,String editAddress,HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(session.getAttribute("userId").toString());
        userDAO.addAddressDAO(editUser, editPhone, editAddress, id);

        System.out.println(editUser+" "+editPhone+" "+editAddress+" "+id);
        return "redirect:shop/info";
    }

    @RequestMapping("backLogin")
    public String backLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userName",null);
        session.setAttribute("userId",null);
        return "redirect:/login";
    }
}
