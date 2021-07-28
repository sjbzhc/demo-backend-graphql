package com.demobackend.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("login")
public class Login {

    @RequestMapping(method = POST)
    public void authenticate() {
    }
}
