package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CalculatorController {

    @RequestMapping("/heart")
    public String heartIndex(){

        return "heart";
    }

    @RequestMapping("/jsq")
    public String jsqIndex(){

        return "Calculator";
    }

    @RequestMapping(value = "/jsqCalculate", method = RequestMethod.POST)
    public String jsqCalculator(String h8, String h3, String m60, String m30, String m15, String m10, String m5, String m1, Model model) {
//        if (h8.length()==0) {
//            System.out.println("cnm");
//        }
//        System.out.println(h8+" "+h3+" "+m60+" "+m30+" "+m15+" "+m10+" "+m5+" "+m1+" ");
        String time=sum(h8, h3, m60, m30, m15, m10, m5, m1);
        model.addAttribute("timeSum", time);
        return "Calculator";
    }

    String sum(String h8, String h3, String m60, String m30, String m15, String m10, String m5, String m1) {
        String t = null;
        int tl = 0, sum = 0;
        int day = 0, hour = 0, min = 0;
//        String time[] = {"8h", "3h", "60m", "30m", "15m", "10m", "5m", "1m"};
        int[] num = new int[8];
        if (h8.length()==0) {
            num[0] = 0;
        }else {
//            int a = Integer.parseInt(h8);
//            System.out.println("a=" + a);
            num[0] = Integer.parseInt(h8)*60*8;
        }
        if (h3.length()==0) {
            num[1] = 0;
        }else {
            num[1] = Integer.parseInt(h3)*60*3;
        }
        if (m60.length()==0) {
            num[2] = 0;
        }else {
            num[2] = Integer.parseInt(m60)*60;
        }
        if (m30.length()==0) {
            num[3] = 0;
        }else {
            num[3] = Integer.parseInt(m30)*30;
        }
        if (m15.length()==0) {
            num[4] = 0;
        }else {
            num[4] = Integer.parseInt(m15)*15;
        }
        if (m10.length()==0) {
            num[5] = 0;
        }else {
            num[5] = Integer.parseInt(m10)*10;
        }
        if (m5.length()==0) {
            num[6] = 0;
        }else {
            num[6] = Integer.parseInt(m5)*5;
        }
        if (m1.length()==0) {
            num[7] = 0;
        }else {
            num[7] = Integer.parseInt(m1);

        }
        for (int i = 0; i < num.length; i++) {
//            System.out.println(time[i] + ":" + num[i]);
            sum += num[i];
//            System.out.println(num[i]);
        }
//        System.out.println(sum);
        day = sum / 1440;
        hour = (sum % 1440) / 60;
        min = (sum % 1440) % 60;
        String time = day + "天" + hour + "小时" + min + "分钟";
        System.out.println(day + "天" + hour + "小时" + min + "分钟");
        return time;
    }
}
