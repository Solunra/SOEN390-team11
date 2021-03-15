package com.soen390.team11.repository;

import com.soen390.team11.entity.PartMaterial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository for Part Material
 */
@Repository
public interface PartMaterialRepository extends CrudRepository<PartMaterial, String> {

    /**
     * Finds all materials of a specified part
     *
     * @param partId The part's ID
     * @return List of all part materials
     */
    List<PartMaterial> findAllByPartId(String partId);
}