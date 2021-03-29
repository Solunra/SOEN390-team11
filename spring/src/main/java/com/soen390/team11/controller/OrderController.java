package com.soen390.team11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen390.team11.dto.CustomizeReportDto;
import com.soen390.team11.dto.OrderDto;
import com.soen390.team11.dto.OrderResponseDto;
import com.soen390.team11.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

/**
 * Controller for Orders
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    ObjectMapper objectMapper= new ObjectMapper();

    public OrdersService ordersService;

    public OrderController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    /**
     * Gets all orders
     *
     * @return A list of all orders
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders()
    {
        List<OrderResponseDto> listOfOrders = ordersService.getAllOrders();
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(listOfOrders), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a new order
     *
     * @param orderDto The request body
     * @return The ID of the new order
     */
    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto)
    {
        String orderId = ordersService.createOrder(orderDto);
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(orderId), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Obtain an order
     *
     * @param oid The order's ID
     * @return The order
     */
    @GetMapping("/{oid}")
    public ResponseEntity<?> getOrderById(@PathVariable String oid)
    {
        try {
            Optional<OrderDto> foundOrder = ordersService.getOrderById(oid);
            if(foundOrder.isEmpty()){
                return new ResponseEntity<>(objectMapper.writeValueAsString("empty"), HttpStatus.NOT_FOUND);
            }
            else{
                return new ResponseEntity<>(objectMapper.writeValueAsString(foundOrder), HttpStatus.OK);
            }

        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }

    /**
     * get report between start and end date
     * @param customizeReportDto
     * @return
     */
    @PostMapping("/report")
    public ResponseEntity<?> getReport(@RequestBody CustomizeReportDto customizeReportDto){
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(ordersService.getCustomizeReport(customizeReportDto)), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("cannot convert to json", HttpStatus.CONFLICT);
        }
    }
}
