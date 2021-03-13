package com.soen390.team11.controller;

import com.soen390.team11.Team11Application;
import com.soen390.team11.constant.MachineryState;
import com.soen390.team11.dto.ProductMachineryDto;
import com.soen390.team11.entity.Product;
import com.soen390.team11.entity.ProductMachinery;
import com.soen390.team11.repository.ProductMachineryRepository;
import com.soen390.team11.repository.ProductRepository;
import org.junit.jupiter.api.*;
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
import static org.hamcrest.core.AnyOf.anyOf;
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

    @BeforeAll
    public void setUpProductDb() {
        Product dummyProduct = new Product();
        productRepository.save(dummyProduct);
        productList.add(dummyProduct);
    }

    @AfterEach
    public void resetMachineryDb() {
        machineryMap.forEach((k, v) -> productMachineryRepository.delete(v));
        machineryMap.clear();
    }

    @AfterAll
    public void resetProductDb() {
        productList.forEach(product -> productRepository.delete(product));
        productList.clear();
    }


    @Test
    public void getProductMachineries_Success() throws Exception {

        machineryMap.put("machine0", new ProductMachinery("abc machine", MachineryState.READY, 100, null));
        machineryMap.put("machine1", new ProductMachinery("xyz machine", MachineryState.READY, 450, null));

        productMachineryRepository.save(machineryMap.get("machine0"));
        productMachineryRepository.save(machineryMap.get("machine1"));

        String expectedId0 = machineryMap.get("machine0").getId();
        String expectedId1 = machineryMap.get("machine1").getId();

        String token = obtainAccessToken();

        mockMvc.perform(get("/machinery")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(equalTo(2))))
                .andExpect(jsonPath("$[1].id", anyOf(equalTo(expectedId0), equalTo(expectedId1))));
    }

    @Test
    public void createProductMachineryWithExistingProduct_Success() throws Exception {

        ProductMachineryDto productMachineryDto = new ProductMachineryDto("dummy_machine", "running", 50, productList.get(0).getProductid());

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
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getHeader("Authorization");

        return Objects.requireNonNull(response).substring("Bearer ".length());
    }

}
