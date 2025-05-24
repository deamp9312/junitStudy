package com.junitTest.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JunitController {

    @GetMapping("/t1")
    public String testA1(){

        return "test";
    }
}
