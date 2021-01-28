package com.soen390.team11.controller;

import com.soen390.team11.dto.UserRequestDto;
import com.soen390.team11.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return "sign in";
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserRequestDto userRequestDto) {
        try {
            System.out.println(userRequestDto.toString());
            userService.createUser(userRequestDto);
        }
        catch (Exception e) {
            System.out.println("Exception in user Account Controller \n"+e);
            return new ResponseEntity<String>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }
}
