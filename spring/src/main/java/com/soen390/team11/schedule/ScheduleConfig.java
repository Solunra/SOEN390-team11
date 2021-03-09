package com.soen390.team11.schedule;

import com.soen390.team11.entity.*;
import com.soen390.team11.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.time.OffsetDateTime;
import java.util.Optional;

@Configuration
@EnableScheduling
public class ScheduleConfig {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private VendorSaleRepository vendorSaleRepository;

    @Autowired
    private PartInventoryRepository partInventoryRepository;

    @Autowired
    private ProductInventoryRepository productInventoryRepository;

    @Autowired
    private MaterialInventoryRepository materialInventoryRepository;

    public static final int ONE_MINUTE = 1000 * 60;

    @Scheduled(fixedRate = 30 * ONE_MINUTE)
    public void doesSomething()
    {
        Iterable<Orders> ordersToDo = ordersRepository.findAllByTimeBefore(OffsetDateTime.now());
        for (Orders order: ordersToDo)
        {
            Optional<VendorSale> vendorSale = vendorSaleRepository.findById(new VendorSaleId(order.getVendorID(),order.getSaleID()));
            if (vendorSale.isPresent())
            {
                switch(vendorSale.get().getType())
                {
                    case PART -> processPart(vendorSale.get().getVendorSaleId().getSaleID(), order.getQuantity());
                    case PRODUCT -> processProduct(vendorSale.get().getVendorSaleId().getSaleID(), order.getQuantity());
                    case MATERIAL -> processMaterial(vendorSale.get().getVendorSaleId().getSaleID(), order.getQuantity());
                }
            }
        }
    }

    private void processPart(String id, int quantity)
    {
        Optional<PartInventory> partInventory = partInventoryRepository.findById(String.valueOf(id));
        if (partInventory.isPresent())
        {
            int currentQuantity = partInventory.get().getQuantity();
            partInventoryRepository.updateInventory(String.valueOf(id), (currentQuantity + quantity));
        }
    }

    private void processProduct(String id, int quantity)
    {
        Optional<ProductInventory> productInventory = productInventoryRepository.findById(String.valueOf(id));
        if (productInventory.isPresent())
        {
            int currentQuantity = productInventory.get().getQuantity();
            partInventoryRepository.updateInventory(String.valueOf(id), (currentQuantity + quantity));
        }
    }

    private void processMaterial(String id, int quantity)
    {
        Optional<MaterialInventory> materialInventory = materialInventoryRepository.findById(String.valueOf(id));
        if (materialInventory.isPresent())
        {
            int currentQuantity = materialInventory.get().getQuantity();
            partInventoryRepository.updateInventory(String.valueOf(id), (currentQuantity + quantity));
        }
    }

}
