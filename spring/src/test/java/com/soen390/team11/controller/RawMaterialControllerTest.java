package com.soen390.team11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.soen390.team11.dto.RawMaterialRequestDto;
import com.soen390.team11.dto.VendorDto;
import com.soen390.team11.entity.Vendors;
import com.soen390.team11.repository.VendorsRepository;
import com.soen390.team11.service.RawMaterialService;
import com.soen390.team11.service.VendorsService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class RawMaterialControllerTest {

    RawMaterialController rawMaterialController;

    @Mock
    RawMaterialService rawMaterialService;

    RawMaterialRequestDto rawMaterialRequestDto;

    @BeforeEach
    public void setup()
    {
        openMocks(this);
        rawMaterialController = new RawMaterialController(rawMaterialService);
        rawMaterialRequestDto = new RawMaterialRequestDto("Steel", "description", 100.23,"ton", "vendorId");
    }

    @Test
    public void testDefineRawMaterial() throws Exception
    {
        when(rawMaterialService.createNewRawMaterial(rawMaterialRequestDto)).thenReturn("");
        ResponseEntity responseEntity = rawMaterialController.defineRawMaterial(rawMaterialRequestDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllRawMaterials()
    {
        when(rawMaterialService.getAllRawMaterial()).thenReturn(List.of());
        ResponseEntity responseEntity = rawMaterialController.retrieveAllRawMaterials();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetRawMaterialUsingID()
    {
        String id = "rawMaterialID";

        when(rawMaterialService.getRawMaterialById(id)).thenReturn(new RawMaterialRequestDto());
        ResponseEntity responseEntity = rawMaterialController.retrieveRawMaterial(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testEditRawMaterial() throws Exception
    {
        rawMaterialRequestDto.setname("New Raw Material");
        rawMaterialRequestDto.setCost(132.22);

        when(rawMaterialService.updateRawMaterial("rawMaterialID", rawMaterialRequestDto)).thenReturn("");
        ResponseEntity responseEntity =rawMaterialController.editRawMaterial("rawMaterialID", rawMaterialRequestDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteRawMaterial() throws Exception
    {
        String rawMaterialID = "id";
        when(rawMaterialService.deleteRawMaterial(rawMaterialID)).thenReturn("Delete Successful");
        ResponseEntity responseEntity =rawMaterialController.removeRawMaterial(rawMaterialID);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Delete Successful", ((String)responseEntity.getBody()).replace("\"",""));
    }

    @Test
    public void testGetNoRawMaterial()
    {
        when(rawMaterialService.getRawMaterialById("rawMaterialID")).thenReturn(null);
        ResponseEntity responseEntity = rawMaterialController.retrieveRawMaterial("rawMaterialID");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}
