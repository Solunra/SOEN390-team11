package com.soen390.team11.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * this is for the testing purpose of the token only would be remove after
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @GetMapping
    public String welcomePage(){
        return "welcome";
    }
}
