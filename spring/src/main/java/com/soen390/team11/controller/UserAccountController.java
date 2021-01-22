package com.soen390.team11.controller;

import com.soen390.team11.dto.UserRequestDto;
import com.soen390.team11.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class UserAccountController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getUserAccount() {
        return "HelloWorld";
    }

    @GetMapping("/signin")
    public String signIn(@RequestBody UserRequestDto userModel) {
        System.out.println(userModel);
        return "sign in";
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody UserRequestDto userRequestDto) {
        userService.createUser(userRequestDto);
        System.out.println(userRequestDto);
        return "sign up";
    }
}
