package com.soen390.team11.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class UserAccountController {

    @GetMapping
    public String getUserAccount()
    {
        return "HelloWorld";
    }
}
