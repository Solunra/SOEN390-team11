package com.soen390.team11.controller;

import com.soen390.team11.dto.UserSignUpRequestDto;
import com.soen390.team11.entity.UserAccount;
import com.soen390.team11.repository.UserAccountRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserAccountControllerTests {

    @Autowired
    UserAccountController userAccountController;
    @Autowired
    private UserAccountRepository userAccountRepository;


    UserSignUpRequestDto userSignUpRequestDto;

    @BeforeAll
    public void setup()
    {
        userSignUpRequestDto = new UserSignUpRequestDto("usernameHere", "passwordHere", "TestUniqueEmail@test.com");
    }

    @Test
    @Order(1)
    public void createAccount()
    {
        ResponseEntity responseEntity = userAccountController.signUp(userSignUpRequestDto);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    @Order(2)
    public void accountConflict()
    {
        ResponseEntity responseEntity = userAccountController.signUp(userSignUpRequestDto);
        Assertions.assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }

    @AfterAll
    public void delete()
    {
        UserAccount account = userAccountRepository.findByEmail(userSignUpRequestDto.getEmail());
        userAccountRepository.delete(account);
    }
}
