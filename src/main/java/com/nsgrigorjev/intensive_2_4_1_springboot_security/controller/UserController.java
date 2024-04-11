package com.nsgrigorjev.intensive_2_4_1_springboot_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class WelcomeController {

    @RequestMapping(value = "/index", method = GET)
    public String welcomePage(){
        return "index";
    }
}
