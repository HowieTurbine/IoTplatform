package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page")
public class PageController {
    @RequestMapping(value = "/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @RequestMapping(value = "/login")
    public String main() {
        return "signin";
    }
}
