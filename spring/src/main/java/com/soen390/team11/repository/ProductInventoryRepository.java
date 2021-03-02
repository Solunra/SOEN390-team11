package com.soen390.team11.repository;

import com.soen390.team11.entity.ProductInventory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInventoryRepository extends CrudRepository<ProductInventory, String> {
    ProductInventory findByProductid(String id);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE product_inventory pi SET pi.quantity = :quantity WHERE pi.id = :id")
    void updateInventory(@Param("id") String id, @Param("quantity") int newQuantity);
}
