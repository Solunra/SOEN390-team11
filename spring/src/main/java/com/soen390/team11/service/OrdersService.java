package com.soen390.team11.service;

import com.soen390.team11.dto.OrderDto;
import com.soen390.team11.entity.Orders;
import com.soen390.team11.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;

    public void createOrder(OrderDto orderDto)
    {
        Orders order = new Orders(orderDto.getVendorID(), orderDto.getQuantity(), orderDto.getDateTime());
        ordersRepository.save(order);
    }

    public Optional<Orders> getOrderById(String orderID)
    {
        return ordersRepository.findByOrderID(orderID);
    }

    public Iterable<Orders> getAllOrders()
    {
        return ordersRepository.findAll();
    }
    public Iterable<Orders> getOrdersByRecent()
    {
        return ordersRepository.findAllByTimeBefore(OffsetDateTime.now());
    }
}
