package com.soen390.team11.repository;

import com.soen390.team11.entity.MaterialInventory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialInventoryRepository extends CrudRepository<MaterialInventory, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE material_inventory mi SET mi.quantity = :quantity WHERE mi.id = :id")
    void updateInventory(@Param("id") Long id, @Param("quantity") int newQuantity);
}
