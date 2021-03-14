package com.soen390.team11.repository;

import com.soen390.team11.entity.RawMaterial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RawMaterialRepository extends CrudRepository<RawMaterial, String> {
    Optional<RawMaterial> findByRawmaterialid(String rawmaterialid);
}
