package com.abdul.reg_login_token_jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class HomeController {

    @GetMapping(path = "/admin")
    public String admin(){
        return "Hello Admin";
    }
    @GetMapping(path = "/user")
    public String helloUser(){
        return "Hello User";
    }
}
