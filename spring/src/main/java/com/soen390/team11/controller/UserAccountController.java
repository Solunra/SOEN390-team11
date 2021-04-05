package com.soen390.team11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.UserAccountDto;
import com.soen390.team11.service.UserService;
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
    private final UserService userService;

    public UserAccountController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Mapping with the endpoint POST /account/signup
     *
     * @param userAccountDto The request body with a sign up request informations
     * @return A status code
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserAccountDto userAccountDto) {
        Map<String, String > response = new HashMap<>();
        try{
            userService.createUser(userAccountDto);
            return new ResponseEntity<Object>(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            System.out.println("Exception in user Account Controller \n"+e);
            return new ResponseEntity<Object>(response, HttpStatus.CONFLICT);
        }
    }

    /**
     * get all user except logged in user
     * @return a list of users
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
     * @return a user object
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
     * @param userAccountDto user data to be updated
     * @return the updated user
     */
    @PostMapping("/edit")
    public ResponseEntity<?> editUser(@RequestBody UserAccountDto userAccountDto) {
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(userService.editUser(userAccountDto)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

    /**
     * Get logged user customers
     */
    @GetMapping("/customers")
    public ResponseEntity<?> getUserCustomers(){
        return new ResponseEntity<>( userService.getLoggedUser().getCustomers(), HttpStatus.OK);
    }

    /**
     * Get logged user payments
     */
    @GetMapping("/payments")
    public ResponseEntity<?> getUserPayments(){
        return new ResponseEntity<>(userService.getLoggedUser().getPayments(), HttpStatus.OK);
    }
}
