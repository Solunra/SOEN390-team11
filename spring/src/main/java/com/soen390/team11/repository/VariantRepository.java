package com.soen390.team11.repository;

import com.soen390.team11.entity.Variant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantRepository extends CrudRepository<Variant, Long> {

    Variant findByValue(String value);

}
