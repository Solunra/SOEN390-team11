package com.soen390.team11.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import com.soen390.team11.constant.MachineryState;
import com.soen390.team11.dto.ProductMachineryDto;
import com.soen390.team11.entity.ProductMachinery;
import com.soen390.team11.service.PartInventoryService;
import com.soen390.team11.service.ProductInventoryService;
import com.soen390.team11.service.ProductMachineryService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProductMachineryControllerTest {

    ProductMachineryController productMachineryController;

    @Mock
    ProductMachineryService productMachineryService;

    @Mock
    ProductInventoryService productInventoryService;

    @Mock
    PartInventoryService partInventoryService;

    private final Map<String, ProductMachinery> machineryMap = new HashMap<>();

    @BeforeEach
    public void setUp() {
        openMocks(this);
        productMachineryController = new ProductMachineryController(productMachineryService,
            productInventoryService, partInventoryService);
    }

    @Test
    public void getProductMachineries_Success() {

        machineryMap
            .put("machine0", new ProductMachinery("abc machine", MachineryState.READY, 100, null));
        machineryMap
            .put("machine1", new ProductMachinery("xyz machine", MachineryState.READY, 450, null));

        String expectedId0 = machineryMap.get("machine0").getId();
        String expectedId1 = machineryMap.get("machine1").getId();

        when(productMachineryService.getAllMachineries())
            .thenReturn(new ArrayList<>(machineryMap.values()));
        ResponseEntity responseEntity = productMachineryController.retrieveAllMachineries();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void createProductMachineryWithExistingProduct_Success() {

        ProductMachineryDto productMachineryDto = new ProductMachineryDto("dummy_machine",
            "running", 50, "prod-randomid");

        when(productMachineryService.createMachinery(productMachineryDto)).thenReturn("");
        ResponseEntity responseEntity = productMachineryController.createMachinery(productMachineryDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void ChangeMachineryStatusFromReadyToRunning_Success() {
        String machineryId = "prodmac-randomid";

        when(productMachineryService.updateMachineryStatus(machineryId, "start")).thenReturn("Success");
        ResponseEntity responseEntity = productMachineryController.updateMachineryStatus(machineryId, "start");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void ChangeMachineryStatusFromUnassignedToRunning_Fail() {
        String machineryId = "prodmac-randomid";

        when(productMachineryService.updateMachineryStatus(machineryId, "start")).thenReturn("Operation is not supported");
        ResponseEntity responseEntity = productMachineryController.updateMachineryStatus(machineryId, "start");
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }


    @Test
    public void associateProductToMachineryWhenThereIsAnUnassignedMachine_Success() {
        String machineryId = "prodmac-randomid";
        String productId = "prod-randomid";

        when(productMachineryService.findAvailableMachinery()).thenReturn(machineryId);
        when(productMachineryService.occupyMachinery(machineryId, productId)).thenReturn("Success");
        ResponseEntity responseEntity = productMachineryController.attemptProduceProduct(productId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
