package com.soen390.team11.repository;

import com.soen390.team11.entity.Vendors;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface VendorsRepository extends CrudRepository<Vendors, String> {
    Optional<Vendors> findByVendorID(String s);
}
