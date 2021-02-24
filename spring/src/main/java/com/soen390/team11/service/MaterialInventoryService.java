package com.soen390.team11.service;

import com.soen390.team11.dto.MaterialInventoryResponse;
import com.soen390.team11.entity.Material;
import com.soen390.team11.entity.MaterialInventory;
import com.soen390.team11.repository.MaterialInventoryRepository;
import com.soen390.team11.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MaterialInventoryService {
    @Autowired
    MaterialInventoryRepository materialInventoryRepository;
    @Autowired
    MaterialRepository materialRepository;

    public ArrayList<MaterialInventoryResponse> getAllMaterialInventory() {
        List<MaterialInventory> materialInventories= (List<MaterialInventory>) materialInventoryRepository.findAll();
        List<Material> materials = (List<Material>) materialRepository.findAll();
        ArrayList<MaterialInventoryResponse> materialInventoryList = new ArrayList<>();
        MaterialInventoryResponse materialInventoryResponse= new MaterialInventoryResponse();
        for(MaterialInventory mi: materialInventories){
            for(Material m : materials){
                if(mi.getMaterialid() == m.getMaterialid()){
                    materialInventoryResponse = new MaterialInventoryResponse(mi.getId(),mi.getLocation(),mi.getQuantity(),mi.getMaterialid(),m.getName());
                }
            }
            materialInventoryList.add(materialInventoryResponse);
        }
        return materialInventoryList;
    }
}
