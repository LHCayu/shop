package com.example.test.controller;

import com.example.test.bean.ProductsBean;
import com.example.test.mapper.OrderItemsDAO;
import com.example.test.mapper.ProductsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShopController {
    @Autowired
    ProductsDAO productsDAO;

    @Autowired
    OrderItemsDAO orderItemsDAO;

    private String screenValue="全部商品";
    private String sortValue="编号";
//    private String alertNums="0";

    @RequestMapping("shop/index")
    public String shopIndex(Model model, HttpServletRequest request) {
        List<ProductsBean> productsBeans = productsDAO.sel();
//        for (ProductsBean productsBean : productsBeans) {
//            System.out.println(productsBean.getName()+"\t"+productsBean.getPrice());
//        }
        String screenName = null;
        switch (screenValue) {
            case "学习书籍":
                screenName = "study";
                productsBeans = productsDAO.screenBycategory(screenName);
                break;
            case "电子产品":
                screenName = "electronic";
                productsBeans = productsDAO.screenBycategory(screenName);
                break;
            case "网络小说":
                screenName = "novel";
                productsBeans = productsDAO.screenBycategory(screenName);
                break;
            default:
                break;
        }
        if (screenValue.equals("全部商品")) {
            switch (sortValue) {
                case "名称":
                    productsBeans = productsDAO.orderByNameALL();
                    break;
                case "价格低->高":
                    productsBeans = productsDAO.orderByPriceLowHighALL();
                    break;
                case "价格高->低":
                    productsBeans = productsDAO.orderByPriceHighLowALL();
                    break;
                default:
                    break;
            }
        } else {
            switch (sortValue) {
                case "名称":
                    productsBeans = productsDAO.orderByName(screenName);
                    break;
                case "价格低->高":
                    productsBeans = productsDAO.orderByPriceLowHigh(screenName);
                    break;
                case "价格高->低":
                    productsBeans = productsDAO.orderByPriceHighLow(screenName);
                    break;
                default:
                    break;
            }
        }

        model.addAttribute("products", productsBeans);
//        model.addAttribute("alertNums", alertNums);
        HttpSession session = request.getSession();
        session.setAttribute("screenValue", screenValue);
        session.setAttribute("sortnValue", sortValue);
//        System.out.println(screenValue+ ""+sortValue);
        return "shop/index";
    }

    @RequestMapping("/editNums")
    public String editNums(int id, int num,HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        System.out.println(id + " " + num);
        try {
            int uid = Integer.parseInt(session.getAttribute("userId").toString());
            if (orderItemsDAO.alertNums(id, uid).size() != 0) {
//                alertNums = "1";
                orderItemsDAO.updateBuyNums(id,num,uid);
                return "redirect:shop/index";
            }
            System.out.println(id+" "+num+" "+uid);
            orderItemsDAO.insertBuyNums(id,num,uid);
            return "redirect:shop/index";
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return "redirect:login";
    }

    @RequestMapping("/screenIndexProducts")
    public String screenIndexProducts(String screenValue) {
        this.screenValue = screenValue;
        return "redirect:shop/index";
    }

    @RequestMapping("/sortIndexProducts")
    public String sortIndexProducts(String sortValue) {
        this.sortValue = sortValue;
        return "redirect:shop/index";
    }

    @RequestMapping("/userScreen")
    public String userScreen(HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            int id = Integer.parseInt(session.getAttribute("userId").toString());
            return "redirect:shop/info";
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return "redirect:login";
    }

    @RequestMapping("/submitOrder")
    public String submitOrder(HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            int id = Integer.parseInt(session.getAttribute("userId").toString());
            return "redirect:shop/order";
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return "redirect:login";
    }

    @RequestMapping("/backIndex")
    public String backIndex() {
        return "redirect:shop/index";
    }

//    @RequestMapping("/backLogin")
//    public String backLogin() {
//        return "redirect:login";
//    }
}
