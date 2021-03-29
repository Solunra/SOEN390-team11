package com.soen390.team11.controller;

import com.soen390.team11.service.MaterialInventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class MaterialInventoryControllerTest {

    MaterialInventoryController materialInventoryController;
    @Mock
    MaterialInventoryService materialInventoryService;

    @BeforeEach
    void setup()
    {
        openMocks(this);
        materialInventoryController = new MaterialInventoryController(materialInventoryService);
    }

    @Test
    void retrieveAllMatInInventory(){
        when(materialInventoryService.getAllMaterialInventory()).thenReturn(List.of());
        ResponseEntity<?> responseEntity = materialInventoryController.retrieveAllMatInInventory();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}