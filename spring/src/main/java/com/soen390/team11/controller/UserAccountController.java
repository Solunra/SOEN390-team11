package com.soen390.team11.controller;

import com.soen390.team11.dto.UserSignUpRequestDto;
import com.soen390.team11.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest API controller
 * Mapping with the url http://localhost:8080/account
 */
@RestController
@RequestMapping("/account")
public class UserAccountController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getUserAccount() {
        return "HelloWorld";
    }

    /**
     * Mapping with the url http://localhost:8080/account/signup
     * with method post
     * receive the userSignUpRequestDto by convert the json file in the body to the object
     * @param userSignUpRequestDto
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserSignUpRequestDto userSignUpRequestDto) {
        try{
            userService.createUser(userSignUpRequestDto);
        }
        catch (Exception e){
            System.out.println("Exception in user Account Controller \n"+e);
            return new ResponseEntity<String>("cannot create", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<String>("success sign up", HttpStatus.CREATED);
    }
}
