package com.soen390.team11.service;

import com.soen390.team11.dto.VendorDto;
import com.soen390.team11.entity.Vendors;
import com.soen390.team11.repository.VendorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service Layer for Vendors
 */
@Service
public class VendorsService {

    @Autowired
    VendorsRepository vendorsRepository;

    /**
     * Gets all vendors
     *
     * @return List of all vendors
     */
    public List<Vendors> getAllVendors(){return (List<Vendors>) vendorsRepository.findAll();}

    /**
     * Creates a new Vendor
     *
     * @param vendorDto The new Vendor's details
     * @return The new vendor's ID
     */
    public String createVendor(VendorDto vendorDto)
    {
        Vendors vendor = new Vendors(
                vendorDto.getCompanyName(),
                vendorDto.getAddress(),
                vendorDto.getPhone(),
                vendorDto.getEmail());
        Vendors resultantVendor = vendorsRepository.save(vendor);
        return resultantVendor.getVendorID();
    }

    /**
     * Gets a specific vendor
     *
     * @param vendorID The vendor's ID
     * @return The vendor wrapped in an Optional
     */
    public Optional<Vendors> getVendor(String vendorID)
    {
        return vendorsRepository.findByVendorID(vendorID);
    }
}
