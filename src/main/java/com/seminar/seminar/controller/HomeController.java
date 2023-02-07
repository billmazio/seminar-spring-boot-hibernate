package com.seminar.seminar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

    public String homePage(){
        return "index";
    }
}
