package com.soen390.team11.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MaterialInventoryControllerTest {
    @Autowired
    MaterialInventoryController materialInventoryController;
    @Test
    void retrieveAllMatInInventory(){
        ResponseEntity<?> responseEntity = materialInventoryController.retrieveAllMatInInventory();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}