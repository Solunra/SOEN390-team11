package com.soen390.team11.schedule;

import com.soen390.team11.entity.MaterialInventory;
import com.soen390.team11.entity.Orders;
import com.soen390.team11.entity.PartInventory;
import com.soen390.team11.entity.ProductInventory;
import com.soen390.team11.entity.Vendors;
import com.soen390.team11.repository.MaterialInventoryRepository;
import com.soen390.team11.repository.OrdersRepository;
import com.soen390.team11.repository.PartInventoryRepository;
import com.soen390.team11.repository.ProductInventoryRepository;
import com.soen390.team11.repository.RawMaterialRepository;
import com.soen390.team11.repository.VendorsRepository;
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
    private VendorsRepository vendorsRepository;

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
            Optional<Vendors> vendor = vendorsRepository.findByVendorID(order.getVendorID());
            if (vendor.isPresent())
            {
                switch(vendor.get().getType())
                {
                    case PART -> processPart(vendor.get().getSaleID(), order.getQuantity());
                    case PRODUCT -> processProduct(vendor.get().getSaleID(), order.getQuantity());
                    case MATERIAL -> processMaterial(vendor.get().getSaleID(), order.getQuantity());
                }
            }
        }
    }

    private void processPart(String id, int quantity)
    {
        Optional<PartInventory> partInventory = partInventoryRepository.findById(Long.valueOf(id));
        if (partInventory.isPresent())
        {
            int currentQuantity = partInventory.get().getQuantity();
            partInventoryRepository.updateInventory(Long.valueOf(id), (currentQuantity + quantity));
        }
    }

    private void processProduct(String id, int quantity)
    {
        Optional<ProductInventory> productInventory = productInventoryRepository.findById(Long.valueOf(id));
        if (productInventory.isPresent())
        {
            int currentQuantity = productInventory.get().getQuantity();
            partInventoryRepository.updateInventory(Long.valueOf(id), (currentQuantity + quantity));
        }
    }

    private void processMaterial(String id, int quantity)
    {
        Optional<MaterialInventory> materialInventory = materialInventoryRepository.findById(Long.valueOf(id));
        if (materialInventory.isPresent())
        {
            int currentQuantity = materialInventory.get().getQuantity();
            partInventoryRepository.updateInventory(Long.valueOf(id), (currentQuantity + quantity));
        }
    }

}
