package com.soen390.team11.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.MaterialRequestDto;
import com.soen390.team11.entity.Material;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MaterialControllerTest {
    @Autowired
    MaterialController materialController;
    MaterialRequestDto materialRequestDto;
    Material createdMaterial;
    ObjectMapper objectMapper= new ObjectMapper();
    @BeforeAll
    public void setup()
    {
        materialRequestDto = new MaterialRequestDto();
        materialRequestDto.setname("Head Tube");
    }
    @Test
    @Order(1)
    void createMaterial() throws Exception{
        ResponseEntity<?> responseEntity = materialController.createMaterial(materialRequestDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Map<String,Object> responseBody = objectMapper.readValue(responseEntity.getBody().toString(),Map.class);
        createdMaterial = new Material(Long.valueOf(responseBody.get("materialid").toString()),responseBody.get("name").toString());
    }

    @Test
    @Order(2)
    void retrieveAllMaterial() {
        ResponseEntity<?> responseEntity = materialController.retrieveAllMaterial();
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @Order(3)
    void retrieveMaterial() {
        ResponseEntity<?> responseEntity = materialController.retrieveMaterial(createdMaterial.getMaterialid());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Order(4)
    void updateMaterial() {
        MaterialRequestDto newMaterial = new MaterialRequestDto("Shock absorber");
        ResponseEntity<?> responseEntity = materialController.updateMaterial(createdMaterial.getMaterialid(), newMaterial);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Order(5)
    void deleteMaterial() {
        ResponseEntity<?> responseEntity = materialController.deleteMaterial(createdMaterial.getMaterialid());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        responseEntity = materialController.deleteMaterial(Long.valueOf("1000"));
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }
}