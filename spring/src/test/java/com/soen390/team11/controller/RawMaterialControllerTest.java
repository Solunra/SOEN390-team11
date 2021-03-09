package com.soen390.team11.controller;

import com.soen390.team11.dto.RawMaterialRequestDto;
import com.soen390.team11.dto.VendorDto;
import com.soen390.team11.entity.Vendors;
import com.soen390.team11.repository.VendorsRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RawMaterialControllerTest {

    @Autowired
    VendorController vendorController;

    @Autowired
    RawMaterialController rawMaterialController;

    @Autowired
    VendorsRepository vendorsRepository;

    String vendorId;
    String rawMaterialID;
    int sizeOfRawMaterial;
    RawMaterialRequestDto rawMaterialRequestDto;


    @BeforeAll
    public void setup()
    {
        ResponseEntity responseEntity = vendorController.createVendor(new VendorDto("Bike Company","Address","514-515-1323","bikecompany@email.com"));
        vendorId = (String) responseEntity.getBody();
        rawMaterialRequestDto = new RawMaterialRequestDto("Steel", "description", 100.23,"ton",vendorId);
        sizeOfRawMaterial = ((List) rawMaterialController.retrieveAllRawMaterials().getBody()).size();
    }

    @Test
    @Order(1)
    public void testDefineRawMaterial()
    {
        ResponseEntity responseEntity = rawMaterialController.defineRawMaterial(rawMaterialRequestDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        rawMaterialID = (String) responseEntity.getBody();
    }

    @Test
    @Order(2)
    public void testGetAllRawMaterials()
    {
        ResponseEntity responseEntity = rawMaterialController.retrieveAllRawMaterials();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(sizeOfRawMaterial+1,((List) responseEntity.getBody()).size());
    }

    @Test
    @Order(3)
    public void testGetRawMaterialUsingID()
    {

        ResponseEntity responseEntity =rawMaterialController.retrieveRawMaterial(rawMaterialID);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(rawMaterialRequestDto.getname(),((RawMaterialRequestDto) responseEntity.getBody()).getname());
        assertEquals(rawMaterialRequestDto.getDescription(),((RawMaterialRequestDto) responseEntity.getBody()).getDescription());
        assertEquals(rawMaterialRequestDto.getPrice(),((RawMaterialRequestDto) responseEntity.getBody()).getPrice());
        assertEquals(rawMaterialRequestDto.getUnit(),((RawMaterialRequestDto) responseEntity.getBody()).getUnit());
        assertEquals(rawMaterialRequestDto.getVendorID(),((RawMaterialRequestDto) responseEntity.getBody()).getVendorID());
    }

    @Test
    @Order(4)
    public void testEditRawMaterial()
    {

        rawMaterialRequestDto.setname("New Raw Material");
        rawMaterialRequestDto.setPrice(132.22);
        ResponseEntity responseEntity =rawMaterialController.editRawMaterial(rawMaterialID,rawMaterialRequestDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        responseEntity =rawMaterialController.retrieveRawMaterial(rawMaterialID);
        assertEquals(rawMaterialRequestDto.getname(),((RawMaterialRequestDto) responseEntity.getBody()).getname());
        assertEquals(rawMaterialRequestDto.getDescription(),((RawMaterialRequestDto) responseEntity.getBody()).getDescription());
        assertEquals(rawMaterialRequestDto.getPrice(),((RawMaterialRequestDto) responseEntity.getBody()).getPrice());
        assertEquals(rawMaterialRequestDto.getUnit(),((RawMaterialRequestDto) responseEntity.getBody()).getUnit());
        assertEquals(rawMaterialRequestDto.getVendorID(),((RawMaterialRequestDto) responseEntity.getBody()).getVendorID());
    }

    @Test
    @Order(5)
    public void testDeleteRawMaterial()
    {
        ResponseEntity responseEntity =rawMaterialController.removeRawMaterial(rawMaterialID);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Delete Successful",responseEntity.getBody());
    }

    @Test
    @Order(6)
    public void testGetNoRawMaterial()
    {
        ResponseEntity responseEntity =rawMaterialController.retrieveRawMaterial(rawMaterialID);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @AfterAll
    public void delete()
    {
        Optional<Vendors> vendors = vendorsRepository.findByVendorID(vendorId);
        vendorsRepository.delete(vendors.get());
    }
}
