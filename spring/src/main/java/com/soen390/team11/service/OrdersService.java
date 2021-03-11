package com.soen390.team11.service;

import com.soen390.team11.dto.OrderDto;
import com.soen390.team11.entity.Orders;
import com.soen390.team11.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;

    public String createOrder(OrderDto orderDto)
    {
        Orders order = new Orders(orderDto.getVendorID(), orderDto.getSaleID() ,orderDto.getQuantity(), orderDto.getDateTime());
        Orders result = ordersRepository.save(order);
        return result.getOrderID();
    }

    public Optional<OrderDto> getOrderById(String orderID)
    {
        Optional<Orders> order = ordersRepository.findByOrderID(orderID);
        if (order.isPresent())
        {
            return Optional.of(new OrderDto(order.get().getVendorID(), order.get().getSaleID(),order.get().getQuantity(), order.get().getTime()));
        }
        else
        {
            return Optional.empty();
        }
    }

    public List<OrderDto> getAllOrders()
    {
        Iterable<Orders> orders = ordersRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Orders order: orders) {
            orderDtos.add(new OrderDto(order.getVendorID(), order.getSaleID(), order.getQuantity(), order.getTime()));
        }
        return orderDtos;
    }
    public Iterable<Orders> getOrdersByRecent()
    {
        return ordersRepository.findAllByTimeBefore(OffsetDateTime.now());
    }
}
