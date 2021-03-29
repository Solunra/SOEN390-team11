package com.soen390.team11.controller;

import com.soen390.team11.constant.Type;
import com.soen390.team11.dto.VendorDto;
import com.soen390.team11.entity.UserAccount;
import com.soen390.team11.entity.Vendors;
import com.soen390.team11.repository.VendorsRepository;
import com.soen390.team11.service.VendorsService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;


public class VendorControllerTest {

    VendorController vendorController;
    @Mock
    VendorsService vendorsService;

    VendorDto vendorDto;
    String ID;


    @BeforeEach
    public void setup()
    {
        openMocks(this);
        vendorController = new VendorController(vendorsService);
        vendorDto = new VendorDto("Bike Company","Address","514-515-1323","bikecompany@email.com");
        ID = "id-1";
    }

    @Test
    public void testCreation()
    {
        when(vendorsService.createVendor(vendorDto)).thenReturn(ID);
        ResponseEntity responseEntity = vendorController.createVendor(vendorDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllVendors()
    {
        when(vendorsService.getAllVendors()).thenReturn(List.of(new Vendors()));
        ResponseEntity responseEntity = vendorController.retrieveAllVendors();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, ((List<Vendors>) responseEntity.getBody()).size());
    }

    @Test
    public void testGetVendor()
    {
        when(vendorsService.getVendor(ID)).thenReturn(Optional.of(new Vendors(vendorDto.getCompanyName(), vendorDto.getAddress(), vendorDto.getPhone(), vendorDto.getEmail())));
        ResponseEntity responseEntity = vendorController.getVendorById(ID);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Bike Company", ((VendorDto) responseEntity.getBody()).getCompanyName());
        assertEquals("Address", ((VendorDto) responseEntity.getBody()).getAddress());
        assertEquals("514-515-1323", ((VendorDto) responseEntity.getBody()).getPhone());
        assertEquals("bikecompany@email.com", ((VendorDto) responseEntity.getBody()).getEmail());
    }

    @Test
    public void testGetNoVendor()
    {
        when(vendorsService.getVendor("v-98")).thenReturn(Optional.empty());
        ResponseEntity responseEntity = vendorController.getVendorById("v-98");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}
