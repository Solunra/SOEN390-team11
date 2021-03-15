package com.soen390.team11.repository;

import com.soen390.team11.entity.RawMaterial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository for Raw Material
 */
@Repository
public interface RawMaterialRepository extends CrudRepository<RawMaterial, String> {
    /**
     * Gets a Raw Material based on its ID
     *
     * @param rawmaterialid The Raw Material's ID
     * @return The Optional Object of a Raw Material
     */
    Optional<RawMaterial> findByRawmaterialid(String rawmaterialid);
}
