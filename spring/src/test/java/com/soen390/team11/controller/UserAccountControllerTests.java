package com.soen390.team11.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import com.soen390.team11.dto.UserAccountDto;
import com.soen390.team11.entity.UserAccount;
import com.soen390.team11.service.UserService;
import java.util.List;
import org.hibernate.DuplicateMappingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserAccountControllerTests {

    @InjectMocks
    UserAccountController userAccountController;

    @Mock
    UserService userService;

    UserAccountDto userAccountDto = new UserAccountDto("usernameHere", "passwordHere",
        "TestUniqueEmail@test.com");

    @BeforeEach
    public void setup() {
        openMocks(this);
    }

    @Test
    public void createAccount() {
        doNothing().when(userService).createUser(userAccountDto);
        ResponseEntity<?> responseEntity = userAccountController.signUp(userAccountDto);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void accountConflict() {
        doThrow(DuplicateMappingException.class).when(userService).createUser(userAccountDto);
        ResponseEntity<?> responseEntity = userAccountController.signUp(userAccountDto);
        Assertions.assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }

    @Test
    void getAllUser() {
        when(userService.getAllUser()).thenReturn(List.of());
        ResponseEntity<?> responseEntity = userAccountController.getAllUser();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getLogUser() {
        UserAccount userAccount = new UserAccount("test", "test", "test@gmail.com", "ADMIN");
        when(userService.getLoggedUser()).thenReturn(userAccount);
        ResponseEntity<?> responseEntity = userAccountController.getLogUser();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void editUser() {
        UserAccount userAccount = new UserAccount("test", "test", "test@gmail.com", "ADMIN");
        when(userService.editUser(userAccountDto)).thenReturn(userAccount);
        ResponseEntity<?> responseEntity = userAccountController.editUser(userAccountDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getAllCustomersUnderLoggedUser_ReturnAListOfCustomers() {
        UserAccount userAccount = new UserAccount("test", "test", "test@gmail.com", "CUSTOMER");

        when(userService.getLoggedUser()).thenReturn(userAccount);
        ResponseEntity<?> responseEntity = userAccountController.getUserCustomers();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    void getAllPaymentsUnderLoggedUser_ReturnAListOfCustomers() {
        UserAccount userAccount = new UserAccount("test", "test", "test@gmail.com", "CUSTOMER");
        when(userService.getLoggedUser()).thenReturn(userAccount);
        ResponseEntity<?> responseEntity = userAccountController.getUserPayments();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
