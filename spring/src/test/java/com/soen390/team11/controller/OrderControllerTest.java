package com.soen390.team11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.constant.Type;
import com.soen390.team11.dto.OrderDto;
import com.soen390.team11.dto.RawMaterialRequestDto;
import com.soen390.team11.dto.VendorDto;
import com.soen390.team11.entity.*;
import com.soen390.team11.repository.OrdersRepository;
import com.soen390.team11.repository.RawMaterialRepository;
import com.soen390.team11.repository.VendorSaleRepository;
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
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderControllerTest {

    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    OrderController orderController;
    @Autowired
    VendorController vendorController;
    @Autowired
    RawMaterialController rawMaterialController;
    @Autowired
    RawMaterialRepository rawMaterialRepository;
    @Autowired
    VendorsRepository vendorsRepository;
    @Autowired
    VendorSaleRepository vendorSaleRepository;

    OrderDto orderDto;
    String vendorId;
    String rawMaterialId;
    String orderId;
    OffsetDateTime time;
    int sizeOfOrders;

    @BeforeAll
    public void setup()
    {
        ResponseEntity responseEntity = vendorController.createVendor(new VendorDto("Bike Company","Address","514-515-1323","bikecompany@email.com"));
        vendorId = (String) responseEntity.getBody();
        responseEntity = rawMaterialController.defineRawMaterial(new RawMaterialRequestDto("Steel","description",12.22,"kg",vendorId));
        rawMaterialId = ((String) responseEntity.getBody()).replace("\"","");
        ArrayList<?> rawMaterialList = null;
        try {
            rawMaterialList = new ObjectMapper().readValue(orderController.getAllOrders().getBody().toString(), ArrayList.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        sizeOfOrders = rawMaterialList.size();
        time = OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        orderDto = new OrderDto(vendorId, rawMaterialId,1, time);
    }

    @Test
    @Order(1)
    public void testCreation()
    {
        ResponseEntity responseEntity = orderController.createOrder(orderDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        orderId = ((String) responseEntity.getBody()).replace("\"","");;
    }

    @Test
    @Order(2)
    public void testGetOrderById()
    {
        System.out.println(orderId);
        ResponseEntity responseEntity = orderController.getOrderById(orderId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Order(3)
    public void testGetAllOrders()
    {
        ResponseEntity responseEntity = orderController.getAllOrders();
        ArrayList<?> orderDTOList = null;
        try {
            orderDTOList = new ObjectMapper().readValue(responseEntity.getBody().toString(), ArrayList.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(sizeOfOrders + 1, orderDTOList.size());
    }

    @Test
    @Order(4)
    public void testGetNoOrder()
    {
        ResponseEntity responseEntity = orderController.getOrderById("v-98");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @AfterAll
    public void delete()
    {
        Optional<Orders> orders = ordersRepository.findByOrderID(orderId);
        ordersRepository.delete(orders.get());

        System.out.println(rawMaterialId);
        Optional<VendorSale> vendorSale = vendorSaleRepository.findById(new VendorSaleId(vendorId, rawMaterialId));
//        System.out.println(vendorSale.toString());
        vendorSaleRepository.delete(vendorSale.get());

        Optional<RawMaterial> rawMaterial = rawMaterialRepository.findById(rawMaterialId);
        rawMaterialRepository.delete(rawMaterial.get());

        Optional<Vendors> vendors = vendorsRepository.findByVendorID(vendorId);
        vendorsRepository.delete(vendors.get());
    }
}
