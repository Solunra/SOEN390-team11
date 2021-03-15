package com.soen390.team11.controller;

import com.soen390.team11.dto.UserSignUpRequestDto;
import com.soen390.team11.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for the account
 */
@RestController
@RequestMapping("/account")
public class UserAccountController {

    @Autowired
    private UserService userService;

    /**
     * Mapping with the endpoint POST /account/signup
     *
     * @param userSignUpRequestDto The request body with a sign up request informations
     * @return A status code
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserSignUpRequestDto userSignUpRequestDto) {
        Map<String, String > response = new HashMap<>();
        try{
            userService.createUser(userSignUpRequestDto);
            return new ResponseEntity<Object>(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            System.out.println("Exception in user Account Controller \n"+e);
            return new ResponseEntity<Object>(response, HttpStatus.CONFLICT);
        }
    }
}
