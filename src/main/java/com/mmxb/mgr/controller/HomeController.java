package com.mmxb.mgr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Xing on 2015/11/24.
 */

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "manager_user";
    }

    @RequestMapping("/manager_user")
    public String manager_user(Model model,@RequestParam(value = "keyWord",defaultValue = "") String keyWord) {
        System.out.println(keyWord);
        return "manager_user";
    }

    @RequestMapping("/manager_system")
    public String manager_system() {
        return "manager_system";
    }

    @RequestMapping("/manager_shop")
    public String manager_shop() {
        return "manager_shop";
    }

    @RequestMapping("/manager_order")
    public String manager_order() {
        return "manager_order";
    }

    @RequestMapping("/manager_feedback")
    public String manager_feedback() {
        return "manager_feedback";
    }

    @RequestMapping("/manager_car")
    public String manager_car() {
        return "manager_car";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/edit_shop")
    public String edit_shop() {
        return "edit_shop";
    }

    @RequestMapping("/edit_order")
    public String edit_order() {
        return "edit_order";
    }

    @RequestMapping("/edit_feedback")
    public String edit_feedback() {
        return "edit_feedback";
    }

    @RequestMapping("/edit_car")
    public String edit_car() {
        return "edit_car";
    }

    @RequestMapping("/add_shop")
    public String add_shop() {
        return "add_shop";
    }

    @RequestMapping("/add_car")
    public String add_car() {
        return "add_car";
    }

}
