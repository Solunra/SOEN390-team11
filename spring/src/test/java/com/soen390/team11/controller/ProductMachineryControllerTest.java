package com.soen390.team11.controller;

import com.soen390.team11.Team11Application;
import com.soen390.team11.entity.ProductMachinery;
import com.soen390.team11.repository.ProductMachineryRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Objects;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @AfterAll
    public void resetDb() {
        productMachineryRepository.deleteAll();
    }

    @Test
    public void getProductMachineries_Success() throws Exception {
        ProductMachinery machinery0 = new ProductMachinery();
        ProductMachinery machinery1 = new ProductMachinery();

        productMachineryRepository.save(machinery0);
        productMachineryRepository.save(machinery1);

        String expectedId0 = machinery0.getId();

        String expectedName1 = machinery1.getName();

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