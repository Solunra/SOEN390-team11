package com.soen390.team11.repository;

import com.soen390.team11.entity.VendorSale;
import com.soen390.team11.entity.VendorSaleId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository for Vendor Sale
 */
@Repository
public interface VendorSaleRepository extends CrudRepository<VendorSale, VendorSaleId> {
    /**
     * Find a vendor based on the sale ID
     *
     * @param rid The Vendor Sale ID
     * @return The VendorSale in an Optional Object
     */
    Optional<VendorSale> findByVendorSaleIdSaleID(String rid);
}
