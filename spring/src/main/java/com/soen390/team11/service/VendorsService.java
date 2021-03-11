package com.soen390.team11.service;

import com.soen390.team11.dto.VendorDto;
import com.soen390.team11.entity.Vendors;
import com.soen390.team11.repository.VendorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorsService {

    @Autowired
    VendorsRepository vendorsRepository;

    public List<Vendors> getAllVendors(){return (List<Vendors>) vendorsRepository.findAll();}

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

    public Optional<Vendors> getVendor(String vendorID)
    {
        return vendorsRepository.findByVendorID(vendorID);
    }
}
