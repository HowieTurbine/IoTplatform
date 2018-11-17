package com.controller;

import com.service.DataService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping("/data")
    @ResponseBody
    String AccessControl(String key, int id) {
        return new DataService().getData(key, id);
    }

    @GetMapping("/all")
    @ResponseBody
    Map<String, Object> GetTableData() {
        return new DataService().getTableData();
    }

    @PostMapping("/login")
    @ResponseBody
    String login(HttpSession httpSession,String account, String password) {
        String result = new DataService().login(account, password);
        System.out.println(result);
        if(result.equals("OK"))
        {
            httpSession.setAttribute("status","login");
        }
        return result;
    }

    @PostMapping("/freeMode")
    @ResponseBody
    String ChangeFreeMode(int value, int id) {
        return new DataService().ChangeFreeMode(value, id);
    }

}
