package com.soen390.team11.controller;

import com.soen390.team11.dto.VendorDto;
import com.soen390.team11.entity.Vendors;
import com.soen390.team11.service.VendorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    public VendorsService vendorsService;

    @GetMapping("/{vid}")
    public ResponseEntity getVendorById(@PathVariable String vid)
    {
        Optional<Vendors> vendor = vendorsService.getVendor(vid);
        if (vendor.isPresent())
        {
            VendorDto vendorDto = new VendorDto(vendor.get().getVendorID(), vendor.get().getType(), vendor.get().getSaleID());
            return ResponseEntity.ok(vendorDto);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity createVendor(@RequestBody VendorDto vendorDto)
    {
        String id = vendorsService.createVendor(vendorDto);
        return ResponseEntity.ok(id);
    }
}
