package com.soen390.team11.repository;

import com.soen390.team11.entity.Vendors;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for Vendor
 */
@Repository
public interface VendorsRepository extends CrudRepository<Vendors, String> {
    /**
     * Get a Vendor based on its Vendor ID
     *
     * @param s The Vendor ID
     * @return The optional object with Vendors
     */
    Optional<Vendors> findByVendorID(String s);
}
