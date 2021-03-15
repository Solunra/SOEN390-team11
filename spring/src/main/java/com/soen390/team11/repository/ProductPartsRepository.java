package com.soen390.team11.repository;

import com.soen390.team11.entity.ProductParts;
import com.soen390.team11.entity.ProductPartsId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository for Product Parts
 */
@Repository
public interface ProductPartsRepository extends CrudRepository<ProductParts, ProductPartsId> {
    /**
     * Find a Product's parts
     *
     * @param productid The Product's ID
     * @return List of its parts
     */
    List<ProductParts> findByProductPartsIdProductid(String productid);
}


