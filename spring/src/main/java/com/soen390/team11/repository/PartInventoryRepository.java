package com.soen390.team11.repository;

import com.soen390.team11.entity.PartInventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartInventoryRepository extends CrudRepository<PartInventory, Long> {

}