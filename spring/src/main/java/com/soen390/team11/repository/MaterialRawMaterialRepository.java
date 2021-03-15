package com.soen390.team11.repository;

import com.soen390.team11.entity.MaterialRawMaterials;
import com.soen390.team11.entity.MaterialRawMaterialsId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository for Material and Raw Material
 */
@Repository
public interface MaterialRawMaterialRepository extends CrudRepository<MaterialRawMaterials, MaterialRawMaterialsId> {

    /**
     * Finds Material based on Raw Materials
     *
     * @param Rawmaterialid Raw Material ID
     * @return List of Material and Raw Material
     */
    List<MaterialRawMaterials> findByMaterialRawMaterialsIdRawmaterialid(String Rawmaterialid);
}


