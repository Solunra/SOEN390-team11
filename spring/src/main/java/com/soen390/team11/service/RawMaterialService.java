package com.soen390.team11.service;

import com.soen390.team11.constant.Type;
import com.soen390.team11.dto.RawMaterialRequestDto;
import com.soen390.team11.entity.RawMaterial;
import com.soen390.team11.entity.VendorSale;
import com.soen390.team11.entity.VendorSaleId;
import com.soen390.team11.repository.RawMaterialRepository;
import com.soen390.team11.repository.VendorSaleRepository;
import com.soen390.team11.repository.VendorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RawMaterialService {

    @Autowired
    RawMaterialRepository rawmaterialRepository;
    @Autowired
    VendorsRepository vendorsRepository;
    @Autowired
    VendorSaleRepository vendorSaleRepository;




    public List<RawMaterial> getAllRawMaterial() {
        List<RawMaterial> RawMaterials = new ArrayList<>();
        rawmaterialRepository.findAll()
                .forEach(RawMaterials::add);
        return RawMaterials;
    }


    public RawMaterial getRawMaterialById(String id) {
        try {
            RawMaterial rawmaterial= rawmaterialRepository.findById(id).get();
            return rawmaterial;
        } catch (Exception e) {
            return null;

        }
    }

    public String createNewRawMaterial(RawMaterialRequestDto rawMaterialRequestDto) throws Exception {

        if(!vendorsRepository.existsById(rawMaterialRequestDto.getVendorID())){
            throw new Exception("Vendor does not exist");
        }

        RawMaterial newRawMaterial = new RawMaterial(
                rawMaterialRequestDto.getname(),
                rawMaterialRequestDto.getDescription(),
                rawMaterialRequestDto.getPrice(),
                rawMaterialRequestDto.getUnit()
        );
        String rawMaterialidID = rawmaterialRepository.save(newRawMaterial).getrawmaterialid();

        VendorSaleId vendorSaleId = new VendorSaleId(rawMaterialRequestDto.getVendorID(), rawMaterialidID);
        VendorSale vendorSale = new VendorSale(vendorSaleId, Type.RAW_MATERIAL);
        vendorSaleRepository.save(vendorSale);
        return rawMaterialidID;
    }


}