package com.soen390.team11.controller;

import com.soen390.team11.dto.UserSignUpRequestDto;
import com.soen390.team11.repository.UserAccountRepository;
import com.soen390.team11.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
     * Mapping with the endpoint POST /account/signup
     * receive the userSignUpRequestDto by convert the json file in the body to the object
     * @param userSignUpRequestDto
     * @return
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
