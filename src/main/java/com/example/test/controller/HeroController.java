package com.example.test.controller;

import com.example.test.bean.HeroBean;
import com.example.test.mapper.HeroDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HeroController {
    @Autowired
    HeroDAO heroDAO;
//    int start=0;
    int pageSize=0;

    @RequestMapping("/showHero")
    public String showHero(Model model, @RequestParam(value = "start",defaultValue = "0") int start) throws Exception {
        List<HeroBean> hb = heroDAO.selectAllHero(start);
        pageSize=getPageSize(heroDAO.countHero());
//        System.out.println(start+" "+pageSize);
        if (start + 5 > heroDAO.countHero()) {
            model.addAttribute("pageLimit", 0);
        } else {
            model.addAttribute("pageLimit", 1);
        }
        if (start - 5 >= 0) {
            model.addAttribute("firstPage", 1);
        } else {
            model.addAttribute("firstPage", 0);
        }
        model.addAttribute("hb", hb);
        model.addAttribute("start", start);
        model.addAttribute("lastPage", pageSize-1);
        return "showHero";
    }

    @RequestMapping("/updateHero")
    public String UpdateHero1(int id,String name) throws Exception {
//        System.out.println(id+" "+name);
        heroDAO.updateHero(id,name);
        return "redirect:showHero";
    }

    @RequestMapping("/insertHero")
    public String insertHero(String name) throws Exception {
//        System.out.println(name);
        heroDAO.insertHero(name);
        return "redirect:showHero";
    }

    @RequestMapping("/deleteHero")
    public String deleteHero(int id) throws Exception {
//        System.out.println(id);
        heroDAO.delete(id);
        return "redirect:showHero";
    }

    int getPageSize(int size) {
        int s=0;
        boolean a=size>0;
        boolean b = size % 5 == 0;
        if (a && b) {
            s = size / 5;
        } else {
            s = (size / 5) + 1;
        }
       return s;
    }
}
