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

    public String updateRawMaterial(String rid, RawMaterialRequestDto rawMaterialRequestDto) throws Exception {
        if(!rawmaterialRepository.existsById(rid)){
            throw new Exception("Raw material not found");
        }

        if(!vendorsRepository.existsById(rawMaterialRequestDto.getVendorID())){
            throw new Exception("Vendor does not exist");
        }

        Optional<VendorSale> vendorSale = vendorSaleRepository.findById( new VendorSaleId(rawMaterialRequestDto.getVendorID(),rid));
        if(!vendorSale.isPresent()){
            Optional<VendorSale> oldVendor = vendorSaleRepository.findByVendorSaleIdSaleID(rid);
            if(!oldVendor.isPresent()){
                throw new Exception("This raw material does not have a vendor");
            }
            vendorSaleRepository.delete(oldVendor.get());
            VendorSale newVendorSale = new VendorSale(
                    new VendorSaleId(rawMaterialRequestDto.getVendorID(),rid),
                    Type.RAW_MATERIAL
            );
            vendorSaleRepository.save(newVendorSale);
        }

        RawMaterial rawMaterial = rawmaterialRepository.findById(rid).get();
        rawMaterial.setName(rawMaterialRequestDto.getname());
        rawMaterial.setDescription(rawMaterialRequestDto.getDescription());
        rawMaterial.setPrice(rawMaterialRequestDto.getPrice());
        rawMaterial.setUnit(rawMaterialRequestDto.getUnit());
        rawmaterialRepository.save(rawMaterial);

        return rid;

    }
    


}