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

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE ProductMachinery mac SET mac.timer = mac.timer - 1 WHERE mac.timer > 0 and mac.status = 'RUNNING'")
    void decrementAllTimers();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE ProductMachinery mac SET mac.status = 'READY' WHERE mac.timer = 0 and mac.status = 'RUNNING'")
    void updateStatus();
}
