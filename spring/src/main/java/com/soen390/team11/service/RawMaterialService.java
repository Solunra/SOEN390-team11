package com.soen390.team11.service;

import com.soen390.team11.constant.Type;
import com.soen390.team11.dto.RawMaterialRequestDto;
import com.soen390.team11.entity.RawMaterial;
import com.soen390.team11.entity.VendorSale;
import com.soen390.team11.entity.VendorSaleId;
import com.soen390.team11.entity.Vendors;
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




    public List<RawMaterialRequestDto> getAllRawMaterial() {
        List<RawMaterialRequestDto> rawMaterialRequestDtos = new ArrayList<>();
        Iterable<RawMaterial> rawMaterials = rawmaterialRepository.findAll();
        for(RawMaterial rawMaterial:rawMaterials){
            rawMaterialRequestDtos.add(getRawMaterialById(rawMaterial.getrawmaterialid()));
        }
        return rawMaterialRequestDtos;
    }


    public RawMaterialRequestDto getRawMaterialById(String id) {
        try {
            RawMaterial rawmaterial= rawmaterialRepository.findById(id).get();
            VendorSale vendorSale = vendorSaleRepository.findByVendorSaleIdSaleID(rawmaterial.getrawmaterialid()).get();
            Vendors vendors = vendorsRepository.findByVendorID(vendorSale.getVendorSaleId().getVendorID()).get();
            RawMaterialRequestDto rawMaterialRequestDto = new RawMaterialRequestDto();
            rawMaterialRequestDto.setrawmaterialid(rawmaterial.getrawmaterialid());
            rawMaterialRequestDto.setname(rawmaterial.getName());
            rawMaterialRequestDto.setDescription(rawmaterial.getDescription());
            rawMaterialRequestDto.setPrice(rawmaterial.getPrice());
            rawMaterialRequestDto.setUnit(rawmaterial.getUnit());
            rawMaterialRequestDto.setVendorID(vendorSale.getVendorSaleId().getVendorID());
            rawMaterialRequestDto.setCompanyname(vendors.getCompanyname());
            return rawMaterialRequestDto;
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

    public String deleteRawMaterial(String rid) throws Exception {
        Optional<RawMaterial> rawMaterial = rawmaterialRepository.findById(rid);
        if(!rawMaterial.isPresent()){
            throw new Exception("Raw material was not found");
        }
        Optional<VendorSale> vendorSale = vendorSaleRepository.findByVendorSaleIdSaleID(rid);
        if(vendorSale.isPresent()){
            vendorSaleRepository.delete(vendorSale.get());
        }
        rawmaterialRepository.delete(rawMaterial.get());
        return "Delete Successful";
    }


}