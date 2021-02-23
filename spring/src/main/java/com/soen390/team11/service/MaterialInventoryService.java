package com.soen390.team11.service;

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

    public ArrayList<Map<String ,String >> getAllMaterialInventory() {
        List<MaterialInventory> materialInventories= (List<MaterialInventory>) materialInventoryRepository.findAll();
        List<Material> materials = (List<Material>) materialRepository.findAll();
        ArrayList<Map<String ,String >> materialInventoryList = new ArrayList<>();
        for(MaterialInventory mi: materialInventories){
            Map<String ,String > materialMap = new HashMap<>();
            for(Material m : materials){
                if(mi.getMaterialid() == m.getMaterialid()){
                    materialMap.put("name",m.getName());
                    materialMap.put("id", mi.getId().toString());
                    materialMap.put("quantity", String.valueOf(mi.getQuantity()));
                    materialMap.put("location",mi.getLocation());
                    materialMap.put("materialid", String.valueOf(mi.getMaterialid()));
                }
            }
            materialInventoryList.add(materialMap);
        }
        return materialInventoryList;
    }
}
