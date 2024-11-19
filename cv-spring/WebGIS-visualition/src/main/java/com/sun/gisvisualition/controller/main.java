package com.sun.gisvisualition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class main {
    @RequestMapping("")
    public String mian() {
        return "forward:system/login";
    }
}
