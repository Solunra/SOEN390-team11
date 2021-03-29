package com.soen390.team11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.constant.Type;
import com.soen390.team11.dto.*;
import com.soen390.team11.entity.*;
import com.soen390.team11.repository.OrdersRepository;
import com.soen390.team11.repository.RawMaterialRepository;
import com.soen390.team11.repository.VendorSaleRepository;
import com.soen390.team11.repository.VendorsRepository;
import com.soen390.team11.service.OrdersService;
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

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class OrderControllerTest {

    @Mock
    OrdersService ordersService;
    OrderController orderController;

    OrderDto orderDto;
    String vendorId = "vendorId";
    String rawMaterialId = "rawMaterialId";
    String orderId = "orderId";
    OffsetDateTime time;

    @BeforeEach
    public void setup()
    {
        openMocks(this);
        orderController = new OrderController(ordersService);
        time = OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        orderDto = new OrderDto(vendorId, rawMaterialId,1, time);
    }

    @Test
    public void testCreation()
    {
        when(ordersService.createOrder(orderDto)).thenReturn("");
        ResponseEntity responseEntity = orderController.createOrder(orderDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetOrderById()
    {
        when(ordersService.getOrderById(orderId)).thenReturn(Optional.of(orderDto));
        ResponseEntity responseEntity = orderController.getOrderById(orderId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllOrders()
    {
        when(ordersService.getAllOrders()).thenReturn(List.of(new OrderResponseDto()));
        ResponseEntity responseEntity = orderController.getAllOrders();
        ArrayList<?> orderDTOList = null;
        try {
            orderDTOList = new ObjectMapper().readValue(responseEntity.getBody().toString(), ArrayList.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, orderDTOList.size());
    }

    @Test
    public void testGetNoOrder()
    {
        when(ordersService.getOrderById("v-98")).thenReturn(Optional.empty());
        ResponseEntity responseEntity = orderController.getOrderById("v-98");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void getReport() {
        CustomizeReportDto customizeReportDto = new CustomizeReportDto(LocalDate.of(2021,3, 27),LocalDate.of(2021,3, 28));
        when(ordersService.getCustomizeReport(customizeReportDto)).thenReturn(List.of());
        ResponseEntity<?> responseEntity = orderController.getReport(customizeReportDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
