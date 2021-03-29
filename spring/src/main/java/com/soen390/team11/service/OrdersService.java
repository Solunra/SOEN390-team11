package com.soen390.team11.service;

import com.soen390.team11.constant.Type;
import com.soen390.team11.dto.CustomizeReportDto;
import com.soen390.team11.dto.OrderDto;
import com.soen390.team11.dto.OrderResponseDto;
import com.soen390.team11.entity.Orders;
import com.soen390.team11.entity.RawMaterial;
import com.soen390.team11.entity.UserAccount;
import com.soen390.team11.entity.Vendors;
import com.soen390.team11.repository.OrdersRepository;
import com.soen390.team11.repository.RawMaterialRepository;
import com.soen390.team11.repository.VendorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Layer for Order
 */
@Service
public class OrdersService {

    OrdersRepository ordersRepository;
    VendorsRepository vendorsRepository;
    RawMaterialRepository rawMaterialRepository;
    UserService userService;

    public OrdersService(OrdersRepository ordersRepository, VendorsRepository vendorsRepository, RawMaterialRepository rawMaterialRepository,UserService userService) {
        this.ordersRepository = ordersRepository;
        this.vendorsRepository = vendorsRepository;
        this.rawMaterialRepository = rawMaterialRepository;
        this.userService =userService;
    }

    /**
     * Creates a new Order
     *
     * @param orderDto The Order details
     * @return The new Order's ID
     */
    public String createOrder(OrderDto orderDto)
    {
        Orders order = new Orders(orderDto.getVendorID(), orderDto.getSaleID() ,orderDto.getQuantity(), orderDto.getDateTime(), LocalDate.now(), userService.getLoggedUser().getUsername());
        Orders result = ordersRepository.save(order);
        return result.getOrderID();
    }

    /**
     * Gets an Order
     *
     * @param orderID The order's ID
     * @return The Order
     */
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

    /**
     * Gets all Orders
     *
     * @return List of all Orders
     */
    public List<OrderResponseDto> getAllOrders()
    {
        List<Orders> orders = (List<Orders>) ordersRepository.findAll();
        return extractOrders(orders);
    }

    /**
     * get customize report within start and end date
     * @param customizeReportDto
     * @return
     */
    public List<OrderResponseDto> getCustomizeReport(CustomizeReportDto customizeReportDto) {
        List<Orders> orders = ordersRepository.findAllByOrdertimeBetween(customizeReportDto.getStartDate(),customizeReportDto.getEndDate());
        return extractOrders(orders);
    }

    /**
     * extract the order
     * @param orders
     * @return
     */
    public List<OrderResponseDto> extractOrders(List<Orders> orders){
        List<OrderResponseDto> orderDtos = new ArrayList<>();
        Vendors vendor=null;
        RawMaterial rawMaterial=null;
        UserAccount userAccount=null;
        for (Orders order: orders) {
            rawMaterial=null;
            vendor=vendorsRepository.findByVendorID(order.getVendorID()).get();
            if(rawMaterialRepository.existsById(order.getSaleID())){
                rawMaterial=rawMaterialRepository.findByRawmaterialid(order.getSaleID()).get();
            }
            OffsetDateTime dateTime = OffsetDateTime.now();
            userService.getUserAccountByUserid(order.getUserid());
//            String vendorname, String type, String rawname, int quantity, String status, String vendorID, String rawID,
//            String orderDateTime, String username, String userid, String amount, String deliveryDateTime
            orderDtos.add(new OrderResponseDto(
                    vendor.getCompanyname(), Type.RAW_MATERIAL.toString(),rawMaterial==null?order.getSaleID():rawMaterial.getName(),
                    order.getQuantity(),order.getTime().isAfter(dateTime)?"Not Receive":"Receive",vendor.getVendorID(),
                    order.getSaleID(),order.getOrdertime(),userAccount.getUsername(),order.getUserid(),order.getQuantity()*rawMaterial.getCost(),order.getTime())
            );
        }
        return orderDtos;
    }

    /**
     * Gets all orders before a certain time
     *
     * @return Iterable of orders before a time
     */
    public Iterable<Orders> getOrdersBeforeNow()
    {
        return ordersRepository.findAllByTimeBefore(OffsetDateTime.now());
    }
}
