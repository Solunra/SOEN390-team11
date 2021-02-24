package com.soen390.team11.controller;

import com.soen390.team11.dto.OrderDto;
import com.soen390.team11.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    public OrdersService ordersService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders()
    {
        List<OrderDto> listOfOrders = ordersService.getAllOrders();
        return ResponseEntity.ok().body(listOfOrders);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto)
    {
        String orderId = ordersService.createOrder(orderDto);
        return ResponseEntity.ok(orderId);
    }

    @GetMapping("/{oid}")
    public ResponseEntity<?> getOrderById(@PathVariable String oid)
    {
        return ResponseEntity.of(ordersService.getOrderById(oid));
    }
}
