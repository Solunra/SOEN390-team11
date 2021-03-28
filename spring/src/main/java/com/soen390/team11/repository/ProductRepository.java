package com.soen390.team11.repository;


import com.soen390.team11.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Repository for Product
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
    /**
     * find first 10 by size
     * @param size
     * @return
     */
    List<Product> findFirst10BySize(String size);

    /**
     * find by Name color size finish
     * @param name
     * @param color
     * @param size
     * @param finish
     * @return
     */
    List<Product> findByNameAndColorAndSizeAndFinish(String name, String color, String size, String finish);

    /**
     * find by product id
     * @param productid
     * @return
     */
    Product findByProductid(String productid);

    /**
     * delete by product Id
     * @param productid
     */
    @Transactional
    void deleteByProductid(String productid);

    /**
     * find product by name type size color finish grade
     * @param name
     * @param type
     * @param size
     * @param color
     * @param finish
     * @param grade
     * @return
     */
    Optional<Product> findByNameAndTypeAndSizeAndColorAndFinishAndGrade(String name, String type, String size, String color, String finish, String grade);

}
