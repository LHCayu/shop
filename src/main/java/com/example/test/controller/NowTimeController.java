package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.util.Date;

@Controller
public class NowTimeController {
    @RequestMapping("/nowTime")
    public String showTime(Model model) {
        System.out.println(DateFormat.getDateTimeInstance().format(new Date()));
        model.addAttribute("time", DateFormat.getDateTimeInstance().format(new Date()));
        return "a/aa";
    }
}
