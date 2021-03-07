package com.soen390.team11.controller;

import com.soen390.team11.Team11Application;
import com.soen390.team11.dto.ProductMachineryDto;
import com.soen390.team11.entity.Product;
import com.soen390.team11.entity.ProductMachinery;
import com.soen390.team11.repository.ProductMachineryRepository;
import com.soen390.team11.repository.ProductRepository;
import org.hamcrest.core.StringStartsWith;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Team11Application.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ProductMachineryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductMachineryRepository productMachineryRepository;

    @Autowired
    private ProductRepository productRepository;

    private Map<String, ProductMachinery> machineryMap = new HashMap<>();
    private List<Product> productList = new LinkedList<>();

    @AfterEach
    public void resetDb() {
        machineryMap.forEach((k, v) -> productMachineryRepository.deleteById(v.getId()));
        machineryMap.clear();
        productList.forEach(product -> productRepository.delete(product));
        productList.clear();
    }

    @Test
    public void getProductMachineries_Success() throws Exception {

        machineryMap.put("machine0", new ProductMachinery());
        machineryMap.put("machine1", new ProductMachinery());

        productMachineryRepository.save(machineryMap.get("machine0"));
        productMachineryRepository.save(machineryMap.get("machine1"));

        String expectedId0 = machineryMap.get("machine0").getId();

        String expectedName1 = machineryMap.get("machine1").getName();

        String token = obtainAccessToken();

        mockMvc.perform(get("/machinery")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(equalTo(2))))
                .andExpect(jsonPath("$[0].id", equalTo(expectedId0)))
                .andExpect(jsonPath("$[1].name", equalTo(expectedName1)));
    }

    @Test
    public void createProductMachineryWithExistingProduct_Success() throws Exception {
        Product dummyProduct = new Product();
        productRepository.save(dummyProduct);
        productList.add(dummyProduct);

        ProductMachineryDto productMachineryDto = new ProductMachineryDto("dummy_machine", "running", 50, dummyProduct.getProductid());

        MvcResult mvcResult = mockMvc.perform(put("/machinery")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + obtainAccessToken())
                .content(new ObjectMapper().writeValueAsString(productMachineryDto))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        String machineryId = mvcResult.getResponse().getContentAsString();

        assertNotNull(machineryId);

        machineryMap.put("testCreationMachinery", productMachineryRepository.findById(machineryId).get());
    }

    private String obtainAccessToken() throws Exception {

        String content = "{\"username\": \"admin@erp.com\", \"password\": \"admin\"}";

        MockHttpServletRequestBuilder requestBuilder = post("/account/signin")
                .content(content)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getHeader("Authorization");

        return Objects.requireNonNull(response).substring("Bearer ".length());
    }

}
