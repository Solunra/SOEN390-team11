package com.soen390.team11.schedule;

import com.soen390.team11.entity.MaterialInventory;
import com.soen390.team11.entity.Orders;
import com.soen390.team11.entity.PartInventory;
import com.soen390.team11.entity.ProductInventory;
import com.soen390.team11.entity.VendorSale;
import com.soen390.team11.entity.VendorSaleId;
import com.soen390.team11.repository.MaterialInventoryRepository;
import com.soen390.team11.repository.OrdersRepository;
import com.soen390.team11.repository.PartInventoryRepository;
import com.soen390.team11.repository.ProductInventoryRepository;
import com.soen390.team11.repository.ProductMachineryRepository;
import com.soen390.team11.repository.VendorSaleRepository;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Scheduled Jobs for the ERP Solution
 */
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

    @Autowired
    private ProductMachineryRepository productMachineryRepository;

    public static final int ONE_SECOND = 1000;
    public static final int ONE_MINUTE = ONE_SECOND * 60;

    /**
     * Processes the order every 30 minutes
     */
    @Scheduled(fixedRate = 30 * ONE_MINUTE)
    public void processOrders()
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


    /**
     * Update status every minute.
     */
    @Scheduled(fixedRate = ONE_SECOND)
    public void updateStatus()
    {
        productMachineryRepository.decrementAllTimers();
        productMachineryRepository.updateStock();
        productMachineryRepository.checkTimerRunningOut();
    }

    /**
     * Processes a parts's order
     *
     * @param id The Part's ID
     * @param quantity The Quantity for the Part
     */
    private void processPart(String id, int quantity)
    {
        Optional<PartInventory> partInventory = partInventoryRepository.findById(String.valueOf(id));
        if (partInventory.isPresent())
        {
            int currentQuantity = partInventory.get().getQuantity();
            partInventoryRepository.updateInventory(String.valueOf(id), (currentQuantity + quantity));
        }
    }

    /**
     * Processes a products's order
     *
     * @param id The Product's ID
     * @param quantity The Quantity for the Product
     */
    private void processProduct(String id, int quantity)
    {
        Optional<ProductInventory> productInventory = productInventoryRepository.findById(String.valueOf(id));
        if (productInventory.isPresent())
        {
            int currentQuantity = productInventory.get().getQuantity();
            productInventoryRepository.updateInventory(String.valueOf(id), (currentQuantity + quantity));
        }
    }

    /**
     * Processes a material's order
     *
     * @param id The Material's ID
     * @param quantity The Quantity for the Material
     */
    private void processMaterial(String id, int quantity)
    {
        Optional<MaterialInventory> materialInventory = materialInventoryRepository.findById(String.valueOf(id));
        if (materialInventory.isPresent())
        {
            int currentQuantity = materialInventory.get().getQuantity();
            materialInventoryRepository.updateInventory(String.valueOf(id), (currentQuantity + quantity));
        }
    }

}
