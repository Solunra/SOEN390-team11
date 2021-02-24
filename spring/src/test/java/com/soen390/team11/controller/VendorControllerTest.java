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

    @BeforeAll
    public void setup()
    {
        vendorDto = new VendorDto(Type.MATERIAL, "1");
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
    public void testGetVendor()
    {
        ResponseEntity responseEntity = vendorController.getVendorById(ID);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Type.MATERIAL, ((VendorDto) responseEntity.getBody()).getType());
        assertEquals("1", ((VendorDto) responseEntity.getBody()).getSaleID());
    }

    @Test
    @Order(3)
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
