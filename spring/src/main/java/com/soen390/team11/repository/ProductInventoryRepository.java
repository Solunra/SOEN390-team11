package com.soen390.team11.repository;

import com.soen390.team11.entity.ProductInventory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for Product Inventory
 */
@Repository
public interface ProductInventoryRepository extends CrudRepository<ProductInventory, String> {
    /**
     * Get a product's inventory by its ID
     *
     * @param id The Product Inventory's ID
     * @return The Product Inventory
     */
    ProductInventory findByProductid(String id);

    /**
     * Updates product inventory with a new quantity
     *
     * @param id The product's ID
     * @param newQuantity The new quantity
     */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE product_inventory pi SET pi.quantity = :quantity WHERE pi.id = :id")
    void updateInventory(@Param("id") String id, @Param("quantity") int newQuantity);
}
