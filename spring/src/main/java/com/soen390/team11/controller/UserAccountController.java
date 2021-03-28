package com.soen390.team11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.UserSignUpRequestDto;
import com.soen390.team11.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller for the account
 */
@RestController
@RequestMapping("/account")
public class UserAccountController {
    ObjectMapper objectMapper = new ObjectMapper();
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

    /**
     * get all user except logged in user
     * @return
     */
    @GetMapping("/allUser")
    public ResponseEntity<?> getAllUser(){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(userService.getAllUser()), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

    /**
     * get logged user
     * @return
     */
    @GetMapping("/loggedUser")
    public ResponseEntity<?> getLogUser(){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(userService.getLoggedUser()), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

    /**
     * edit user
     * @param userSignUpRequestDto
     * @return
     */
    @PostMapping("/edit")
    public ResponseEntity<?> editUser(@RequestBody UserSignUpRequestDto userSignUpRequestDto) {
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(userService.editUser(userSignUpRequestDto)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }
}
