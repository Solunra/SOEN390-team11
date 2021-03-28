package com.soen390.team11.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.MaterialRequestDto;
import com.soen390.team11.entity.Material;
import com.soen390.team11.service.MaterialService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class MaterialControllerTest {

    MaterialController materialController;
    @Mock
    MaterialService materialService;
    MaterialRequestDto materialRequestDto;
    Material createdMaterial;
    ObjectMapper objectMapper= new ObjectMapper();

    @BeforeEach
    public void setup()
    {
        openMocks(this);
        materialController = new MaterialController(materialService);
        materialRequestDto = new MaterialRequestDto();
        materialRequestDto.setname("Head Tube");
        createdMaterial = new Material("material", "Head Tube");
    }

    @Test
    void createMaterial()
    {
        when(materialService.createMaterial(materialRequestDto)).thenReturn(createdMaterial);
        ResponseEntity<?> responseEntity = materialController.createMaterial(materialRequestDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void retrieveAllMaterial() {
        when(materialService.getAllMaterial()).thenReturn(List.of());
        ResponseEntity<?> responseEntity = materialController.retrieveAllMaterial();
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void retrieveMaterial() {
        when(materialService.getMaterialById(createdMaterial.getMaterialid())).thenReturn(createdMaterial);
        ResponseEntity<?> responseEntity = materialController.retrieveMaterial(createdMaterial.getMaterialid());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void updateMaterial() throws Exception {
        MaterialRequestDto newMaterial = new MaterialRequestDto("Shock absorber");
        when(materialService.updateMaterial(createdMaterial.getMaterialid(), newMaterial)).thenReturn(new Material(createdMaterial.getMaterialid(), newMaterial.getname()));
        ResponseEntity<?> responseEntity = materialController.updateMaterial(createdMaterial.getMaterialid(), newMaterial);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void deleteMaterial() throws Exception {
        when(materialService.deleteMaterial(createdMaterial.getMaterialid())).thenReturn("");
        ResponseEntity<?> responseEntity = materialController.deleteMaterial(createdMaterial.getMaterialid());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        when(materialService.deleteMaterial("1000")).thenThrow(new Exception());
        responseEntity = materialController.deleteMaterial("1000");
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }
}