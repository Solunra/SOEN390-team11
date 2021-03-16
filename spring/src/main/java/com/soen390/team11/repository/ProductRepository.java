package com.soen390.team11.repository;


import com.soen390.team11.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Product
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
    List<Product> findFirst10BySize(String size);
    List<Product> findByNameAndColorAndSizeAndFinish(String name, String color, String size, String finish);
    Product findByProductid(String productid);
}
