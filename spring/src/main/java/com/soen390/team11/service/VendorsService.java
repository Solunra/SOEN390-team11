package com.soen390.team11.service;

import com.soen390.team11.dto.VendorDto;
import com.soen390.team11.entity.Vendors;
import com.soen390.team11.repository.VendorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class VendorsService {

    @Autowired
    VendorsRepository vendorsRepository;

    public String createVendor(VendorDto vendorDto)
    {
        Vendors vendor = new Vendors(vendorDto.getType(), vendorDto.getSaleID());
        Vendors resultantVendor = vendorsRepository.save(vendor);
        return resultantVendor.getVendorID();
    }

    public Optional<Vendors> getVendor(String vendorID)
    {
        return vendorsRepository.findByVendorID(vendorID);
    }
}
