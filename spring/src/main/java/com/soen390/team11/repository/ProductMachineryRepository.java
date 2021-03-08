package com.soen390.team11.repository;

import com.soen390.team11.entity.ProductMachinery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMachineryRepository extends CrudRepository<ProductMachinery, String> {

}
