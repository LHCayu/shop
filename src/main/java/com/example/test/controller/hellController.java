package com.example.test.controller;

import com.example.test.bean.ProductsBean;
import com.example.test.mapper.ProductsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class hellController {
    @Autowired
    ProductsDAO productsDAO;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String sayHello(Model model) {
        model.addAttribute("time", DateFormat.getDateTimeInstance().format(new Date()));
        model.addAttribute("aa", "aa：");
        System.out.println(DateFormat.getDateTimeInstance().format(new Date()));
        return "index";
    }

}
