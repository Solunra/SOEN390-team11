package com.soen390.team11.controller;

import com.soen390.team11.constant.Type;
import com.soen390.team11.dto.VendorDto;
import com.soen390.team11.entity.UserAccount;
import com.soen390.team11.entity.Vendors;
import com.soen390.team11.repository.VendorsRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
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
public class VendorControllerTest {

    @Autowired
    VendorController vendorController;
    @Autowired
    VendorsRepository vendorsRepository;

    VendorDto vendorDto;
    String ID;
    int sizeOfVendors;


    @BeforeAll
    public void setup()
    {
        vendorDto = new VendorDto("Bike Company","Address","514-515-1323","bikecompany@email.com");
        sizeOfVendors = ((List) vendorController.retrieveAllVendors().getBody()).size();
    }

    @Test
    @Order(1)
    public void testCreation()
    {
        ResponseEntity responseEntity = vendorController.createVendor(vendorDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ID = (String) responseEntity.getBody();
    }

    @Test
    @Order(2)
    public void testGetAllVendors()
    {
        ResponseEntity responseEntity = vendorController.retrieveAllVendors();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(sizeOfVendors+1, ((List<Vendors>) responseEntity.getBody()).size());
    }

    @Test
    @Order(3)
    public void testGetVendor()
    {
        ResponseEntity responseEntity = vendorController.getVendorById(ID);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Bike Company", ((VendorDto) responseEntity.getBody()).getCompanyName());
        assertEquals("Address", ((VendorDto) responseEntity.getBody()).getAddress());
        assertEquals("514-515-1323", ((VendorDto) responseEntity.getBody()).getPhone());
        assertEquals("bikecompany@email.com", ((VendorDto) responseEntity.getBody()).getEmail());
    }

    @Test
    @Order(4)
    public void testGetNoVendor()
    {
        ResponseEntity responseEntity = vendorController.getVendorById("v-98");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @AfterAll
    public void delete()
    {
        Optional<Vendors> vendors = vendorsRepository.findByVendorID(ID);
        vendorsRepository.delete(vendors.get());
    }

}
