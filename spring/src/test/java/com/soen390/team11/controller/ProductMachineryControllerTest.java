package com.soen390.team11.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Team11Application.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ProductMachineryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductMachineryRepository productMachineryRepository;

    @MockBean
    private ProductRepository productRepository;

    private ProductMachineryService productMachineryService;

    private final Map<String, ProductMachinery> machineryMap = new HashMap<>();
    private final List<Product> productList = new LinkedList<>();

    @BeforeAll
    public void setUp() {
        Product dummyProduct = new Product();
        when(productRepository.findById(Mockito.anyString())).thenReturn(
            java.util.Optional.of(dummyProduct));
        ProductMachinery dummyMachinery = new ProductMachinery("xyz machine", MachineryState.READY, 450,
            null);
        when(productMachineryRepository.save(Mockito.any())).thenReturn(dummyMachinery);

        productMachineryService = new ProductMachineryService(productMachineryRepository, productRepository);
    }

    @AfterEach
    public void resetMachineryDb() {
        machineryMap.forEach((k, v) -> productMachineryRepository.delete(v));
        machineryMap.clear();
    }


    @Test
    public void getProductMachineries_Success() throws Exception {

        machineryMap
            .put("machine0", new ProductMachinery("abc machine", MachineryState.READY, 100, null));
        machineryMap
            .put("machine1", new ProductMachinery("xyz machine", MachineryState.READY, 450, null));

        String expectedId0 = machineryMap.get("machine0").getId();
        String expectedId1 = machineryMap.get("machine1").getId();

        when(productMachineryService.getAllMachineries())
            .thenReturn(new ArrayList<>(machineryMap.values()));

        mockMvc.perform(get("/machinery")
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + obtainAccessToken())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(equalTo(2))))
            .andExpect(jsonPath("$[1].id", anyOf(equalTo(expectedId0), equalTo(expectedId1))));
    }

    @Test
    public void createProductMachineryWithExistingProduct_Success() throws Exception {

        ProductMachineryDto productMachineryDto = new ProductMachineryDto("dummy_machine",
            "running", 50, "dummy-productid");

        MvcResult mvcResult = mockMvc.perform(put("/machinery")
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + obtainAccessToken())
            .content(new ObjectMapper().writeValueAsString(productMachineryDto))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        String machineryId = mvcResult.getResponse().getContentAsString();

        assertNotNull(machineryId);
    }

    @Test
    public void ChangeMachineryStatusFromReadyToRunning_Success() throws Exception {
        String machineName = "dummy_machinery";
        machineryMap
            .put(machineName, new ProductMachinery("abc machine", MachineryState.READY, 100, null));

        String machineryId = "prodmac-randomid";

        when(productMachineryRepository.findById(machineryId)).thenReturn(java.util.Optional
            .of(machineryMap.get(machineName)));

        mockMvc.perform(post(String.format("/machinery/%s/%s", machineryId, "start"))
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + obtainAccessToken())
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void ChangeMachineryStatusFromUnassignedToRunning_Fail() throws Exception {
        String machineName = "dummy_machinery";
        machineryMap.put(machineName,
            new ProductMachinery("abc machine", MachineryState.UNASSIGNED, 100, null));

        String machineryId = "prodmac-randomid";

        when(productMachineryRepository.findById(machineryId)).thenReturn(java.util.Optional
            .of(machineryMap.get(machineName)));

        mockMvc.perform(post(String.format("/machinery/%s/%s", machineryId, "start"))
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + obtainAccessToken())
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

        assertEquals(MachineryState.UNASSIGNED,
            productMachineryRepository.findById(machineryId).get().getStatus());
    }

    private String obtainAccessToken() throws Exception {

        String content = "{\"username\": \"admin@erp.com\", \"password\": \"admin\"}";

        MockHttpServletRequestBuilder requestBuilder = post("/account/signin")
            .content(content)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
            .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getHeader("Authorization");

        return Objects.requireNonNull(response).substring("Bearer ".length());
    }

}
