package com.soen390.team11.service;

import com.soen390.team11.constant.LogTypes;
import com.soen390.team11.dto.MaterialInventoryResponse;
import com.soen390.team11.entity.Material;
import com.soen390.team11.entity.MaterialInventory;
import com.soen390.team11.repository.MaterialInventoryRepository;
import com.soen390.team11.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Service Layer for Material Inventory
 */
@Service
public class MaterialInventoryService {

    MaterialInventoryRepository materialInventoryRepository;
    MaterialRepository materialRepository;
    LogService logService;

    public MaterialInventoryService(MaterialInventoryRepository materialInventoryRepository, MaterialRepository materialRepository, LogService logService) {
        this.materialInventoryRepository = materialInventoryRepository;
        this.materialRepository = materialRepository;
        this.logService = logService;
    }

    /**
     * Gets all Material Inventory
     *
     * @return List of all Material's Inventory
     */
    public List<MaterialInventoryResponse> getAllMaterialInventory() {
        logService.writeLog(LogTypes.MATERIAL,"Generating all the material inventory");
        List<MaterialInventory> materialInventories= (List<MaterialInventory>) materialInventoryRepository.findAll();
        List<Material> materials = (List<Material>) materialRepository.findAll();
        ArrayList<MaterialInventoryResponse> materialInventoryList = new ArrayList<>();
        MaterialInventoryResponse materialInventoryResponse= new MaterialInventoryResponse();
        logService.writeLog(LogTypes.MATERIAL,"Gathering every material");
        for(MaterialInventory mi: materialInventories){
            for(Material m : materials){
                if(mi.getMaterialid().equals(m.getMaterialid())){
                    materialInventoryResponse = new MaterialInventoryResponse(mi.getId(),mi.getLocation(),mi.getQuantity(),mi.getMaterialid(),m.getName());
                }
            }
            materialInventoryList.add(materialInventoryResponse);
        }
        logService.writeLog(LogTypes.MATERIAL,"Returning full material inventory");
        return materialInventoryList;
    }
}
