package com.soen390.team11.controller;

import com.soen390.team11.entity.UserRequestModel;
import com.soen390.team11.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class UserAccountController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String getUserAccount()
    {
        return "HelloWorld";
    }

    @GetMapping
    @RequestMapping("/signin")
    public String signIn(@RequestBody UserRequestModel userModel){
        System.out.println(userModel);
        return "sign in";
    }
    @PostMapping
    @RequestMapping("/signup")
    public String signUp(@RequestBody UserRequestModel userRequestModel){
        userService.createUser(userRequestModel);
        System.out.println(userRequestModel);
        return "sign up";
    }
}
