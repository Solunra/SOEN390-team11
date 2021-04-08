package com.soen390.team11.service;

import com.soen390.team11.constant.LogTypes;
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
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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
    LogService logService;

    public OrdersService(OrdersRepository ordersRepository, VendorsRepository vendorsRepository, RawMaterialRepository rawMaterialRepository,UserService userService,
                         LogService logService) {
        this.ordersRepository = ordersRepository;
        this.vendorsRepository = vendorsRepository;
        this.rawMaterialRepository = rawMaterialRepository;
        this.userService =userService;
        this.logService = logService;
    }

    /**
     * Creates a new Order
     *
     * @param orderDto The Order details
     * @return The new Order's ID
     */
    public String createOrder(OrderDto orderDto)
    {
        logService.writeLog(LogTypes.ORDERS,"Creating order...");
        Orders order = new Orders(orderDto.getVendorID(), orderDto.getSaleID() ,orderDto.getQuantity(), orderDto.getDateTime(), OffsetDateTime.now(), userService.getLoggedUser().getUserID());
        Orders result = ordersRepository.save(order);
        logService.writeLog(LogTypes.ORDERS,"Order created, returning ID");
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
        logService.writeLog(LogTypes.ORDERS,"Find the order by ID...");
        Optional<Orders> order = ordersRepository.findByOrderID(orderID);
        if (order.isPresent())
        {
            logService.writeLog(LogTypes.ORDERS,"Order is present");
            return Optional.of(new OrderDto(order.get().getVendorID(), order.get().getSaleID(),order.get().getQuantity(), order.get().getTime()));
        }
        else
        {
            logService.writeLog(LogTypes.ORDERS,"Order is empty");
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
        if(orders.isEmpty()){
            return new ArrayList<>();
        }
        logService.writeLog(LogTypes.ORDERS,"Return all orders");
        return extractOrders(orders);
    }

    /**
     * get customize report within start and end date
     * @param customizeReportDto
     * @return
     */
    public List<OrderResponseDto> getCustomizeReport(CustomizeReportDto customizeReportDto) {
        logService.writeLog(LogTypes.ORDERS,"Generating customized report of orders...");
        List<Orders> orders = ordersRepository.findAllByOrdertimeBetween(OffsetDateTime.of(customizeReportDto.getStartDate(), LocalTime.now(), ZoneOffset.UTC) ,
                OffsetDateTime.of(customizeReportDto.getEndDate(), LocalTime.now(), ZoneOffset.UTC));
        if(orders.isEmpty()){
            return new ArrayList<>();
        }
        logService.writeLog(LogTypes.ORDERS,"Report generated");
        return extractOrders(orders);
    }

    /**
     * extract the order
     * @param orders
     * @return
     */
    public List<OrderResponseDto> extractOrders(List<Orders> orders){
        logService.writeLog(LogTypes.ORDERS,"Extracting orders...");
        List<OrderResponseDto> orderDtos = new ArrayList<>();
        Vendors vendor=null;
        RawMaterial rawMaterial=null;
        UserAccount userAccount=null;
        logService.writeLog(LogTypes.ORDERS,"Filtering through every order");
        for (Orders order: orders) {
            rawMaterial=null;
            vendor=vendorsRepository.findByVendorID(order.getVendorID()).get();
            if(rawMaterialRepository.existsById(order.getSaleID())){
                rawMaterial=rawMaterialRepository.findByRawmaterialid(order.getSaleID()).get();
            }
            OffsetDateTime dateTime = OffsetDateTime.now();
            userAccount=userService.getUserAccountByUserid(order.getUserid());
            String username = userAccount == null ? "Null": userAccount.getUsername();
            orderDtos.add(new OrderResponseDto(
                    vendor.getCompanyname(), Type.RAW_MATERIAL.toString(),rawMaterial==null?order.getSaleID():rawMaterial.getName(),
                    order.getQuantity(),order.getTime().isAfter(dateTime)?"Not Receive":"Receive",vendor.getVendorID(),
                    order.getSaleID(),order.getOrdertime(),username,order.getUserid(),order.getQuantity()*rawMaterial.getCost(),order.getTime())
            );
        }
        logService.writeLog(LogTypes.ORDERS,"Returning orders");
        return orderDtos;
    }

    /**
     * Gets all orders before a certain time
     *
     * @return Iterable of orders before a time
     */
    public Iterable<Orders> getOrdersBeforeNow()
    {
        logService.writeLog(LogTypes.ORDERS,"getting all order before a time limit");
        return ordersRepository.findAllByTimeBefore(OffsetDateTime.now());
    }
}
