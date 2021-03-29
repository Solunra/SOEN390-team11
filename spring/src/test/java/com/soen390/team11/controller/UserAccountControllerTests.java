package com.soen390.team11.controller;

import com.soen390.team11.dto.UserSignUpRequestDto;
import com.soen390.team11.service.UserService;
import org.hibernate.DuplicateMappingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.openMocks;

public class UserAccountControllerTests {

    UserAccountController userAccountController;

    @Mock
    UserService userService;

    UserSignUpRequestDto userSignUpRequestDto = new UserSignUpRequestDto("usernameHere", "passwordHere", "TestUniqueEmail@test.com");

    @BeforeEach
    public void setup()
    {
        openMocks(this);
        userAccountController = new UserAccountController(userService);
    }

    @Test
    public void createAccount()
    {
        doNothing().when(userService).createUser(userSignUpRequestDto);
        ResponseEntity responseEntity = userAccountController.signUp(userSignUpRequestDto);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void accountConflict()
    {
        doThrow(DuplicateMappingException.class).when(userService).createUser(userSignUpRequestDto);
        ResponseEntity responseEntity = userAccountController.signUp(userSignUpRequestDto);
        Assertions.assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }

}
