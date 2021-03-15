package com.soen390.team11.repository;

import com.soen390.team11.entity.Material;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Material
 */
@Repository
public interface MaterialRepository extends CrudRepository<Material, String> {

}
