package com.soen390.team11.service;

import com.soen390.team11.entity.Part;
import com.soen390.team11.entity.PartInventory;
import com.soen390.team11.repository.PartInventoryRepository;
import com.soen390.team11.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PartInventoryService {
    @Autowired
    PartInventoryRepository partInventoryRepository;
    @Autowired
    PartRepository partRepository;
    public ArrayList<Map<String ,String >> getAllPartInventory(){
        List<PartInventory> partInventories= (List<PartInventory>) partInventoryRepository.findAll();
        List<Part> parts = (List<Part>) partRepository.findAll();
        ArrayList<Map<String ,String >> partInventoryList = new ArrayList<>();
        for(PartInventory pi : partInventories){
            Map<String ,String > partMap = new HashMap<>();
            for(Part p : parts){
                if(p.getPartid() == pi.getPartid()){
                    partMap.put("name",p.getName());
                    partMap.put("id", pi.getId().toString());
                    partMap.put("quantity", String.valueOf(pi.getQuantity()));
                    partMap.put("location",pi.getLocation());
                    partMap.put("productid", String.valueOf(pi.getPartid()));
                }
            }
            partInventoryList.add(partMap);
        }
        return partInventoryList;
    }
}
