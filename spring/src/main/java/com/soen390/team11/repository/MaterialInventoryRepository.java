package com.soen390.team11.repository;

import com.soen390.team11.entity.MaterialInventory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for Material Inventory
 */
@Repository
public interface MaterialInventoryRepository extends CrudRepository<MaterialInventory, String> {

    /**
     * Updates the quantity of a material inventory
     *
     * @param id The Material Inventory ID
     * @param newQuantity The new Quantity
     */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE material_inventory mi SET mi.quantity = :quantity WHERE mi.id = :id")
    void updateInventory(@Param("id") String id, @Param("quantity") int newQuantity);
}
