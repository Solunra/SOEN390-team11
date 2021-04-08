package com.soen390.team11.service;

import com.soen390.team11.constant.LogTypes;
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
    LogService logService;

    public PartInventoryService(PartInventoryRepository partInventoryRepository, PartRepository partRepository, LogService logService) {
        this.partInventoryRepository = partInventoryRepository;
        this.partRepository = partRepository;
        this.logService = logService;
    }

    /**
     * Gets all Part's Inventory
     *
     * @return List of all Part's Inventory
     */
    public ArrayList<PartInventoryResponse> getAllPartInventory(){
        logService.writeLog(LogTypes.PART,"Getting all parts in the inventory...");
        List<PartInventory> partInventories= (List<PartInventory>) partInventoryRepository.findAll();
        List<Part> parts = (List<Part>) partRepository.findAll();
        ArrayList<PartInventoryResponse> partInventoryList = new ArrayList<>();
        PartInventoryResponse partInventoryResponse = new PartInventoryResponse();
        logService.writeLog(LogTypes.PART,"Going through the part inventory");
        for(PartInventory pi : partInventories){
            for(Part p : parts){
                if(p.getPartid().equals(pi.getPartid())){
                    partInventoryResponse=new PartInventoryResponse(pi.getId(),pi.getLocation(),pi.getQuantity(),pi.getPartid(),p.getName());
                }
            }
            partInventoryList.add(partInventoryResponse);
        }
        logService.writeLog(LogTypes.PART,"Returning all parts in the inventory");
        return partInventoryList;
    }


    /**
     * Updates a part inventory
     *
     * @param partInventory the part inventory to be updated
     * @return The updated PartInventory's details
     */
    public PartInventory updatePartInventory(PartInventory partInventory) {
        logService.writeLog(LogTypes.PART,"Updating the Part inventory");
        return partInventoryRepository.save(partInventory);
    }
}
