package com.soen390.team11.repository;

import com.soen390.team11.entity.PartMaterial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartMaterialRepository extends CrudRepository<PartMaterial, String> {
    List<PartMaterial> findAllByPart_id(String part_id);
}