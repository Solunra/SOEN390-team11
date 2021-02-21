package com.soen390.team11.repository;

import com.soen390.team11.entity.MaterialRawMaterials;
import com.soen390.team11.entity.MaterialRawMaterialsId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRawMaterialRepository extends CrudRepository<MaterialRawMaterials, MaterialRawMaterialsId> {
    List<MaterialRawMaterials> findByMaterialRawMaterialsIdRawmaterialid(Long Rawmaterialid);
}


