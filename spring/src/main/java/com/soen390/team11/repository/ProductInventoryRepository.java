package com.soen390.team11.repository;

import com.soen390.team11.entity.ProductInventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInventoryRepository extends CrudRepository<ProductInventory, Long> {

}
