package com.soen390.team11.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.soen390.team11.Team11Application;
import com.soen390.team11.constant.MachineryState;
import com.soen390.team11.dto.ProductMachineryDto;
import com.soen390.team11.entity.Product;
import com.soen390.team11.entity.ProductMachinery;
import com.soen390.team11.repository.ProductMachineryRepository;
import com.soen390.team11.repository.ProductRepository;
import com.soen390.team11.service.ProductMachineryService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

public class ProductMachineryControllerTest {

    ProductMachineryController productMachineryController;
    @Mock
    ProductMachineryService productMachineryService;

    private final Map<String, ProductMachinery> machineryMap = new HashMap<>();

    @BeforeEach
    public void setUp() {
        openMocks(this);
        productMachineryController = new ProductMachineryController(productMachineryService);
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
    public void createProductMachineryWithExistingProduct_Success() throws Exception {

        ProductMachineryDto productMachineryDto = new ProductMachineryDto("dummy_machine",
            "running", 50, "prod-randomid");

        when(productMachineryService.createMachinery(productMachineryDto)).thenReturn("");
        ResponseEntity responseEntity = productMachineryController.createMachinery(productMachineryDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void ChangeMachineryStatusFromReadyToRunning_Success() throws Exception {
        String machineryId = "prodmac-randomid";

        when(productMachineryService.updateMachineryStatus(machineryId, "start")).thenReturn("Success");
        ResponseEntity responseEntity = productMachineryController.updateMachineryStatus(machineryId, "start");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void ChangeMachineryStatusFromUnassignedToRunning_Fail() throws Exception {
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
