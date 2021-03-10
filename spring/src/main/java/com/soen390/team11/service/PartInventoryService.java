package com.soen390.team11.service;

import com.soen390.team11.dto.PartInventoryResponse;
import com.soen390.team11.entity.Part;
import com.soen390.team11.entity.PartInventory;
import com.soen390.team11.repository.PartInventoryRepository;
import com.soen390.team11.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.PartialResultException;
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
    public ArrayList<PartInventoryResponse> getAllPartInventory(){
        List<PartInventory> partInventories= (List<PartInventory>) partInventoryRepository.findAll();
        List<Part> parts = (List<Part>) partRepository.findAll();
        ArrayList<PartInventoryResponse> partInventoryList = new ArrayList<>();
        PartInventoryResponse partInventoryResponse = new PartInventoryResponse();
        for(PartInventory pi : partInventories){
            for(Part p : parts){
                if(p.getPartid().equals(pi.getPartid())){
                    partInventoryResponse=new PartInventoryResponse(pi.getId(),pi.getLocation(),pi.getQuantity(),pi.getPartid(),p.getName());
                }
            }
            partInventoryList.add(partInventoryResponse);
        }
        return partInventoryList;
    }
}
