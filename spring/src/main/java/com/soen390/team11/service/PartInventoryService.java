package com.soen390.team11.service;

import com.soen390.team11.dto.PartInventoryResponse;
import com.soen390.team11.entity.Part;
import com.soen390.team11.entity.PartInventory;
import com.soen390.team11.repository.PartInventoryRepository;
import com.soen390.team11.repository.PartRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service Layer for Part Inventory
 */
@Service
public class PartInventoryService {

    PartInventoryRepository partInventoryRepository;
    PartRepository partRepository;

    public PartInventoryService(PartInventoryRepository partInventoryRepository, PartRepository partRepository) {
        this.partInventoryRepository = partInventoryRepository;
        this.partRepository = partRepository;
    }

    /**
     * Gets all Part's Inventory
     *
     * @return List of all Part's Inventory
     */
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


    /**
     * Updates a part inventory
     *
     * @param partInventory the part inventory to be updated
     * @return The updated PartInventory's details
     */
    public PartInventory updatePartInventory(PartInventory partInventory) {
        return partInventoryRepository.save(partInventory);
    }
}
