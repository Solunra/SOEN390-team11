package com.soen390.team11.controller;

import com.soen390.team11.dto.VendorDto;
import com.soen390.team11.service.VendorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    public VendorsService vendorsService;

    @GetMapping("/{vid}")
    public ResponseEntity getVendorById(@PathVariable String vid)
    {
        return ResponseEntity.of(vendorsService.getVendor(vid));
    }

    @PostMapping
    public ResponseEntity createVendor(@RequestBody VendorDto vendorDto)
    {
        vendorsService.createVendor(vendorDto);
        return ResponseEntity.ok().build();
    }
}
