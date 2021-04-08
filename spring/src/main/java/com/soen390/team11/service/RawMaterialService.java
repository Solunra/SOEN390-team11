package com.soen390.team11.service;

import com.soen390.team11.constant.LogTypes;
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

/**
 * Service Layer for Raw Material
 */
@Service
public class RawMaterialService {

    RawMaterialRepository rawmaterialRepository;
    VendorsRepository vendorsRepository;
    VendorSaleRepository vendorSaleRepository;
    LogService logService;

    public RawMaterialService(RawMaterialRepository rawmaterialRepository, VendorsRepository vendorsRepository, VendorSaleRepository vendorSaleRepository
    , LogService logService) {
        this.rawmaterialRepository = rawmaterialRepository;
        this.vendorsRepository = vendorsRepository;
        this.vendorSaleRepository = vendorSaleRepository;
        this.logService = logService;
    }

    /**
     * Gets all Raw Materials
     *
     * @return List of Raw Materials
     */
    public List<RawMaterialRequestDto> getAllRawMaterial() {
        List<RawMaterialRequestDto> rawMaterialRequestDtos = new ArrayList<>();
        Iterable<RawMaterial> rawMaterials = rawmaterialRepository.findAll();
        logService.writeLog(LogTypes.MATERIAL,"getting all raw materials");
        for(RawMaterial rawMaterial:rawMaterials){
            rawMaterialRequestDtos.add(getRawMaterialById(rawMaterial.getrawmaterialid()));
        }
        logService.writeLog(LogTypes.PRODUCT,"Returning the rawmaterial Dtolist");
        return rawMaterialRequestDtos;
    }

    /**
     * Gets a Raw Material by its ID
     *
     * @param id Raw Material's ID
     * @return Object with the necessary Raw Material's Information
     */
    public RawMaterialRequestDto getRawMaterialById(String id) {
        try {
            logService.writeLog(LogTypes.PRODUCT,"Getting raw material by ID");
            RawMaterial rawmaterial= rawmaterialRepository.findById(id).get();
            logService.writeLog(LogTypes.PRODUCT,"Looking through the vendors");
            VendorSale vendorSale = vendorSaleRepository.findByVendorSaleIdSaleID(rawmaterial.getrawmaterialid()).get();
            Vendors vendors = vendorsRepository.findByVendorID(vendorSale.getVendorSaleId().getVendorID()).get();
            RawMaterialRequestDto rawMaterialRequestDto = new RawMaterialRequestDto();
            logService.writeLog(LogTypes.PRODUCT,"Setting the rawmaterial ID");
            rawMaterialRequestDto.setrawmaterialid(rawmaterial.getrawmaterialid());
            logService.writeLog(LogTypes.PRODUCT,"Setting the rawmaterial name");
            rawMaterialRequestDto.setname(rawmaterial.getName());
            logService.writeLog(LogTypes.PRODUCT,"Setting the rawmaterial description");
            rawMaterialRequestDto.setDescription(rawmaterial.getDescription());
            logService.writeLog(LogTypes.PRODUCT,"Setting the rawmaterial cost");
            rawMaterialRequestDto.setCost(rawmaterial.getCost());
            logService.writeLog(LogTypes.PRODUCT,"Setting the rawmaterial unit");
            rawMaterialRequestDto.setUnit(rawmaterial.getUnit());
            logService.writeLog(LogTypes.PRODUCT,"Setting the rawmaterial vendorID");
            rawMaterialRequestDto.setVendorID(vendorSale.getVendorSaleId().getVendorID());
            logService.writeLog(LogTypes.PRODUCT,"Setting the rawmaterial company name");
            rawMaterialRequestDto.setCompanyname(vendors.getCompanyname());
            logService.writeLog(LogTypes.PRODUCT,"Returning the rawmaterial");
            return rawMaterialRequestDto;
        } catch (Exception e) {
            return null;

        }
    }

    /**
     * Creates a new Raw Material
     *
     * @param rawMaterialRequestDto The Raw Material's Details
     * @return The new Raw Material's ID
     * @throws Exception Thrown if the vendor for the raw material does not exist
     */
    public String createNewRawMaterial(RawMaterialRequestDto rawMaterialRequestDto) throws Exception {

        if(!vendorsRepository.existsById(rawMaterialRequestDto.getVendorID())){
            throw new Exception("Vendor does not exist");
        }
        logService.writeLog(LogTypes.PRODUCT,"Creating a new rawmaterial");
        RawMaterial newRawMaterial = new RawMaterial(
                rawMaterialRequestDto.getname(),
                rawMaterialRequestDto.getDescription(),
                rawMaterialRequestDto.getCost(),
                rawMaterialRequestDto.getUnit()
        );
        logService.writeLog(LogTypes.PRODUCT,"Saving the rawmaterial ID");
        String rawMaterialidID = rawmaterialRepository.save(newRawMaterial).getrawmaterialid();
        logService.writeLog(LogTypes.PRODUCT,"Adding the rawmaterial to a vendor");
        VendorSaleId vendorSaleId = new VendorSaleId(rawMaterialRequestDto.getVendorID(), rawMaterialidID);
        VendorSale vendorSale = new VendorSale(vendorSaleId, Type.RAW_MATERIAL);
        logService.writeLog(LogTypes.PRODUCT,"Saving the new raw material");
        vendorSaleRepository.save(vendorSale);
        return rawMaterialidID;
    }

    /**
     * Update an existing raw material
     *
     * @param rid The raw material's ID
     * @param rawMaterialRequestDto The Raw Material's updated information
     * @return The raw material's ID
     * @throws Exception Thrown if the ID does not exist or the vendor no longer exists
     */
    public String updateRawMaterial(String rid, RawMaterialRequestDto rawMaterialRequestDto) throws Exception {
        if(!rawmaterialRepository.existsById(rid)){
            throw new Exception("Raw material not found");
        }

        if(!vendorsRepository.existsById(rawMaterialRequestDto.getVendorID())){
            throw new Exception("Vendor does not exist");
        }
        logService.writeLog(LogTypes.PRODUCT,"Looking though vendors to find the raw material to update");
        Optional<VendorSale> vendorSale = vendorSaleRepository.findById(new VendorSaleId(rawMaterialRequestDto.getVendorID(),rid));
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
        logService.writeLog(LogTypes.PRODUCT,"Updating an existing raw material");
        RawMaterial rawMaterial = rawmaterialRepository.findById(rid).get();
        rawMaterial.setName(rawMaterialRequestDto.getname());
        rawMaterial.setDescription(rawMaterialRequestDto.getDescription());
        rawMaterial.setCost(rawMaterialRequestDto.getCost());
        rawMaterial.setUnit(rawMaterialRequestDto.getUnit());
        rawmaterialRepository.save(rawMaterial);

        return rid;

    }

    /**
     * Delete Raw Materials
     *
     * @param rid The Raw Material's ID
     * @return Success message
     * @throws Exception Thrown if the raw material does not exist
     */
    public String deleteRawMaterial(String rid) throws Exception {
        Optional<RawMaterial> rawMaterial = rawmaterialRepository.findById(rid);
        if(!rawMaterial.isPresent()){
            throw new Exception("Raw material was not found");
        }
        logService.writeLog(LogTypes.PRODUCT,"Deleting an existing raw material");
        rawmaterialRepository.delete(rawMaterial.get());
        Optional<VendorSale> vendorSale = vendorSaleRepository.findByVendorSaleIdSaleID(rid);
        if(vendorSale.isPresent()){
            vendorSaleRepository.delete(vendorSale.get());
        }
        return "Delete Successful";
    }


}