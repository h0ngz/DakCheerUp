package com.fourdrone.dakcheerup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/index")
public class AppController {
    @RequestMapping(method = RequestMethod.GET)
    public String getIndex(ModelMap model) {
        model.addAttribute("message", "Hello world!!!");
        return "index/index";
    }
}