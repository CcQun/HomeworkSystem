package com.example.homework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author CcQun
 * @Date 2020/6/15 14:35
 */
@Controller
@RequestMapping("/jsp")
public class JspController {
    @RequestMapping("/login")
    public String index(){
        return "login";
    }
}
