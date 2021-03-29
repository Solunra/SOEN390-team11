package com.soen390.team11.controller;

import com.soen390.team11.service.PartInventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.openMocks;

class PartInventoryControllerTest {
    PartInventoryController partInventoryController;
    @Mock
    PartInventoryService partInventoryService;

    @BeforeEach
    void setup()
    {
        openMocks(this);
        partInventoryController = new PartInventoryController((partInventoryService));
    }

    @Test
    void retrievePartInventory(){
        ResponseEntity<?> responseEntity = partInventoryController.retrieveAllPartInInventory();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}