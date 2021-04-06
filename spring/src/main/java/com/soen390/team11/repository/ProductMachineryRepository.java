package com.soen390.team11.repository;

import com.soen390.team11.entity.ProductMachinery;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Repository for Product Machinery
 */
@Repository
public interface ProductMachineryRepository extends CrudRepository<ProductMachinery, String> {

    /**
     * Decrement timer of every product machinery by 1 second.
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE ProductMachinery mac SET mac.timer = mac.timer - 1 WHERE mac.timer > 0 and mac.status = 'RUNNING'")
    void decrementAllTimers();


    /**
     * Update status of every product machinery when timer reaches 0.
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE ProductMachinery mac SET mac.status = 'READY' WHERE mac.timer = 0 and mac.status = 'RUNNING'")
    void checkTimerRunningOut();

    /**
     * Update status of every product machinery when timer reaches 0.
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE product_inventory inv SET inv.quantity = inv.quantity + 1 WHERE inv.productid = (SELECT mac.product.productid FROM ProductMachinery mac WHERE mac.timer = 0 and mac.status = 'RUNNING')")
    void updateStock();
}
