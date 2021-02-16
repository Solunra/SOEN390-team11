package com.soen390.team11.repository;

import com.soen390.team11.entity.Part;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PartRepository extends CrudRepository<Part, Long> {

}
