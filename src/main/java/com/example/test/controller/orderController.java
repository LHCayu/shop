package com.example.test.controller;

import com.example.test.bean.OrderItemsBean;
import com.example.test.bean.ReceiverBean;
import com.example.test.mapper.OrderItemsDAO;
import com.example.test.mapper.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class orderController {
    @Autowired
    OrderItemsDAO orderItemsDAO;

    @Autowired
    UserDAO userDAO;

    private int addressId=0;

    @RequestMapping("shop/order")
    public String orderIndex(Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            int uid = Integer.parseInt(session.getAttribute("userId").toString());
            List<OrderItemsBean> orderItemsBeans = orderItemsDAO.selOrder(uid);
            int sum = sum(orderItemsBeans);
            ReceiverBean receiverBean = orderItemsDAO.defaultAddress(uid);
            List<ReceiverBean> receiverBeans = userDAO.showAddress(uid);
            if (addressId != 0) {
                receiverBean = orderItemsDAO.selectAddress(addressId,uid);
            }
            System.out.println(receiverBean);
            System.out.println(orderItemsBeans);
            model.addAttribute("orderItemsBeans", orderItemsBeans);
            model.addAttribute("defaultAddress", receiverBean);
            model.addAttribute("showAllAddress", receiverBeans);
            model.addAttribute("orderItemsSum", sum);
            return "shop/order";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/login";
    }

    @RequestMapping("/abc")
    public String abc(int id, int num) {
//        System.out.println("cnm");
        System.out.println(id+" "+num);
        return "/login";
    }

    @RequestMapping("/updateOrder")
    public String updateOrder(int id, int num, HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println(id+" "+num);
        try {
            int uid = Integer.parseInt(session.getAttribute("userId").toString());
            orderItemsDAO.updateToOrderItems(id, num);
            return "redirect:shop/order";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/login";
    }

    @RequestMapping("/selAddress")
    public String selAddress(int id) {
        this.addressId = id;
        return "redirect:shop/order";
    }

    int sum(List<OrderItemsBean> orderItemsBeans) {
        int sum = 0;
        int sumAll=0;
        for (OrderItemsBean orderItemsBean : orderItemsBeans) {
            sum = orderItemsBean.getBuynum() * orderItemsBean.getPrice();
            sumAll += sum;
//            System.out.println(orderItemsBean.getName()+" "+sum);
        }
        return sumAll;
    }
}
